package model.DAO;

import model.Utente;
import model.Videogioco;

import java.sql.*;

public class VideogiocoDAO implements MetodiDAO<Videogioco> {

    @Override
    public void doSave(Videogioco videogioco) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO VIDEOGIOCO (titolo, piattaforma, dataRilascio, descrizione, prezzo, sconto, screenshot) VALUES(?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, videogioco.getTitolo());
            ps.setString(2, videogioco.getPiattaforma());
            ps.setDate(3, videogioco.getDataRilascio());
            ps.setString(4, videogioco.getDescrizione());
            ps.setDouble(5, videogioco.getPrezzo());
            ps.setInt(6, videogioco.getSconto());
            ps.setString(7, videogioco.getScreenshot());
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
    public void doDelete(int idVideogioco) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM VIDEOGIOCO WHERE idVideogioco = ?");
            ps.setInt(1, idVideogioco);
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
                    "UPDATE VIDEOGIOCO SET titolo = ?, piattaforma = ?, dataRilascio = ?, descrizione = ?, prezzo = ?, sconto = ?, screenshot = ? WHERE idVideogioco = id");
            ps.setString(1, videogioco.getTitolo());
            ps.setString(2, videogioco.getPiattaforma());
            ps.setDate(3, videogioco.getDataRilascio());
            ps.setString(4, videogioco.getDescrizione());
            ps.setDouble(5, videogioco.getPrezzo());
            ps.setInt(6, videogioco.getSconto());
            ps.setString(7, videogioco.getScreenshot());

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
                    "SELECT * FROM VIDEOGIOCO WHERE idVideogioco = id");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Videogioco videogioco = new Videogioco();
                videogioco.setTitolo(rs.getString("titolo"));
                videogioco.setPiattaforma(rs.getString("piattaforma"));
                videogioco.setDataRilascio(rs.getDate("dataRilascio"));
                videogioco.setDescrizione(rs.getString("descrizione"));
                videogioco.setPrezzo(rs.getDouble("prezzo"));
                videogioco.setSconto(rs.getInt("sconto"));
                videogioco.setScreenshot(rs.getString("screenshot"));
                return videogioco;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
