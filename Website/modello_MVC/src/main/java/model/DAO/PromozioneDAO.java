package model.DAO;

import model.Promozione;

import java.sql.*;

public class PromozioneDAO implements MetodiDAO<Promozione> {

    @Override
    public void doSave(Promozione promozione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO PROMOZIONE (codice, descrizione, stato, tipoSconto, sconto) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, promozione.getCodice());
            ps.setString(2, promozione.getDescrizione());
            ps.setBoolean(3, promozione.isStato());
            ps.setString(4, promozione.getTipoSconto());
            ps.setInt(5, promozione.getSconto());
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
    public void doDelete(int idPromozione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM PROMOZIONE WHERE idPromozione = ?");
            ps.setInt(1, idPromozione);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("DELETE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doUpdate(Promozione promozione, int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE PROMOZIONE SET codice = ?, descrizione = ?, stato = ?, tipoSconto = ?, sconto = ? WHERE idPromozione = id");
            ps.setInt(1, promozione.getCodice());
            ps.setString(2, promozione.getDescrizione());
            ps.setBoolean(3, promozione.isStato());
            ps.setString(4, promozione.getTipoSconto());
            ps.setInt(5, promozione.getSconto());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("UPDATE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
