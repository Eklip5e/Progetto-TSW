package model.DAO;

import model.Carrello;

import java.sql.*;

public class CarrelloDAO implements MetodiDAO<Carrello> {

    @Override
    public void doSave(Carrello carrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO CARRELLO (totale, idUser, idPromozione) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, carrello.getTotale());
            ps.setString(2, carrello.getIdUser());
            ps.setInt(3, carrello.getIdPromozione());
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
    public void doDelete(int idCarrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM CARRELLO WHERE idCarrello = ?");
            ps.setInt(1, idCarrello);
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
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE CARRELLO SET totale = ?, idUser = ?, idPromozione = ? WHERE idCarrello = id");
            ps.setDouble(1, carrello.getTotale());
            ps.setString(2, carrello.getIdUser());
            ps.setInt(3, carrello.getIdPromozione());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("UPDATE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
