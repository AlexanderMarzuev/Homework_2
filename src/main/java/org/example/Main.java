package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService service = new UserService();
        boolean exit = false;
        while (!exit) {
            printMenu();
            System.out.print("Выберите действие (1-6): ");
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        service.createUser(scanner);
                        break;
                    case "2":
                        service.readUserById(scanner);
                        break;
                    case "3":
                        service.readAllUsers();
                        break;
                    case "4":
                        service.updateUser(scanner);
                        break;
                    case "5":
                        service.deleteUser(scanner);
                        break;
                    case "6":
                        exit = true;
                        System.out.println("Выход из приложения...");
                        break;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- МЕНЮ ---");
        System.out.println("1. Создать пользователя");
        System.out.println("2. Найти пользователя по ID");
        System.out.println("3. Показать всех пользователей");
        System.out.println("4. Обновить пользователя");
        System.out.println("5. Удалить пользователя по ID");
        System.out.println("6. Выход");
    }
}