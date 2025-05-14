package com.unigame.model;

import java.sql.*;

public class UserDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/progettoTSW";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "WANNACRY";

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public boolean validate(String username, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM utente WHERE username = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public boolean insert(User user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO utenti (username, email, password, indirizzo, cap, citta) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(5, user.getIndirizzo());
            stmt.setString(6, user.getCap());
            stmt.setString(7, user.getCitta());
            return stmt.executeUpdate() > 0;
        }
    }
}
