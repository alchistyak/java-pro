import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.*;

public class MainApplication {
    public static void main(String[] args) {
        TestRunner.runTests(TestAnimal.class); // TestAnimal - класс обертка для тестируемого класса Animal
    }
}
