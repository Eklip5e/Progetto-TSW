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
                    "INSERT INTO Videogioco (titolo, piattaforma, dataRilascio, descrizione, produttore, genere, appIdSteam, prezzo, sconto) VALUES(?,?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, videogioco.getTitolo());
            ps.setString(2, videogioco.getPiattaforma());
            ps.setDate(3, new Date(videogioco.getDataRilascio().getTime()));
            ps.setString(4, videogioco.getDescrizione());
            ps.setString(5, videogioco.getProduttore());
            ps.setString(6, videogioco.getGenere());
            ps.setInt(7, videogioco.getAppIdSteam());
            ps.setDouble(8, videogioco.getPrezzo());
            ps.setInt(9, videogioco.getSconto());

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
    public void doDelete(int idVideogioco) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Videogioco SET stato = FALSE WHERE idVideogioco = ?"
            );
            ps.setInt(1, idVideogioco);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("UPDATE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Attiva(int idVideogioco) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Videogioco SET stato = TRUE WHERE idVideogioco = ?"
            );
            ps.setInt(1, idVideogioco);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("UPDATE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doUpdate(Videogioco videogioco, int idVideogioco) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Videogioco SET titolo = ?, piattaforma = ?, dataRilascio = ?, descrizione = ?, prezzo = ?, sconto = ?, produttore = ?, appIdSteam = ?, genere = ? WHERE idVideogioco = ?");
            ps.setString(1, videogioco.getTitolo());
            ps.setString(2, videogioco.getPiattaforma());
            ps.setDate(3, new Date(videogioco.getDataRilascio().getTime()));
            ps.setString(4, videogioco.getDescrizione());
            ps.setDouble(5, videogioco.getPrezzo());
            ps.setInt(6, videogioco.getSconto());
            ps.setString(7, videogioco.getProduttore());
            ps.setInt(8, videogioco.getAppIdSteam());
            ps.setString(9, videogioco.getGenere());
            ps.setInt(10, idVideogioco);

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
                videogioco.setGenere(rs.getString("genere"));
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

    public List<Videogioco> doRetrieveDisabled() {
        List<Videogioco> giochi = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM VIDEOGIOCO WHERE stato = FALSE ORDER BY idVideogioco DESC"
            );
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
                gioco.setStato(false); // imposta lo stato esplicitamente
                giochi.add(gioco);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return giochi;
    }

    public List<Videogioco> doRetrieveAllActive() {
        List<Videogioco> videogiochi = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM VIDEOGIOCO WHERE stato = TRUE ORDER BY idVideogioco DESC");
            ResultSet rs = ps.executeQuery();
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
                videogioco.setStato(rs.getBoolean("stato"));
                videogiochi.add(videogioco);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return videogiochi;
    }

    public List<Videogioco> doRetrieveByPiattaformaAttivi(String piattaforma) {
        List<Videogioco> giochi = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM VIDEOGIOCO WHERE piattaforma = ? AND stato = TRUE ORDER BY idVideogioco DESC"
            );
            ps.setString(1, piattaforma);
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
                gioco.setStato(rs.getBoolean("stato"));
                giochi.add(gioco);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return giochi;
    }
}
