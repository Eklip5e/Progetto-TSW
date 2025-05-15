package com.unigame.model.DAO;

import com.unigame.model.Utente;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class UtenteDAO implements MetodiDAO<Utente> {

    @Override
    public void doSave(Utente utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO UTENTE (email, username, nome, cognome, password) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(2, utente.getUsername());
            ps.setString(3, utente.getNome());
            ps.setString(4, utente.getCognome());
            ps.setString(1, utente.getEmail());
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
                    "UPDATE UTENTE SET username = ?, nome = ?, cognome = ?, email = ?, password = ? WHERE IDUtente = ?");
            ps.setString(2, utente.getUsername());
            ps.setString(3, utente.getNome());
            ps.setString(4, utente.getCognome());
            ps.setString(1, utente.getEmail());
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
                    "SELECT utente.IDUtente, username, nome, cognome, email, password FROM UTENTE WHERE IDUtente = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Utente utente = new Utente();
                utente.setIdUtente(rs.getInt("idUtente"));
                utente.setUsername(rs.getString("username"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
                return utente;
            }

            return null; // Nessun utente trovato con l'id fornito
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveByUsernameAndPassword(String username, String password) {
        try (Connection con = ConPool.getConnection()) {
            // Hashtag la password prima di inviarla alla query
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            String hashedPassword = String.format("%040x", new BigInteger(1, digest.digest()));

            PreparedStatement ps = con.prepareStatement(
                    "SELECT IDUtente, username, nome, cognome, email, password FROM UTENTE WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, hashedPassword);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Utente utente = new Utente();
                utente.setIdUtente(rs.getInt("idUtente"));
                utente.setUsername(rs.getString("username"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
                return utente;
            }

            return null;

        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
