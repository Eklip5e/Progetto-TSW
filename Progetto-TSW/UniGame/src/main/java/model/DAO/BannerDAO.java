package model.DAO;

import model.Banner;

import java.sql.*;

public class BannerDAO implements MetodiDAO<Banner> {

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

    }

    @Override
    public void doUpdate(Banner banner, int idBanner) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Banner SET idVideogioco = ? WHERE idBanner = ?"
            );
            ps.setInt(1, banner.getIdVideogioco());
            ps.setInt(2, idBanner);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("UPDATE failed, no rows affected!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
