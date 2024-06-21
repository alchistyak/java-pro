import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainApplication {
    public static void main(String[] args) {
        // Удаление из листа всех дубликатов
        List.of("a", "ab", "a", "abc", "ab", "abcd", "abc")
                .stream()
                .distinct()
                .forEach(System.out::println);
        System.out.println("---------------------------");
        // Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 64 9 4 3 22 1 13 => 13)
        List.of(5, 2, 10, 64, 9, 4, 3, 22, 1, 13)
                .stream()
                .sorted(Comparator.reverseOrder())
                .skip(2).limit(1)
                .forEach(System.out::println);
        System.out.println("---------------------------");
        // Найдите в списке целых чисел 3-е наибольшее «уникальное» число (пример: 5 2 10 9 4 3 10 1 13 => 9)
        List.of(5, 2, 10, 9, 4, 3, 10, 1, 13)
                .stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(2).limit(1)
                .forEach(System.out::println);
        System.out.println("---------------------------");
        // Имеется список объектов типа Сотрудник (имя, возраст, должность)
        List<Employee> employees = List.of(
                new Employee("Иванов Иван Иванович", 54, Position.ENGINEER),
                new Employee("Ухов Максим Семенович", 22, Position.MANAGER),
                new Employee("Иванова Ирина Ивановна", 36, Position.SECURITY),
                new Employee("Петров Петр Петрович", 62, Position.DIRECTOR),
                new Employee("Стулов Илья Федорович", 38, Position.ENGINEER),
                new Employee("Тихонов Тихон Евгеньевич", 44, Position.ENGINEER),
                new Employee("Смирнов Артем Викторович", 66, Position.ENGINEER)
        );
        // Необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста
        employees
                .stream()
                .filter(employee -> employee.getPosition() == Position.ENGINEER)
                .sorted(Comparator.comparing(Employee::getAge).reversed())
                .limit(3)
                .map(Employee::getName)
                .forEach(System.out::println);
        System.out.println("---------------------------");
        // Посчитайте средний возраст сотрудников с должностью «Инженер»
        System.out.println(employees
                .stream()
                .filter(employee -> employee.getPosition() == Position.ENGINEER)
                .mapToInt(Employee::getAge)
                .average()
                .getAsDouble());
        System.out.println("---------------------------");
        // Найдите в списке слов самое длинное
        System.out.println(List.of("a", "ab", "a", "abc", "ab", "abcd", "abc")
                .stream()
                .max(Comparator.comparingInt(String::length))
                .get()
        );
        System.out.println("---------------------------");
        // Имеется строка с набором слов в нижнем регистре, разделенных пробелом.
        // Постройте хеш-мапы, в которой будут хранится пары: слово - сколько раз оно встречается во входной строке
        String str = "a ab a abc ab abcd abc";
        Arrays.stream(str.split(" "))
                .collect(Collectors.toMap(String::toLowerCase, i -> 1, Integer::sum))
                .forEach((key, value) -> System.out.println(key + " " + value));
        System.out.println("---------------------------");
        // Отпечатайте в консоль строки из списка в порядке увеличения длины слова
        // Если слова имеют одинаковую длину, то должен быть сохранен алфавитный порядок
        List.of("a", "ab", "a", "cba", "ba", "abcd", "abc")
                .stream()
                .sorted(Comparator.comparing(String::length).thenComparing(String::compareTo))
                .forEach(System.out::println);
        System.out.println("---------------------------");
        // Имеется массив строк, в каждой из которых лежит набор из 5 строк, разделенных пробелом
        // Найдите среди всех слов самое длинное, если таких слов несколько, получите любое из них
        System.out.println(List.of("Второе задание гораздо проще первого", "Хорошие задания по Stream API", "Замечательный курс по Java Pro")
                .stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .max(Comparator.comparing(String::length))
                .get());
    }
}
