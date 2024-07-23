package tech.inno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ComponentScan
//public class MainApplication {
//    public static void main(String[] args) throws SQLException {
//        System.out.println("Main");
//        var context = new AnnotationConfigApplicationContext(MainApplication.class);
//        UserService userService = context.getBean(UserService.class);
//        for (int i = 30; i < 35; i++) {
//            userService.addUser(new User(Long.valueOf(i), "User_" + i));
//        }
//        userService.fetchAllUsers().stream().forEach(System.out::println);
//        System.out.println("-----");
//        User user = userService.fetchUserById(33L).get();
//        System.out.println(user);
//        System.out.println("-----");
//        userService.deleteUser(user);
//        System.out.println("-----");
//        userService.fetchAllUsers().stream().forEach(System.out::println);
//    }
//}

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
