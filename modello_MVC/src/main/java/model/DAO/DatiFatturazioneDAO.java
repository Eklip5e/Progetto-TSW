package model.DAO;

import model.DatiFatturazione;

import java.sql.*;

public class DatiFatturazioneDAO implements MetodiDAO<DatiFatturazione> {

    @Override
    public void doSave(DatiFatturazione datiFatturazione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO DATI_FATTURAZIONE (idDatiFatturazione, idUtente, via, città, cap, numeroCarta, dataScadenza, cvv) VALUES(?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, datiFatturazione.getIdDatiFatturazione());
            ps.setString(2, datiFatturazione.getIdUtente());
            ps.setString(3, datiFatturazione.getVia());
            ps.setString(4, datiFatturazione.getCittà());
            ps.setString(5, datiFatturazione.getCap());
            ps.setString(6, datiFatturazione.getNumeroCarta());
            ps.setDate(7, datiFatturazione.getDataScadenza());
            ps.setInt(8, datiFatturazione.getCvv());
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
    public void doDelete(int idDatiFatturazione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM DATI_FATTURAZIONE WHERE idDatiFatturazione = ?");
            ps.setInt(1, idDatiFatturazione);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("DELETE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doUpdate(DatiFatturazione datiFatturazione, int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE DATI_FATTURAZIONE SET idUtente = ?, via = ?, città = ?, cap = ?, numeroCarta = ?, dataScadenza = ?, cvv = ? WHERE idDatiFatturazione = id");
            ps.setString(1, datiFatturazione.getIdUtente());
            ps.setString(2, datiFatturazione.getVia());
            ps.setString(3, datiFatturazione.getCittà());
            ps.setString(4, datiFatturazione.getCap());
            ps.setString(5, datiFatturazione.getNumeroCarta());
            ps.setDate(6, datiFatturazione.getDataScadenza());
            ps.setInt(7, datiFatturazione.getCvv());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("UPDATE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
