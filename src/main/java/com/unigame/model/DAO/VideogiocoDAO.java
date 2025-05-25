package com.unigame.model.DAO;

import com.unigame.model.Videogioco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideogiocoDAO implements MetodiDAO<Videogioco> {

    @Override
    public void doSave(Videogioco videogioco) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO VIDEOGIOCO (titolo, piattaforma, ReleaseDate, Descrizione, appIdSteam, Prezzo, Sconto, Produttore) VALUES(?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, videogioco.getTitolo());
            ps.setString(2, videogioco.getPiattaforma());
            ps.setDate(3, new Date(videogioco.getDataRilascio().getTime()));
            ps.setString(4, videogioco.getDescrizione());
            ps.setInt(5, videogioco.getAppIdSteam());
            ps.setDouble(6, videogioco.getPrezzo());
            ps.setInt(7, videogioco.getSconto());
            ps.setString(8, videogioco.getProduttore());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                videogioco.setIdGame(rs.getInt(1));
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
                    "UPDATE VIDEOGIOCO SET titolo = ?, piattaforma = ?, ReleaseDate = ?, descrizione = ?, prezzo = ?, sconto = ?, Produttore = ?, appIdSteam = ? WHERE idVideogioco = ?");
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
                videogioco.setIdGame(rs.getInt("idVideogioco"));
                videogioco.setTitolo(rs.getString("titolo"));
                videogioco.setPiattaforma(rs.getString("piattaforma"));
                videogioco.setDataRilascio(rs.getDate("ReleaseDate"));
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
                gioco.setIdGame(rs.getInt("idVideogioco"));
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

    public List<Videogioco> doRetrieveByPiattaforma(String piattaforma) {
        List<Videogioco> giochi = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM VIDEOGIOCO WHERE piattaforma = ? ORDER BY idGame DESC");
            ps.setString(1, piattaforma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Videogioco gioco = new Videogioco();
                gioco.setIdGame(rs.getInt("idGame"));
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
}
