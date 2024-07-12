package tech.inno;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import tech.inno.service.UserService;
import tech.inno.user.User;

import java.sql.SQLException;

@ComponentScan
public class MainApplication {
    public static void main(String[] args) throws SQLException {
        System.out.println("Main");
        var context = new AnnotationConfigApplicationContext(MainApplication.class);
        UserService userService = context.getBean(UserService.class);
        for (int i = 30; i < 35; i++) {
            userService.addUser(new User(Long.valueOf(i), "User_" + i));
        }
        userService.fetchAllUsers().stream().forEach(System.out::println);
        System.out.println("-----");
        User user = userService.fetchUserById(33L).get();
        System.out.println(user);
        System.out.println("-----");
        userService.deleteUser(user);
        System.out.println("-----");
        userService.fetchAllUsers().stream().forEach(System.out::println);
    }
}
