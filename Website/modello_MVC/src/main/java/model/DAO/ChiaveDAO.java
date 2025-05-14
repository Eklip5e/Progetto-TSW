package model.DAO;

import model.Chiave;

import java.sql.*;

public class ChiaveDAO implements MetodiDAO<Chiave> {

    @Override
    public void doSave(Chiave chiave) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO CHIAVE (stato, dataAttivazione, idVideogioco) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, chiave.isStato());
            ps.setDate(2, chiave.getDataAttivazione());
            ps.setInt(3, chiave.getIdVideogioco());
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
    public void doDelete(int idChiave) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM CHIAVE WHERE idChiave = ?");
            ps.setInt(1, idChiave);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("DELETE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doUpdate(Chiave chiave, int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE CHIAVE SET stato = ?, dataAttivazione = ?, idVideogioco = ? WHERE idChiave = id");
            ps.setBoolean(1, chiave.isStato());
            ps.setDate(2, chiave.getDataAttivazione());
            ps.setInt(3, chiave.getIdVideogioco());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("UPDATE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
