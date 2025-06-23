package model.DAO;

import model.Videogioco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideogiocoDAO implements MetodiDAO<Videogioco> {

    @Override
    public void doSave(Videogioco videogioco) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Videogioco (titolo, piattaforma, dataRilascio, descrizione, produttore, appIdSteam, prezzo, sconto) VALUES(?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, videogioco.getTitolo());
            ps.setString(2, videogioco.getPiattaforma());
            ps.setDate(3, new Date(videogioco.getDataRilascio().getTime()));
            ps.setString(4, videogioco.getDescrizione());
            ps.setString(5, videogioco.getProduttore());
            ps.setInt(6, videogioco.getAppIdSteam());
            ps.setDouble(7, videogioco.getPrezzo());
            ps.setInt(8, videogioco.getSconto());

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new RuntimeException("Errore durante l'inserimento del videogioco.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    videogioco.setIdVideogioco(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doDelete(int idGame) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM VIDEOGIOCO WHERE idVideogioco = ?");
            ps.setInt(1, idGame);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("DELETE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doUpdate(Videogioco videogioco, int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE VIDEOGIOCO SET titolo = ?, piattaforma = ?, dataRilascio = ?, descrizione = ?, prezzo = ?, sconto = ?, Produttore = ?, appIdSteam = ? WHERE idVideogioco = ?");
            ps.setString(1, videogioco.getTitolo());
            ps.setString(2, videogioco.getPiattaforma());
            ps.setDate(3, new Date(videogioco.getDataRilascio().getTime()));
            ps.setString(4, videogioco.getDescrizione());
            ps.setDouble(5, videogioco.getPrezzo());
            ps.setInt(6, videogioco.getSconto());
            ps.setString(7, videogioco.getProduttore());
            ps.setInt(8, videogioco.getAppIdSteam());
            ps.setInt(9, id);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("UPDATE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Videogioco doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM VIDEOGIOCO WHERE idVideogioco = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Videogioco videogioco = new Videogioco();
                videogioco.setIdVideogioco(rs.getInt("idVideogioco"));
                videogioco.setTitolo(rs.getString("titolo"));
                videogioco.setPiattaforma(rs.getString("piattaforma"));
                videogioco.setDataRilascio(rs.getDate("dataRilascio"));
                videogioco.setDescrizione(rs.getString("descrizione"));
                videogioco.setProduttore(rs.getString("produttore"));
                videogioco.setAppIdSteam(rs.getInt("appIdSteam"));
                videogioco.setPrezzo(rs.getDouble("prezzo"));
                videogioco.setSconto(rs.getInt("sconto"));
                return videogioco;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Videogioco> doRetrieveAll() {
        List<Videogioco> giochi = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM VIDEOGIOCO ORDER BY idVideogioco DESC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Videogioco gioco = new Videogioco();
                gioco.setIdVideogioco(rs.getInt("idVideogioco"));
                gioco.setTitolo(rs.getString("titolo"));
                gioco.setPiattaforma(rs.getString("piattaforma"));
                gioco.setDataRilascio(rs.getDate("dataRilascio"));
                gioco.setDescrizione(rs.getString("descrizione"));
                gioco.setProduttore(rs.getString("produttore"));
                gioco.setAppIdSteam(rs.getInt("appIdSteam"));
                gioco.setPrezzo(rs.getDouble("prezzo"));
                gioco.setSconto(rs.getInt("sconto"));
                giochi.add(gioco);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return giochi;
    }

    public List<Videogioco> doRetrieveByPiattaforma(String piattaforma) {
        List<Videogioco> giochi = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM VIDEOGIOCO WHERE piattaforma = ? ORDER BY idVideogioco DESC");
            ps.setString(1, piattaforma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Videogioco gioco = new Videogioco();
                gioco.setIdVideogioco(rs.getInt("idVideogioco"));
                gioco.setTitolo(rs.getString("titolo"));
                gioco.setPiattaforma(rs.getString("piattaforma"));
                gioco.setDataRilascio(rs.getDate("ReleaseDate"));
                gioco.setDescrizione(rs.getString("descrizione"));
                gioco.setProduttore(rs.getString("produttore"));
                gioco.setAppIdSteam(rs.getInt("appIdSteam"));
                gioco.setPrezzo(rs.getDouble("prezzo"));
                gioco.setSconto(rs.getInt("sconto"));
                giochi.add(gioco);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return giochi;
    }

    public List<Videogioco> doRetrieveByFiltro(String titolo, String piattaforma, String genere, Double prezzoMin, Double prezzoMax) throws SQLException {
        List<Videogioco> videogiochi = new ArrayList<>();

        StringBuilder query = new StringBuilder("SELECT * FROM Videogioco WHERE 1 = 1");

        if (titolo != null && !titolo.isEmpty()) {
            query.append(" AND titolo LIKE ?");
        }
        if (piattaforma  != null && !piattaforma.isEmpty()) {
            query.append(" AND piattaforma = ?");
        }
        if (genere != null && !genere.isEmpty()) {
            query.append(" AND genere = ?");
        }
        if (prezzoMin != null) {
            query.append(" AND prezzo >= ?");
        }
        if (prezzoMax != null) {
            query.append(" AND prezzo <= ?");
        }

        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query.toString())) {

            int i = 1;
            if (titolo != null && !titolo.isEmpty()) {
                ps.setString(i++, "%" + titolo + "%");
            }
            if (piattaforma != null && !piattaforma.isEmpty()) {
                ps.setString(i++, piattaforma);
            }
            if (genere != null && !genere.isEmpty()) {
                ps.setString(i++, genere);
            }
            if (prezzoMin != null) {
                ps.setDouble(i++, prezzoMin);
            }
            if (prezzoMax != null) {
                ps.setDouble(i++, prezzoMax);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Videogioco videogioco = new Videogioco();

                    videogioco.setIdVideogioco(rs.getInt("idVideogioco"));
                    videogioco.setTitolo(rs.getString("titolo"));
                    videogioco.setPiattaforma(rs.getString("piattaforma"));
                    videogioco.setDataRilascio(rs.getDate("dataRilascio"));
                    videogioco.setDescrizione(rs.getString("descrizione"));
                    videogioco.setProduttore(rs.getString("produttore"));
                    videogioco.setAppIdSteam(rs.getInt("appIdSteam"));
                    videogioco.setPrezzo(rs.getDouble("prezzo"));
                    videogioco.setSconto(rs.getInt("sconto"));

                    videogiochi.add(videogioco);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il recupero dei videogiochi filtrati", e);
        }

        return videogiochi;
    }
}
