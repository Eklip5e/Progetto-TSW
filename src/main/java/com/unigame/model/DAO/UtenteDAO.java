package com.unigame.model.DAO;

import com.unigame.model.Utente;

import java.sql.*;

public class UtenteDAO implements MetodiDAO<Utente> {

    @Override
    public void doSave(Utente utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO UTENTE (email, username, nome, cognome, password) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, utente.getEmail());
            ps.setString(2, utente.getUsername());
            ps.setString(3, utente.getNome());
            ps.setString(4, utente.getCognome());
            ps.setString(5, utente.getPassword());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doDelete(int idUtente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM UTENTE WHERE IDUtente = ?");
            ps.setInt(1, idUtente);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("DELETE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doUpdate(Utente utente, int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE UTENTE SET email = ?, username = ?, nome = ?, cognome = ?, password = ? WHERE IDUtente = ?");
            ps.setString(1, utente.getEmail());
            ps.setString(2, utente.getUsername());
            ps.setString(3, utente.getNome());
            ps.setString(4, utente.getCognome());
            ps.setString(5, utente.getPassword());
            ps.setInt(6, id);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("UPDATE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Utente doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT IDUtente, username, nome, cognome, email, password FROM UTENTE WHERE IDUtente = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Utente utente = new Utente();
                utente.setIdUtente(rs.getInt("IDUtente"));
                utente.setUsername(rs.getString("username"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
                return utente;
            }
            return null; // Nessun utente trovato
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveByUsername(String username) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT IDUtente, username, nome, cognome, email, password, IsAdmin FROM UTENTE WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Utente utente = new Utente();
                utente.setIdUtente(rs.getInt("IDUtente"));
                utente.setUsername(rs.getString("username"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password")); // Password hashata
                utente.setAdmin(rs.getBoolean("isAdmin"));
                return utente;
            }
            return null; // Nessun utente trovato
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}