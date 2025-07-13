package model.DAO;

import model.Banner;

import java.sql.*;

public class BannerDAO implements MetodiDAO<Banner> {

    @Override
    public void doUpdate(Banner banner, int id) throws SQLException {

    }

    @Override
    public void doSave(Banner banner) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Banner (idBanner, idVideogioco) VALUES(?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, 1);
            ps.setInt(2, banner.getIdVideogioco());

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new RuntimeException("Errore durante l'inserimento del banner.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doDelete(int id) {
<<<<<<<< HEAD:src/main/java/model/DAO/BannerDAO.java
========
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM Banner WHERE idBanner = ?"
            );
            ps.setInt(1, id);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("DELETE failed, no rows affected!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante l'eliminazione del banner con ID: " + id, e);
        }
    }

    @Override
    public void doUpdate(Banner banner, int idBanner) {
>>>>>>>> Gennaro:Progetto-TSW/UniGame/src/main/java/model/DAO/BannerDAO.java
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM Banner WHERE idBanner = ?"
            );
            ps.setInt(1, id);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("DELETE failed, no rows affected!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante l'eliminazione del banner con ID: " + id, e);
        }
    }

    public void doUpdate(Banner banner) throws SQLException{
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Banner SET idVideogioco = ? WHERE idBanner = 1"
            );
            ps.setInt(1, banner.getIdVideogioco());

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new RuntimeException("Errore durante la modifica del banner.");
            }
        }
    }

    @Override
    public Banner doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT idBanner, idVideogioco FROM Banner WHERE idBanner = ?"
            );
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Banner banner = new Banner();
                banner.setIdBanner(rs.getInt("idBanner"));
                banner.setIdVideogioco(rs.getInt("idVideogioco"));
                return banner;
            }

            return null; // Nessun banner trovato
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il recupero del banner con ID: " + id, e);
        }
    }
}
