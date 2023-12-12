package org.example;

import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        DataBaseController controller = new DataBaseController();
        while (isRunning) {
            System.out.println("Выберите команду:");
            System.out.println("1. Добавить абонента");
            System.out.println("2. Показать всех абонентов");
            System.out.println("3. Найти абонента по номеру телефона");
            System.out.println("4. Найти абонента по имени и фамилии");
            System.out.println("5. Изменить абонента");
            System.out.println("6. Удалить абонента");
            System.out.println("0. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    controller.addAbonent(scanner);
                    break;
                case 2:
                    controller.showAllAbonents(scanner);
                    break;
                case 3:
                    controller.findAbonentNumber(scanner);
                    break;
                case 4:
                    controller.findAbonentName(scanner);
                    break;
                case 5:
                    controller.changeAbonent(scanner);
                    break;
                case 6:
                    controller.delete(scanner);
                    break;
                case 0:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Некорректная команда. Пожалуйста, выберите снова.");
            }
        }
    }
}