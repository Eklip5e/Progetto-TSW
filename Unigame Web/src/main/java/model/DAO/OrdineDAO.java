package model.DAO;

import model.Ordine;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class OrdineDAO implements MetodiDAO<Ordine> {

    @Override
    public void doSave(Ordine ordine) {
        String sql = "INSERT INTO Ordine (data, stato, totale, idUtente, idFatturazione) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setTimestamp(1, new java.sql.Timestamp(ordine.getDataOrdine().getTime()));
            ps.setString(2, ordine.getStato());
            ps.setDouble(3, ordine.getTotale());
            ps.setInt(4, ordine.getIdUtente());
            ps.setInt(5, ordine.getIdFatturazione());

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new RuntimeException("Errore durante l'inserimento dell'ordine.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    ordine.setIdOrdine(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doDelete(int id) {
        String sql = "DELETE FROM Ordine WHERE idOrdine = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doUpdate(Ordine ordine, int id) {
        String sql = "UPDATE Ordine SET data = ?, stato = ?, totale = ?, idUtente = ?, idFatturazione = ? WHERE idOrdine = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setTimestamp(1, new Timestamp(ordine.getDataOrdine().getTime()));
            ps.setString(2, ordine.getStato());
            ps.setDouble(3, ordine.getTotale());
            ps.setInt(4, ordine.getIdUtente());
            ps.setInt(5, ordine.getIdFatturazione());
            ps.setInt(6, id);

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new RuntimeException("Errore durante l'aggiornamento dell'ordine.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Ordine doRetrieveById(int id) {
        String sql = "SELECT * FROM Ordine WHERE idOrdine = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Ordine ordine = new Ordine();
                    ordine.setIdOrdine(rs.getInt("idOrdine"));
                    ordine.setDataOrdine(rs.getTimestamp("data"));
                    ordine.setStato(rs.getString("stato"));
                    ordine.setTotale(rs.getDouble("totale"));
                    ordine.setIdUtente(rs.getInt("idUtente"));
                    ordine.setIdFatturazione(rs.getInt("idFatturazione"));
                    return ordine;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ordine> doRetrieveByIdUtente(int idUtente) {
        String sql = "SELECT * FROM Ordine WHERE idUtente = ?";
        List<Ordine> ordini = new ArrayList<>();
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUtente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Ordine ordine = new Ordine();
                    ordine.setIdOrdine(rs.getInt("idOrdine"));
                    ordine.setDataOrdine(rs.getTimestamp("data"));
                    ordine.setStato(rs.getString("stato"));
                    ordine.setTotale(rs.getDouble("totale"));
                    ordine.setIdUtente(rs.getInt("idUtente"));
                    ordine.setIdFatturazione(rs.getInt("idFatturazione"));
                    ordini.add(ordine);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordini;
    }
}
