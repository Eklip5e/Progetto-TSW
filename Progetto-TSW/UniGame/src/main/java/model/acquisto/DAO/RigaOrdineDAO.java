package model.acquisto.DAO;

import model.DAO.ConPool;
import model.DAO.MetodiDAO;
import model.acquisto.RigaOrdine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RigaOrdineDAO implements MetodiDAO<RigaOrdine> {

    @Override
    public void doSave(RigaOrdine riga) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO RigaOrdine (idOrdine, idVideogioco, chiave, prezzoUnitario, quantità) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, riga.getIdOrdine());
            ps.setInt(2, riga.getIdVideogioco());
            ps.setString(3, riga.getChiave());
            ps.setDouble(4, riga.getPrezzoUnitario());
            ps.setInt(5, riga.getQuantità());

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new RuntimeException("Errore durante l'inserimento della riga ordine.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doDelete(int idOrdine) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM RigaOrdine WHERE idOrdine = ?");
            ps.setInt(1, idOrdine);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doUpdate(RigaOrdine riga, int idOrdine) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE RigaOrdine SET chiave = ?, prezzoUnitario = ?, quantità = ? WHERE idOrdine = ? AND idVideogioco = ?");
            ps.setString(1, riga.getChiave());
            ps.setDouble(2, riga.getPrezzoUnitario());
            ps.setInt(3, riga.getQuantità());
            ps.setInt(4, idOrdine);
            ps.setInt(5, riga.getIdVideogioco());

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new RuntimeException("Errore durante l'aggiornamento della riga ordine.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RigaOrdine doRetrieveById(int id) {
        // Non usato: chiave primaria è (idOrdine, idVideogioco)
        throw new UnsupportedOperationException("Metodo non supportato: usa doRetrieveByOrdineId.");
    }

    public List<RigaOrdine> doRetrieveByOrdineId(int idOrdine) {
        List<RigaOrdine> dettagli = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM RigaOrdine WHERE idOrdine = ?");
            ps.setInt(1, idOrdine);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RigaOrdine r = new RigaOrdine();
                    r.setIdOrdine(rs.getInt("idOrdine"));
                    r.setIdVideogioco(rs.getInt("idVideogioco"));
                    r.setChiave(rs.getString("chiave"));
                    r.setPrezzoUnitario(rs.getDouble("prezzoUnitario"));
                    r.setQuantità(rs.getInt("quantità"));
                    dettagli.add(r);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dettagli;
    }

    public List<RigaOrdine> doRetrieveByVideogiocoId(int idVideogioco) {
        List<RigaOrdine> dettagli = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM RigaOrdine WHERE idVideogioco = ?");
            ps.setInt(1, idVideogioco);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RigaOrdine r = new RigaOrdine();
                    r.setIdOrdine(rs.getInt("idOrdine"));
                    r.setIdVideogioco(rs.getInt("idVideogioco"));
                    r.setChiave(rs.getString("chiave"));
                    r.setPrezzoUnitario(rs.getDouble("prezzoUnitario"));
                    r.setQuantità(rs.getInt("quantità"));
                    dettagli.add(r);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dettagli;
    }
}
