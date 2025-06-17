package com.unigame.model.DAO;

import com.unigame.model.Utente;

import java.sql.*;

public class UtenteDAO implements MetodiDAO<Utente> {

    @Override
    public void doSave(Utente utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Utente (admin, email, password, nome, cognome, dataDiNascita) VALUES(?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setBoolean(1, utente.isAdmin() != null ? utente.isAdmin() : false);
            ps.setString(2, utente.getEmail());
            ps.setString(3, utente.getPassword());
            ps.setString(4, utente.getNome());
            ps.setString(5, utente.getCognome());
            ps.setDate(6, new java.sql.Date(utente.getDataDiNascita().getTime()));

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new RuntimeException("Errore durante l'inserimento dell'utente.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    utente.setIdUtente(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doDelete(int idUtente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM Utente WHERE idUtente = ?"
            );
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
    public void doUpdate(Utente utente, int idUtente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Utente SET admin = ?, email = ?, password = ?, nome = ?, cognome = ?, dataDiNascita = ? WHERE idUtente = ?"
            );
            ps.setBoolean(1, utente.isAdmin());
            ps.setString(2, utente.getEmail());
            ps.setString(3, utente.getPassword());
            ps.setString(4, utente.getNome());
            ps.setString(5, utente.getCognome());
            ps.setDate(6, new java.sql.Date(utente.getDataDiNascita().getTime()));

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("UPDATE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Utente doRetrieveById(int idUtente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT idUtente, admin, email, password, nome, cognome, dataDiNascita FROM UTENTE WHERE idUtente = ?"
            );
            ps.setInt(1, idUtente);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Utente utente = new Utente();

                utente.setIdUtente(rs.getInt(1));
                utente.setAdmin(rs.getBoolean("admin"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setDataDiNascita(rs.getDate("dataDiNascita"));

                return utente;
            }
            return null; // Nessun utente trovato
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il recupero dell'utente con ID: " + idUtente, e);
        }
    }

    public Utente doRetrieveByEmail(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT idUtente, admin, email, password, nome, cognome, dataDiNascita FROM Utente WHERE email = ?"
            );
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            Utente utente = new Utente();
            if (rs.next()) {
                utente.setIdUtente(rs.getInt("idUtente"));
                utente.setAdmin(rs.getBoolean("admin"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setDataDiNascita(rs.getDate("dataDiNascita"));

                return utente;
            }
            return null; // Nessun utente trovato
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}