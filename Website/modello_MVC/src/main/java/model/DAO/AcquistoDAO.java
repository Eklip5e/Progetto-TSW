package model.DAO;

import model.Acquisto;

import java.sql.*;

public class AcquistoDAO implements MetodiDAO<Acquisto> {


    @Override
    public void doSave(Acquisto acquisto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ACQUISTO (dataAcquisto, idUtente, idCarrello, idFattura) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, acquisto.getDataAcquisto());
            ps.setString(2, acquisto.getIdUtente());
            ps.setInt(3, acquisto.getIdCarrello());
            ps.setInt(4, acquisto.getIdFattura());
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
    public void doDelete(int idAquisto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM ACQUISTO WHERE idAcquisto = ?");
            ps.setInt(1, idAquisto);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("DELETE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doUpdate(Acquisto acquisto, int idAcquisto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE ACQUISTO SET dataAcquisto = ?, idUtente = ?, idCarrello = ?, idFattura = ? WHERE idAcquisto = idAcquisto");
            ps.setDate(1, acquisto.getDataAcquisto());
            ps.setString(2, acquisto.getIdUtente());
            ps.setInt(3, acquisto.getIdCarrello());
            ps.setInt(4, acquisto.getIdFattura());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("UPDATE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Acquisto doRetrieveById(int id) {
        return null;
    }
}
