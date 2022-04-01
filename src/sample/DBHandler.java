package sample;

import java.sql.*;

public class DBHandler extends Config{
    Connection dbConnection;
    Statement statement;
    ResultSet result = null;

    //Установка соединения с бд
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public ResultSet loginUser(String login, String pass) throws SQLException, ClassNotFoundException {

        String querry = "SELECT * FROM users WHERE Email = '"+login+"' AND Password = '"+pass+"'";
        Statement statement = getDbConnection().createStatement();
        ResultSet resSet = statement.executeQuery(querry);
        return resSet;
    }
    public ResultSet querry(String querry) {
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(querry,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = preparedStatement.executeQuery(querry);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }


    public void addUser(String email, String password, String firstName, String lastName, int OfficeID, String BirthDate) {
        String request = "INSERT INTO Users (RoleID, Email, Password, firstName, LastName, OfficeID, BirthDate)VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(request);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, firstName);
            preparedStatement.setString(5, lastName);
            preparedStatement.setInt(6, OfficeID);
            preparedStatement.setString(7, BirthDate);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
