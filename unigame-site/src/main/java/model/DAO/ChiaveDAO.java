package model.DAO;

import model.Chiave;

import java.sql.*;

public class ChiaveDAO implements model.DAO.MetodiDAO<Chiave> {

    @Override
    public void doSave(Chiave chiave) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO CHIAVE (GameKey, Venduto, Riscattato, DataAttivazione, IDVideogioco) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, chiave.getGameKey());
            ps.setBoolean(2, chiave.isVenduto());
            ps.setBoolean(3, chiave.isRiscattato());
            ps.setDate(4, new java.sql.Date(chiave.getDataAttivazione().getTime()));
            ps.setInt(5, chiave.getIdVideogioco());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doDelete(int id) {
        // Modifico il metodo per eliminare tramite GameKey invece di un ID numerico.
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM CHIAVE WHERE GameKey = ?");
            ps.setString(1, String.valueOf(id)); // Convertiamo l'ID in una stringa per GameKey
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
                    "UPDATE CHIAVE SET Venduto = ?, Riscattato = ?, DataAttivazione = ?, IDVideogioco = ? WHERE GameKey = ?");
            ps.setBoolean(1, chiave.isVenduto());
            ps.setBoolean(2, chiave.isRiscattato());
            ps.setDate(3, new java.sql.Date(chiave.getDataAttivazione().getTime()));
            ps.setInt(4, chiave.getIdVideogioco());
            ps.setString(5, String.valueOf(id)); // Convertiamo l'ID in una stringa per GameKey
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("UPDATE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Chiave doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM CHIAVE WHERE GameKey = ?");
            ps.setString(1, String.valueOf(id)); // Convertiamo l'ID in una stringa per GameKey
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Chiave chiave = new Chiave();
                chiave.setGameKey(rs.getString("GameKey"));
                chiave.setVenduto(rs.getBoolean("Venduto"));
                chiave.setRiscattato(rs.getBoolean("Riscattato"));
                chiave.setDataAttivazione(rs.getDate("DataAttivazione"));
                chiave.setIdVideogioco(rs.getInt("IDVideogioco"));
                return chiave;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Database error during SELECT", e);
        }
    }
}