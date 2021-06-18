package Architect.mapper;

import java.sql.*;

public class SQLClient {

    private static Connection connection;
    private static Statement statement;

    public synchronized static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:chat-server/chat.db");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized static String getNickname(Long id) {
        String query = String.format("SELECT nickname FROM users WHERE id = '%d'", id);
        try (ResultSet set = statement.executeQuery(query)) {
            if (set.next())
                return set.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public synchronized static boolean setNewNickName(Long id, String newNickname) {
        String query = String.format("UPDATE users SET nickname = '%d' WHERE login = '%s'", id, newNickname);
        int i;
        try {
            i = statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i != 0;
    }
}