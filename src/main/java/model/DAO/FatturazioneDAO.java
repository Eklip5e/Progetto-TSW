package model.DAO;

import model.Fatturazione;

import java.sql.*;

public class FatturazioneDAO implements MetodiDAO<Fatturazione> {

    @Override
    public void doSave(Fatturazione fatturazione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Fatturazione (numero, titolare, scadenza, cvc, idUtente) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, fatturazione.getNumero());
            ps.setString(2, fatturazione.getTitolare());
            ps.setDate(3, new Date(fatturazione.getScadenza().getTime()));
            ps.setString(4, fatturazione.getCvc());
            ps.setInt(5, fatturazione.getIdUtente());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Errore durante l'inserimento dei dati di fatturazione.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    fatturazione.setIdFatturazione(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doDelete(int idFatturazione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM Fatturazione WHERE idFatturazione = ?");
            ps.setInt(1, idFatturazione);
            if (ps.executeUpdate() == 0) {
                throw new RuntimeException("Nessuna riga cancellata.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doUpdate(Fatturazione fatturazione, int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Fatturazione SET numero = ?, titolare = ?, scadenza = ?, cvc = ?, idUtente = ? WHERE idFatturazione = ?");
            ps.setString(1, fatturazione.getNumero());
            ps.setString(2, fatturazione.getTitolare());
            ps.setDate(3, new Date(fatturazione.getScadenza().getTime()));
            ps.setString(4, fatturazione.getCvc());
            ps.setInt(5, fatturazione.getIdUtente());
            ps.setInt(6, id);

            if (ps.executeUpdate() == 0) {
                throw new RuntimeException("Nessun aggiornamento effettuato.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Fatturazione doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Fatturazione WHERE idFatturazione = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Fatturazione f = new Fatturazione();
                f.setIdFatturazione(rs.getInt("idFatturazione"));
                f.setNumero(rs.getString("numero"));
                f.setTitolare(rs.getString("titolare"));
                f.setScadenza(rs.getDate("scadenza"));
                f.setCvc(rs.getString("cvc"));
                f.setIdUtente(rs.getInt("idUtente"));
                return f;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}