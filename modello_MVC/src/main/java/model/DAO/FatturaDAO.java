package model.DAO;

import model.Fattura;

import java.sql.*;

public class FatturaDAO implements MetodiDAO<Fattura> {

    @Override
    public void doSave(Fattura fattura) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO FATTURA (idDatiFatturazione) VALUES(?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, fattura.getIdDatiFatturazione());
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
    public void doDelete(int idFattura) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM FATTURA WHERE idFattura = ?");
            ps.setInt(1, idFattura);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("DELETE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doUpdate(Fattura fattura, int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE FATTURA SET idDatiFatturazione = ? WHERE idFattura = id");
            ps.setInt(1, fattura.getIdDatiFatturazione());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("UPDATE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
