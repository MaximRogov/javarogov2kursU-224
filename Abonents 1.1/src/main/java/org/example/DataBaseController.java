package org.example;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.Database.getConnection;

public class DataBaseController {

    private void add(String surname, String name, String phoneNumber) throws SQLException {
        if (!validatePhoneNumber(phoneNumber)) {
            return;
        }
        String sql = "SELECT * FROM abonent WHERE surname = ? and name = ?";
        PreparedStatement statement1 = getConnection().prepareStatement(sql);
        statement1.setString(1, surname);
        statement1.setString(2, name);
        ResultSet resultSet = statement1.executeQuery();
        if (resultSet.next()) {
            System.out.println(true);
            int id = resultSet.getInt("idabonent");
            String currName = resultSet.getString("Surname");
            String currSurname = resultSet.getString("Surname");
            String phoneNumber1 = resultSet.getString("numberPhoneOne");
            String phoneNumber2 = resultSet.getString("numberPhoneTwo");
            String phoneNumber3 = resultSet.getString("numberPhoneThree");
            if (phoneNumber1 == null) {
                sql = "UPDATE abonent SET numberPhoneTwo = ? WHERE idabonent = ?";
                PreparedStatement statement2 = getConnection().prepareStatement(sql);
                statement2.setString(1, phoneNumber);
                statement2.setInt(2, id);
                statement2.execute();
                return;
            }
            if (phoneNumber2 == null) {
                sql = "UPDATE abonent SET numberPhoneTwo = ? WHERE idabonent = ?";
                PreparedStatement statement2 = getConnection().prepareStatement(sql);
                statement2.setString(1, phoneNumber);
                statement2.setInt(2, id);
                statement2.execute();
                return;
            }

            if (phoneNumber3 == null) {
                sql = "UPDATE abonent SET numberPhoneThree = ? WHERE idabonent = ?";
                PreparedStatement statement2 = getConnection().prepareStatement(sql);
                statement2.setString(1, phoneNumber);
                statement2.setInt(2, id);
                statement2.execute();
                return;
            }

        } else {
            sql = "INSERT INTO abonent ( surname, name, numberPhoneOne) VALUES ( ?, ?, ?)";
            PreparedStatement statement3 = getConnection().prepareStatement(sql);
            statement3.setString(1, surname);
            statement3.setString(2, name);
            statement3.setString(3, phoneNumber);
            statement3.execute();
        }
    }

    public List<Abonent> showAll() throws SQLException {
        String sql = "SELECT * FROM abonent order by surname, name, numberPhoneOne, numberPhoneTwo, numberPhoneThree";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Abonent> res = new LinkedList<>();
        while (resultSet.next()) {
            res.add(new Abonent(resultSet.getInt("idabonent"), resultSet.getString("surname"), resultSet.getString("name"), resultSet.getString("numberPhoneOne"), resultSet.getString("numberPhoneTwo"), resultSet.getString("numberPhoneThree")));
        }
        return res;
    }

    private void delete(int id) throws SQLException {
        String sql = "DELETE FROM abonent WHERE idabonent = ?";
        PreparedStatement statement4 = getConnection().prepareStatement(sql);
        statement4.setInt(1, id);
        statement4.execute();
        return;
    }

    private void edit(int id, String surname, String name, String phonenumber1, String phonenumber2, String phonenumber3) throws SQLException {
        if (!validatePhoneNumber(phonenumber1) || !validatePhoneNumber(phonenumber2) || !validatePhoneNumber(phonenumber3)) {
            return;
        }
        String sql = "UPDATE abonent SET surname = ? , name = ?, numberPhoneOne = ?, numberPhoneTwo = ?, numberPhoneThree = ? WHERE idabonent = ?";
        PreparedStatement statement1 = getConnection().prepareStatement(sql);
        statement1.setString(1, surname);
        statement1.setString(2, name);
        statement1.setString(3, phonenumber1);
        statement1.setString(4, phonenumber2);
        statement1.setString(5, phonenumber3);
        statement1.setInt(6, id);
        statement1.execute();
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        String regex = "^(\\+\\d{1,3}[-]?)?\\d{10,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    private List<Abonent> findNumberPhone(String numberOfPhone) throws SQLException {
        String sql = "SELECT * FROM abonent where numberPhoneOne = ? order by surname, name, numberPhoneOne, numberPhoneTwo, numberPhoneThree ";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1, numberOfPhone);
        ResultSet resultSet = statement.executeQuery();
        List<Abonent> res = new LinkedList<>();
        while (resultSet.next()) {
            res.add(new Abonent(resultSet.getInt("idabonent"), resultSet.getString("surname"), resultSet.getString("name"), resultSet.getString("numberPhoneOne"), resultSet.getString("numberPhoneTwo"), resultSet.getString("numberPhoneThree")));
        }
        if (res.size() != 0) {
            return res;
        }
        sql = "SELECT * FROM abonent where numberPhoneTwo = ? order by surname, name, numberPhoneOne, numberPhoneTwo, numberPhoneThree ";
        statement = getConnection().prepareStatement(sql);
        statement.setString(1, numberOfPhone);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            res.add(new Abonent(resultSet.getInt("idabonent"), resultSet.getString("surname"), resultSet.getString("name"), resultSet.getString("numberPhoneOne"), resultSet.getString("numberPhoneTwo"), resultSet.getString("numberPhoneThree")));
        }
        if (res.size() != 0) {
            return res;
        }
        sql = "SELECT * FROM abonent where numberPhoneThree = ? order by surname, name, numberPhoneOne, numberPhoneTwo, numberPhoneThree";
        statement = getConnection().prepareStatement(sql);
        statement.setString(1, numberOfPhone);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            res.add(new Abonent(resultSet.getInt("idabonent"), resultSet.getString("surname"), resultSet.getString("name"), resultSet.getString("numberPhoneOne"), resultSet.getString("numberPhoneTwo"), resultSet.getString("numberPhoneThree")));
        }
        return res;

    }

    private List<Abonent> findSurmameAndName(String surname, String name) throws SQLException {
        String sql = "SELECT * FROM abonent where surname = ? and name = ? order by surname, name, numberPhoneOne, numberPhoneTwo, numberPhoneThree";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1, surname);
        statement.setString(2, surname);
        ResultSet resultSet = statement.executeQuery();
        List<Abonent> res = new LinkedList<>();
        while (resultSet.next()) {
            res.add(new Abonent(resultSet.getInt("idabonent"), resultSet.getString("surname"), resultSet.getString("name"), resultSet.getString("numberPhoneOne"), resultSet.getString("numberPhoneTwo"), resultSet.getString("numberPhoneThree")));
        }
        return res;
    }
    public void addAbonent(Scanner scanner){
        System.out.println("Введите фамилию:");
        String surname = scanner.nextLine();
        System.out.println("Введите Имя:");
        String name = scanner.nextLine();
        System.out.println("Введите Номер телефона:");
        String phonenumber = scanner.nextLine();
        try{
            add(surname,name,phonenumber);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public  void  findAbonentNumber(Scanner scanner){
        System.out.println("Введите номер телефона:");
        String phonenumber1 = scanner.nextLine();
        try {
            List<Abonent> abonentList1 = findNumberPhone(phonenumber1);
            for (Abonent abonent: abonentList1){
                System.out.println(abonent);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void findAbonentName(Scanner scanner){
        System.out.println("Введите фамилию:");
        String surname1 = scanner.nextLine();
        System.out.println("Введите Имя:");
        String name1 = scanner.nextLine();
        try {
            List<Abonent> abonentList4 = findSurmameAndName(surname1, name1);
            for (Abonent abonent : abonentList4) {
                System.out.println(abonent);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public  void changeAbonent(Scanner scanner){
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
        try {
            edit(id, surname2, name2, phonenumber11, phonenumber21, phonenumber31);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void showAllAbonents(Scanner scanner){
        try {
            List<Abonent> abonentList = showAll();
            for (Abonent abonent : abonentList) {
                System.out.println(abonent);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void  delete(Scanner scanner){
        System.out.println("Введите ID");
        int id4 = scanner.nextInt();
        try {
            delete(id4);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
