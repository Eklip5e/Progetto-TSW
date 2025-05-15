package com.unigame.model.DAO;

import com.unigame.model.Carrello;

import java.sql.*;

public class CarrelloDAO implements MetodiDAO<Carrello> {

    @Override
    public void doSave(Carrello carrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO CARRELLO (IDUser) VALUES(?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, carrello.getIdUser());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doDelete(int id) {
        // Non ha senso eliminare un carrello tramite ID, poiché è identificato da IDUser.
        // Modifichiamo il metodo per eliminare tramite IDUser.
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM CARRELLO WHERE IDUser = ?");
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("DELETE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doUpdate(Carrello carrello, int id) {
        // Non c'è nulla da aggiornare nel carrello, dato che è identificato solo da IDUser.
        // Questo metodo può essere lasciato vuoto o lanciare un'eccezione.
        throw new UnsupportedOperationException("Update not supported for Carrello.");
    }

    @Override
    public Carrello doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM CARRELLO WHERE IDUser = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Carrello carrello = new Carrello();
                carrello.setIdUser(rs.getString("IDUser"));
                return carrello;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}