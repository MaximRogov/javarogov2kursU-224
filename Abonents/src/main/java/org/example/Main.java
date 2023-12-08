package org.example;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Выберите команду:");
            System.out.println("1. Добавить абонента");
            System.out.println("2. Показать всех абонентов");
            System.out.println("3. Найти абонента по первому номеру телефона");
            System.out.println("4. Найти абонента по второму номеру телефона");
            System.out.println("5. Найти абонента по третьему номеру телефона");
            System.out.println("6. Найти абонента по имени и фамилии");
            System.out.println("7. Изменить абонента");
            System.out.println("8. Удалить абонента");
            System.out.println("0. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Введите фамилию:");
                    String surname = scanner.nextLine();
                    System.out.println("Введите Имя:");
                    String name = scanner.nextLine();
                    System.out.println("Введите Номер телефона:");
                    String phonenumber = scanner.nextLine();
                    DataBase.add(surname,name,phonenumber);
                    break;
                case 2:
                    List<Abonent> abonentList = DataBase.showAll();
                    for (Abonent abonent: abonentList){
                        System.out.println(abonent);
                    }
                    break;
                case 3:
                    System.out.println("Введите номер телефона:");
                    String phonenumber1 = scanner.nextLine();
                    List<Abonent> abonentList1 = DataBase.findNumberPhone1(phonenumber1);
                    for (Abonent abonent: abonentList1){
                        System.out.println(abonent);
                    }
                    break;
                case 4:
                    System.out.println("Введите номер телефона:");
                    String phonenumber2 = scanner.nextLine();
                    List<Abonent> abonentList2 = DataBase.findNumberPhone2(phonenumber2);
                    for (Abonent abonent: abonentList2){
                        System.out.println(abonent);
                    }
                    break;
                case 5:
                    System.out.println("Введите номер телефона:");
                    String phonenumber3 = scanner.nextLine();
                    List<Abonent> abonentList3 = DataBase.findNumberPhone3(phonenumber3);
                    for (Abonent abonent: abonentList3){
                        System.out.println(abonent);
                    }
                    break;
                case 6:
                    System.out.println("Введите фамилию:");
                    String surname1 = scanner.nextLine();
                    System.out.println("Введите Имя:");
                    String name1 = scanner.nextLine();
                    List<Abonent> abonentList4 = DataBase.findSurmameAndName(surname1, name1);
                    for (Abonent abonent: abonentList4){
                        System.out.println(abonent);
                    }
                    break;
                case 7:
                    System.out.println("Введите ID");
                    int id = scanner.nextInt();
                    System.out.println("Введите фамилию:");
                    String surname2 = scanner.nextLine();
                    System.out.println("Введите Имя:");
                    String name2 = scanner.nextLine();
                    System.out.println("Введите номер телефона:");
                    String phonenumber11 = scanner.nextLine();
                    System.out.println("Введите номер телефона:");
                    String phonenumber21 = scanner.nextLine();
                    System.out.println("Введите номер телефона:");
                    String phonenumber31 = scanner.nextLine();
                    DataBase.edit(id, surname2, name2, phonenumber11, phonenumber21, phonenumber31);
                    break;
                case 8:
                    System.out.println("Введите ID");
                    int id4 = scanner.nextInt();
                    DataBase.delete(id4);
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