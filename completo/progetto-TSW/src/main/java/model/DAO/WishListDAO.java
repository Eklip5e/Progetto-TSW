package model.DAO;

import model.WishList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishListDAO implements MetodiDAO<WishList>{

    @Override
    public void doSave(WishList wishList) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Preferito (idUtente, idVideogioco) VALUES (?, ?)");
            ps.setInt(1, wishList.getIdUtente());
            ps.setInt(2, wishList.getIdVideogioco());

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new RuntimeException("Errore durante l'inserimento nella wishlist.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doDelete(int idUtente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM Preferito WHERE idUtente = ?");
            ps.setInt(1, idUtente);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Elimina un singolo videogioco dalla wishlist di un utente
    public void doDeleteByGame(int idUtente, int idVideogioco) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM Preferito WHERE idUtente = ? AND idVideogioco = ?");
            ps.setInt(1, idUtente);
            ps.setInt(2, idVideogioco);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doUpdate(WishList wishList, int id) {
        // Non applicabile: la wishlist ha solo due chiavi, non ha senso aggiornare
        throw new UnsupportedOperationException("Aggiornamento non supportato per la wishlist.");
    }

    @Override
    public WishList doRetrieveById(int idUtente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Preferito WHERE idUtente = ?");
            ps.setInt(1, idUtente);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                WishList wl = new WishList();
                wl.setIdUtente(rs.getInt("idUtente"));
                wl.setIdVideogioco(rs.getInt("idVideogioco"));
                return wl;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public WishList doRetrieveByUtenteAndVideogioco(int idUtente, int idVideogioco) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Preferito WHERE idUtente = ? AND idVideogioco = ?");
            ps.setInt(1, idUtente);
            ps.setInt(2, idVideogioco);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                WishList wl = new WishList();
                wl.setIdUtente(rs.getInt("idUtente"));
                wl.setIdVideogioco(rs.getInt("idVideogioco"));
                return wl;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Recupera TUTTI i giochi preferiti per un utente
    public List<WishList> doRetrieveAllByUtente(int idUtente) {
        List<WishList> preferiti = new ArrayList<>();

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Preferito WHERE idUtente = ?");
            ps.setInt(1, idUtente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                WishList wl = new WishList();
                wl.setIdUtente(rs.getInt("idUtente"));
                wl.setIdVideogioco(rs.getInt("idVideogioco"));
                preferiti.add(wl);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return preferiti;
    }

    public static boolean isPreferito(int idUtente, int idVideogioco) {
        boolean trovato = false;
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT 1 FROM Preferito WHERE idUtente = ? AND idVideogioco = ?");
            ps.setInt(1, idUtente);
            ps.setInt(2, idVideogioco);
            ResultSet rs = ps.executeQuery();
            trovato = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trovato;
    }
}
