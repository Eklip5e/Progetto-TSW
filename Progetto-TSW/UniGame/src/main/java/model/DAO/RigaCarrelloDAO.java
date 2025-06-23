package model.DAO;

import model.RigaCarrello;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RigaCarrelloDAO {

    public void doSave(RigaCarrello riga) {
        String sql = "INSERT INTO RigaCarrello (idUtente, idVideogioco) VALUES (?, ?)";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, riga.getIdUtente());
            ps.setInt(2, riga.getIdVideogioco());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<RigaCarrello> doRetrieveByUtenteId(int idUtente) {
        List<RigaCarrello> righe = new ArrayList<>();
        String sql = "SELECT * FROM RigaCarrello WHERE idUtente = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUtente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RigaCarrello riga = new RigaCarrello();
                riga.setIdRiga(rs.getInt("idRiga"));
                riga.setIdUtente(rs.getInt("idUtente"));
                riga.setIdVideogioco(rs.getInt("idVideogioco"));
                righe.add(riga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return righe;
    }

    public void doDeleteById(int idVideogioco) {
        String sql = "DELETE FROM RigaCarrello WHERE idVideogioco = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVideogioco);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void incrementaQuantita(int idUtente, int idVideogioco) {
        String sql = "UPDATE RigaCarrello SET quantita = quantita + 1 WHERE idUtente = ? AND idVideogioco = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUtente);
            ps.setInt(2, idVideogioco);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
