package model.DAO;

import model.Carrello;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrelloDAO implements MetodiDAO<Carrello> {

    @Override
    public void doSave(Carrello carrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO CARRELLO (idUtente, idVideogioco) VALUES(?, ?)");
            ps.setInt(1, carrello.getIdUtente());
            ps.setInt(2, carrello.getIdVideogioco());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doDelete(int idUtente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM CARRELLO WHERE idUtente = ?");
            ps.setInt(1, idUtente);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doUpdate(Carrello carrello, int id) {
        throw new UnsupportedOperationException("Update not supported for Carrello.");
    }

    @Override
    public Carrello doRetrieveById(int idCarrello) {
        throw new UnsupportedOperationException("Retrieving single Carrello by idCarrello not supported.");
    }

    public List<Carrello> doRetrieveByUtenteId(int idUtente) {
        List<Carrello> carrello = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM CARRELLO WHERE idUtente = ?");
            ps.setInt(1, idUtente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Carrello carrelloItem = new Carrello();

                carrelloItem.setIdUtente(rs.getInt("idUtente"));
                carrelloItem.setIdVideogioco(rs.getInt("idVideogioco"));
                carrello.add(carrelloItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carrello;
    }
}