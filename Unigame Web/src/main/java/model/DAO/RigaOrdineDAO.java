package model.DAO;

import model.RigaOrdine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RigaOrdineDAO implements MetodiDAO<RigaOrdine> {

    @Override
    public void doSave(RigaOrdine riga) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO RigaOrdine (idOrdine, idVideogioco, chiave, prezzoUnitario) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, riga.getIdOrdine());
            ps.setInt(2, riga.getIdVideogioco());
            ps.setString(3, riga.getChiave());
            ps.setDouble(4, riga.getPrezzoUnitario());

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new RuntimeException("Errore durante l'inserimento della riga ordine.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    riga.setIdRigaOrdine(generatedKeys.getInt(1));
                } else {
                    throw new RuntimeException("Errore, nessun ID generato per la riga ordine.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doDelete(int idOrdine) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM RigaOrdine WHERE idOrdine = ?");
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
                    "UPDATE RigaOrdine SET chiave = ?, prezzoUnitario = ? WHERE idRigaOrdine = ?");
            ps.setString(1, riga.getChiave());
            ps.setDouble(2, riga.getPrezzoUnitario());
            ps.setInt(3, riga.getIdRigaOrdine());

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new RuntimeException("Errore durante l'aggiornamento della riga ordine.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Il metodo doRetrieveById ora prende idRigaOrdine, non idOrdine
    @Override
    public RigaOrdine doRetrieveById(int idRigaOrdine) {
        RigaOrdine riga = null;
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM RigaOrdine WHERE idRigaOrdine = ?");
            ps.setInt(1, idRigaOrdine);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    riga = new RigaOrdine();
                    riga.setIdRigaOrdine(rs.getInt("idRigaOrdine"));
                    riga.setIdOrdine(rs.getInt("idOrdine"));
                    riga.setIdVideogioco(rs.getInt("idVideogioco"));
                    riga.setChiave(rs.getString("chiave"));
                    riga.setPrezzoUnitario(rs.getDouble("prezzoUnitario"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return riga;
    }

    public List<RigaOrdine> doRetrieveByOrdineId(int idOrdine) {
        List<RigaOrdine> dettagli = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM RigaOrdine WHERE idOrdine = ?");
            ps.setInt(1, idOrdine);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RigaOrdine r = new RigaOrdine();
                    r.setIdRigaOrdine(rs.getInt("idRigaOrdine"));
                    r.setIdOrdine(rs.getInt("idOrdine"));
                    r.setIdVideogioco(rs.getInt("idVideogioco"));
                    r.setChiave(rs.getString("chiave"));
                    r.setPrezzoUnitario(rs.getDouble("prezzoUnitario"));
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
            PreparedStatement ps = con.prepareStatement("SELECT * FROM RigaOrdine WHERE idVideogioco = ?");
            ps.setInt(1, idVideogioco);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RigaOrdine r = new RigaOrdine();
                    r.setIdRigaOrdine(rs.getInt("idRigaOrdine"));
                    r.setIdOrdine(rs.getInt("idOrdine"));
                    r.setIdVideogioco(rs.getInt("idVideogioco"));
                    r.setChiave(rs.getString("chiave"));
                    r.setPrezzoUnitario(rs.getDouble("prezzoUnitario"));
                    dettagli.add(r);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dettagli;
    }

    public RigaOrdine doRetrieveByOrdineIdEVideogiocoId(int idOrdine, int idVideogioco) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM RigaOrdine WHERE idOrdine = ? AND idVideogioco = ?"
            );
            ps.setInt(1, idOrdine);
            ps.setInt(2, idVideogioco);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    RigaOrdine r = new RigaOrdine();
                    r.setIdRigaOrdine(rs.getInt("idRigaOrdine"));
                    r.setIdOrdine(rs.getInt("idOrdine"));
                    r.setIdVideogioco(rs.getInt("idVideogioco"));
                    r.setChiave(rs.getString("chiave"));
                    r.setPrezzoUnitario(rs.getDouble("prezzoUnitario"));
                    return r;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}