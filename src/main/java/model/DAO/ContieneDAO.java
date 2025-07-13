package model.DAO;

import model.Contiene;
import model.DAO.ConPool;

import java.sql.*;

public class ContieneDAO implements MetodiDAO<Contiene> {

    @Override
    public void doSave(Contiene contiene) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO CONTIENE (IDGioco, IDUser, Quantita, DataInserimento, Bloccato) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, contiene.getIdGioco());
            ps.setString(2, contiene.getIdUser());
            ps.setInt(3, contiene.getQuantita());
            ps.setDate(4, new Date(contiene.getDataInserimento().getTime()));
            ps.setBoolean(5, contiene.isBloccato());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doDelete(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM CONTIENE WHERE IDGioco = ? AND IDUser = ?");
            ps.setInt(1, id); // IDGioco
            ps.setString(2, ""); // IDUser (da specificare)
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("DELETE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doUpdate(Contiene contiene, int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE CONTIENE SET Quantita = ?, DataInserimento = ?, Bloccato = ? WHERE IDGioco = ? AND IDUser = ?");
            ps.setInt(1, contiene.getQuantita());
            ps.setDate(2, new Date(contiene.getDataInserimento().getTime()));
            ps.setBoolean(3, contiene.isBloccato());
            ps.setInt(4, contiene.getIdGioco());
            ps.setString(5, contiene.getIdUser());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("UPDATE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Contiene doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM CONTIENE WHERE IDGioco = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Contiene contiene = new Contiene();
                contiene.setIdGioco(rs.getInt("IDGioco"));
                contiene.setIdUser(rs.getString("IDUser"));
                contiene.setQuantita(rs.getInt("Quantita"));
                contiene.setDataInserimento(rs.getDate("DataInserimento"));
                contiene.setBloccato(rs.getBoolean("Bloccato"));
                return contiene;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}