import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class TestRunner {
    public static void runTests(Class<?> c) {
        TreeMap<Integer, Method> methodTreeMap = new TreeMap<>();
        List<Method> beforeSuiteList = new ArrayList<>();
        List<Method> afterSuiteList = new ArrayList<>();
        List<Method> beforeTestList = new ArrayList<>();
        List<Method> afterTestList = new ArrayList<>();
        Object object = new Object();
        HashMap<Method, Object[]> csvSourceMap = new HashMap<>();

        //System.out.println(c.getName());

        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
//            System.out.println("------------------------");
//            System.out.println(method.getName());
//            System.out.println(method.getDefaultValue());
//            System.out.println(Arrays.toString(method.getGenericParameterTypes()));
//            System.out.println(method.getModifiers());

            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
//                System.out.println(annotation);

                if (annotation instanceof Test) {
                    int priority = ((Test) annotation).priority();
//                    System.out.println(priority);
                    methodTreeMap.put(priority, method);
                }

                if (annotation instanceof BeforeSuite && method.getModifiers() == (Modifier.PUBLIC + Modifier.STATIC)) {
                    beforeSuiteList.add(method);
                }

                if (annotation instanceof AfterSuite && method.getModifiers() == (Modifier.PUBLIC + Modifier.STATIC)) {
                    afterSuiteList.add(method);
                }

                if (annotation instanceof BeforeTest) {
                    beforeTestList.add(method);
                }
                if (annotation instanceof AfterTest) {
                    afterTestList.add(method);
                }
                if (annotation instanceof CsvSource) {
                    Object[] typeParams = method.getGenericParameterTypes();
                    String strValue = ((CsvSource) annotation).value();
                    if (!strValue.isEmpty()) {
                        String[] strParams = strValue.split(", ");
                        Object[] objParams = new Object[strParams.length];
                        try {
                            for (int i = 0; i < strParams.length; i++) {
                                Class<?> classType = (Class<?>) typeParams[i];
//                                System.out.println(classType);
                                switch (classType.getName()) {
                                    case ("int"):
                                        objParams[i] = Integer.valueOf(strParams[i]);
                                        break;
                                    case ("float"):
                                        objParams[i] = Float.valueOf(strParams[i]);
                                        break;
                                    case ("double"):
                                        objParams[i] = Double.valueOf(strParams[i]);
                                        break;
                                    case ("byte"):
                                        objParams[i] = Byte.valueOf(strParams[i]);
                                        break;
                                    case ("char"):
                                        objParams[i] = strParams[i].charAt(0);
                                        break;
                                    case ("boolean"):
                                        objParams[i] = Boolean.valueOf(strParams[i]);
                                        break;
                                    case ("short"):
                                        objParams[i] = Short.valueOf(strParams[i]);
                                        break;
                                    case ("long"):
                                        objParams[i] = Long.valueOf(strParams[i]);
                                        break;
                                    default:
                                        objParams[i] = classType.getConstructor(String.class).newInstance(strParams[i]);
                                        break;
                                }
                            }
                            csvSourceMap.put(method, objParams);
                        } catch (Exception e) {
                            csvSourceMap.put(method, null);
                            System.out.println(e.getStackTrace());
                        }
                    } else {
                        csvSourceMap.put(method, null);
                    }
                }
            }
        }

        Constructor<?>[] constructors = c.getConstructors();
        for (Constructor<?> constructor : constructors) {
//            System.out.println("~~~~~~~~~~~~~~~~");
//            System.out.println(constructor.getName());
//            System.out.println(Arrays.toString(constructor.getParameterTypes()));

            try {
                object = constructor.newInstance();
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }


        if (beforeSuiteList.size() == 1) {
            try {
                Method m = beforeSuiteList.get(0);
                m.invoke(object, csvSourceMap.get(m));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (beforeSuiteList.size() == 0) {
                System.out.println("Нет методов с аннотацией @BeforeSuite");
            } else {
                System.out.println("Более одного метода с аннотацией @BeforeSuite");
            }
        }

        for (Map.Entry<Integer, Method> entry : methodTreeMap.entrySet()) {
            Integer key = entry.getKey();
            Method value = entry.getValue();
//            System.out.println(key + " " + value);
            try {
                for (int i = 0; i < beforeTestList.size(); i++) {
                    Method m = beforeTestList.get(i);
                    m.invoke(object, csvSourceMap.get(m));
                }

                value.invoke(object, csvSourceMap.get(value));

                for (int i = 0; i < afterTestList.size(); i++) {
                    Method m = afterTestList.get(i);
                    m.invoke(object, csvSourceMap.get(m));
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        if (afterSuiteList.size() == 1) {
            try {
                Method m = afterSuiteList.get(0);
                m.invoke(object, csvSourceMap.get(m));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (afterSuiteList.size() == 0) {
                System.out.println("Нет методов с аннотацией @AfterSuite");
            } else {
                System.out.println("Более одного метода с аннотацией @AfterSuite");
            }
        }
    }
}
