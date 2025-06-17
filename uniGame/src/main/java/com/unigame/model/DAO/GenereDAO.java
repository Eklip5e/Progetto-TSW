package com.unigame.model.DAO;

import com.unigame.model.Genere;

import java.sql.*;

public class GenereDAO implements MetodiDAO<Genere> {

    @Override
    public void doSave(Genere genere) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO GENERE (genere) VALUES(?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, genere.getGenere());
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
    public void doDelete(int idGenere) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM GENERE WHERE idGenere = ?");
            ps.setInt(1, idGenere);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("DELETE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doUpdate(Genere genere, int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE GENERE SET genere = ? WHERE idGenere = ?");
            ps.setString(1, genere.getGenere());
            ps.setInt(2, id);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("UPDATE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Genere doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT idGenere, genere FROM GENERE WHERE idGenere = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Genere genere = new Genere();
                genere.setIdGenere(rs.getInt("idGenere"));
                genere.setGenere(rs.getString("genere"));
                return genere;
            }

            return null; // Nessun risultato trovato
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
