package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Alex","Ivanjv",(byte) 10);
        userService.saveUser("Nike","Sidorov",(byte) 20);
        userService.saveUser("Panyy","Petrov",(byte) 30);
        userService.saveUser("Elena","Rukova",(byte) 40);

        userService.removeUserById(1L);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
