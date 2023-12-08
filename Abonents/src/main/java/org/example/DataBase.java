package org.example;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataBase {
    private final static String userName = "root";
    private final static String password = "30102023";
    private final static String connectionUrl = "jdbc:mysql://localhost:3306/new_schema";

    private static Connection connection;

    private static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(connectionUrl, userName, password);
        }
        return connection;
    }


    public static void add(String surname, String name, String phoneNumber) throws SQLException {
        if(!validatePhoneNumber(phoneNumber)){
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

            if (phoneNumber3 == null ) {
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

    public static List<Abonent> showAll() throws SQLException {
        String sql = "SELECT * FROM abonent order by surname, name, numberPhoneOne, numberPhoneTwo, numberPhoneThree";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Abonent> res = new LinkedList<>();
        while (resultSet.next()) {
            res.add(new Abonent(resultSet.getInt("idabonent"), resultSet.getString("surname"), resultSet.getString("name"), resultSet.getString("numberPhoneOne"), resultSet.getString("numberPhoneTwo"),resultSet.getString("numberPhoneThree") ));
        }
        return res;
    }

    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM abonent WHERE idabonent = ?";
        PreparedStatement statement4 = getConnection().prepareStatement(sql);
        statement4.setInt(1, id);
        statement4.execute();
        return;
    }

    public static void edit(int id, String surname, String name, String phonenumber1, String phonenumber2, String phonenumber3) throws SQLException {
        if(!validatePhoneNumber(phonenumber1) || !validatePhoneNumber(phonenumber2) || !validatePhoneNumber(phonenumber3)){
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

    public static boolean validatePhoneNumber(String phoneNumber) {
        String regex = "^(\\+\\d{1,3}[-]?)?\\d{10,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
    public static List<Abonent> findNumberPhone1(String numberOfPhone) throws SQLException {
        String sql = "SELECT * FROM abonent order by surname, name, numberPhoneOne, numberPhoneTwo, numberPhoneThree where numberPhoneOne = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1 , numberOfPhone);
        ResultSet resultSet = statement.executeQuery();
        List<Abonent> res = new LinkedList<>();
        while (resultSet.next()) {
            res.add(new Abonent(resultSet.getInt("idabonent"), resultSet.getString("surname"), resultSet.getString("name"), resultSet.getString("numberPhoneOne"), resultSet.getString("numberPhoneTwo"),resultSet.getString("numberPhoneThree") ));
        }
        return res;
    }
    public static List<Abonent> findNumberPhone2(String numberOfPhone) throws SQLException {
        String sql = "SELECT * FROM abonent order by surname, name, numberPhoneOne, numberPhoneTwo, numberPhoneThree where numberPhoneTwo = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1 , numberOfPhone);
        ResultSet resultSet = statement.executeQuery();
        List<Abonent> res = new LinkedList<>();
        while (resultSet.next()) {
            res.add(new Abonent(resultSet.getInt("idabonent"), resultSet.getString("surname"), resultSet.getString("name"), resultSet.getString("numberPhoneOne"), resultSet.getString("numberPhoneTwo"),resultSet.getString("numberPhoneThree") ));
        }
        return res;
    }
    public static List<Abonent> findNumberPhone3(String numberOfPhone) throws SQLException {
        String sql = "SELECT * FROM abonent order by surname, name, numberPhoneOne, numberPhoneTwo, numberPhoneThree where numberPhoneThree = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1 , numberOfPhone);
        ResultSet resultSet = statement.executeQuery();
        List<Abonent> res = new LinkedList<>();
        while (resultSet.next()) {
            res.add(new Abonent(resultSet.getInt("idabonent"), resultSet.getString("surname"), resultSet.getString("name"), resultSet.getString("numberPhoneOne"), resultSet.getString("numberPhoneTwo"),resultSet.getString("numberPhoneThree") ));
        }
        return res;
    }
    public static List<Abonent> findSurmameAndName(String surname, String name) throws SQLException {
        String sql = "SELECT * FROM abonent where surname = ? and name = ? order by surname, name, numberPhoneOne, numberPhoneTwo, numberPhoneThree";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1 , surname);
        statement.setString(2 , surname);
        ResultSet resultSet = statement.executeQuery();
        List<Abonent> res = new LinkedList<>();
        while (resultSet.next()) {
            res.add(new Abonent(resultSet.getInt("idabonent"), resultSet.getString("surname"), resultSet.getString("name"), resultSet.getString("numberPhoneOne"), resultSet.getString("numberPhoneTwo"),resultSet.getString("numberPhoneThree") ));
        }
        return res;
    }





}
