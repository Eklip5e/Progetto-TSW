package model.DAO;

import model.DatiFatturazione;

import java.sql.*;

public class DatiFatturazioneDAO implements MetodiDAO<DatiFatturazione> {

    @Override
    public void doSave(DatiFatturazione datiFatturazione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO FATTURA (idUtente, via, citta, cap) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, datiFatturazione.getIdUtente());
            ps.setString(2, datiFatturazione.getVia());
            ps.setString(3, datiFatturazione.getCittà());
            ps.setString(4, datiFatturazione.getCap());

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
                    "UPDATE DATI_FATTURAZIONE SET idUtente = ?, via = ?, città = ?, cap = ?, numeroCarta = ?, dataScadenza = ?, cvv = ? WHERE idDatiFatturazione = ?");
            ps.setString(1, datiFatturazione.getIdUtente());
            ps.setString(2, datiFatturazione.getVia());
            ps.setString(3, datiFatturazione.getCittà());
            ps.setString(4, datiFatturazione.getCap());
            ps.setString(5, datiFatturazione.getNumeroCarta());
            ps.setDate(6, new Date(datiFatturazione.getDataScadenza().getTime()));

            ps.setInt(7, datiFatturazione.getCvv());
            ps.setInt(8, id);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("UPDATE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DatiFatturazione doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM DATI_FATTURAZIONE WHERE idDatiFatturazione = ?"
            );
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                DatiFatturazione df = new DatiFatturazione();
                df.setIdDatiFatturazione(rs.getInt("idDatiFatturazione"));
                df.setIdUtente(rs.getString("idUtente"));
                df.setVia(rs.getString("via"));
                df.setCittà(rs.getString("città"));
                df.setCap(rs.getString("cap"));
                df.setNumeroCarta(rs.getString("numeroCarta"));
                df.setDataScadenza(rs.getDate("dataScadenza"));
                df.setCvv(rs.getInt("cvv"));
                return df;
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
