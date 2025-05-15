package com.unigame.model.DAO;

import com.unigame.model.Fattura;

import java.sql.*;

public class FatturaDAO implements MetodiDAO<Fattura> {

    @Override
    public void doSave(Fattura fattura) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO FATTURA (idFatturazione) VALUES(?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, fattura.getIdFatturazione());
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
    public void doDelete(int idFatturazione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM FATTURA WHERE idFatturazione = ?");
            ps.setInt(1, idFatturazione);
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
                    "UPDATE FATTURA SET idFatturazione = ? WHERE idFatturazione = ?");
            ps.setInt(1, fattura.getIdFatturazione());
            ps.setInt(2, id);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("UPDATE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Fattura doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM FATTURA WHERE idFatturazione = ?"
            );
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Fattura fattura = new Fattura();
                fattura.setIdFatturazione(rs.getInt("idFatturazione"));
                return fattura;
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
