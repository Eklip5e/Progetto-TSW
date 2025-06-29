package model.DAO;

import model.RigaCarrello;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RigaCarrelloDAO {

    public void doSave(RigaCarrello riga) {
        String sql = "INSERT INTO RigaCarrello (prezzoUnitario, quantità, idUtente, idVideogioco) VALUES (?, ?, ?, ?)";

        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, riga.getPrezzoUnitario());  // Aggiunto prezzoUnitario
            ps.setInt(2, riga.getQuantità());
            ps.setInt(3, riga.getIdUtente());
            ps.setInt(4, riga.getIdVideogioco());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<RigaCarrello> doRetrieveByUtenteId(int idUtente) {
        List<RigaCarrello> righe = new ArrayList<>();
        String sql = "SELECT idVideogioco, prezzoUnitario, quantità FROM RigaCarrello WHERE idUtente = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUtente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RigaCarrello riga = new RigaCarrello();
                riga.setIdUtente(idUtente);
                riga.setIdVideogioco(rs.getInt("idVideogioco"));
                riga.setPrezzoUnitario(rs.getDouble("prezzoUnitario"));
                riga.setQuantità(rs.getInt("quantità"));
                righe.add(riga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return righe;
    }

    public void doDeleteById(int idUtente) {
        String sql = "DELETE FROM RigaCarrello WHERE idUtente = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUtente);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doDeleteByIdVideogioco(int idVideogioco) {
        String sql = "DELETE FROM RigaCarrello WHERE idVideogioco = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVideogioco);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RigaCarrello doRetrieveByIdVideogioco(int idVideogioco) {
        String sql = "SELECT idVideogioco, SUM(quantità) AS totalQuantità FROM RigaCarrello WHERE idVideogioco = ? GROUP BY idVideogioco";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVideogioco);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                RigaCarrello riga = new RigaCarrello();
                riga.setIdVideogioco(idVideogioco);
                riga.setQuantità(rs.getInt("totalQuantità"));
                return riga;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean exists(int idUtente, int idVideogioco) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT 1 FROM rigaCarrello WHERE idUtente = ? AND idVideogioco = ?"
            );

            ps.setInt(1, idUtente);
            ps.setInt(2, idVideogioco);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void incrementaQuantita(int idUtente, int idVideogioco, double prezzoUnitario) {
        String sql = "UPDATE RigaCarrello SET quantità = quantità + 1, prezzoUnitario = ? WHERE idUtente = ? AND idVideogioco = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, prezzoUnitario);
            ps.setInt(2, idUtente);
            ps.setInt(3, idVideogioco);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void aggiornaQuantita(int idUtente, int idVideogioco, int quantita) {
        String sql = "UPDATE RigaCarrello SET quantità = ? WHERE idUtente = ? AND idVideogioco = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, quantita);
            ps.setInt(2, idUtente);
            ps.setInt(3, idVideogioco);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
