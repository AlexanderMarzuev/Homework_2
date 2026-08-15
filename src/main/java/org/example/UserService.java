package org.example;

import java.util.List;
import java.util.Scanner;

public class UserService {

    private final UserRepo userRepo = new UserRepoImpl();

    public void createUser(Scanner scanner) {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();

        System.out.print("Введите email: ");
        String email = scanner.nextLine();

        System.out.print("Введите возраст: ");
        int age = Integer.parseInt(scanner.nextLine());

        User user = new User(name, email, age);
        userRepo.create(user);

        System.out.println("Пользователь успешно создан! ID: " + user.getId());
    }

    public User readUserById(Scanner scanner) {
        System.out.print("Введите ID пользователя для поиска: ");

        Long id = Long.parseLong(scanner.nextLine());
        User user = userRepo.findId(id);

        if (user != null) {
            System.out.println("Найден пользователь:" + user);

            return user;
        } else {
            System.out.println("Пользователь с таким ID не найден.");
            return null;
        }
    }

    public void deleteUser(Scanner scanner) {
        System.out.print("Введите ID пользователя для удаления: ");
        Long id = Long.parseLong(scanner.nextLine());
        userRepo.delete(id);
        System.out.println("Удаление выполнено.");
    }

    public void updateUser(Scanner scanner) {
        System.out.print("Введите id: ");
        Long id = Long.parseLong(scanner.nextLine());

        System.out.print("Введите имя: ");
        String name = scanner.nextLine();

        System.out.print("Введите email: ");
        String email = scanner.nextLine();

        System.out.print("Введите возраст: ");
        int age = Integer.parseInt(scanner.nextLine());

        User user = new User(name, email, age);
        user.setId(id);
        userRepo.update(user);

        System.out.println("Пользователь успешно изменен! ID: " + user.getId());
    }

    public List<User> readAllUsers() {

        return userRepo.findAll();
    }
}
