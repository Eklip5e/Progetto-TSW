package com.unigame.model.DAO;

import com.unigame.model.Utente;
import com.unigame.model.Videogioco;

import java.sql.*;

public class VideogiocoDAO implements MetodiDAO<Videogioco> {

    @Override
    public void doSave(Videogioco videogioco) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO VIDEOGIOCO (titolo, piattaforma, ReleaseDate, descrizione, prezzo, sconto, Produttore) VALUES(?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, videogioco.getTitolo());
            ps.setString(2, videogioco.getPiattaforma());
            ps.setDate(6, new Date(videogioco.getDataRilascio().getTime()));
            ps.setString(4, videogioco.getDescrizione());
            ps.setDouble(5, videogioco.getPrezzo());
            ps.setInt(6, videogioco.getSconto());
            ps.setString(7, videogioco.getProduttore());
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
    public void doDelete(int idGame) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM VIDEOGIOCO WHERE IDGame = ?");
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
                    "UPDATE VIDEOGIOCO SET titolo = ?, piattaforma = ?, ReleaseDate = ?, descrizione = ?, prezzo = ?, sconto = ?, Produttore = ? WHERE IDGame = ?");
            ps.setString(1, videogioco.getTitolo());
            ps.setString(2, videogioco.getPiattaforma());
            ps.setDate(6, new Date(videogioco.getDataRilascio().getTime()));
            ps.setString(4, videogioco.getDescrizione());
            ps.setDouble(5, videogioco.getPrezzo());
            ps.setInt(6, videogioco.getSconto());
            ps.setString(7, videogioco.getProduttore());
            ps.setInt(8, id);

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
                    "SELECT idGame, titolo, piattaforma, ReleaseDate, descrizione, prezzo, sconto, Produttore FROM videogioco WHERE IDGame = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Videogioco videogioco = new Videogioco();
                videogioco.setIdGame(rs.getInt("idGame"));
                videogioco.setTitolo(rs.getString("titolo"));
                videogioco.setPiattaforma(rs.getString("piattaforma"));
                videogioco.setDataRilascio(rs.getDate("ReleaseDate"));
                videogioco.setDescrizione(rs.getString("descrizione"));
                videogioco.setPrezzo(rs.getDouble("prezzo"));
                videogioco.setSconto(rs.getInt("sconto"));
                videogioco.setProduttore(rs.getString("Produttore"));
                return videogioco;
            }

            return null; // Nessun videogioco trovato con l'id fornito
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
