package model.DAO;

import model.Utente;

import java.sql.*;

public class UtenteDAO implements MetodiDAO<Utente> {

    @Override
    public void doSave(Utente utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Utente (admin, username, email, password, nome, cognome, dataDiNascita) VALUES(?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setBoolean(1, utente.isAdmin() != null ? utente.isAdmin() : false);
            ps.setString(2, utente.getUsername());
            ps.setString(3, utente.getEmail());
            ps.setString(4, utente.getPassword());
            ps.setString(5, utente.getNome());
            ps.setString(6, utente.getCognome());
            ps.setDate(7, new Date(utente.getDataDiNascita().getTime()));

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new RuntimeException("Errore durante l'inserimento dell'utente.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    utente.setIdUtente(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doDelete(int idUtente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM Utente WHERE idUtente = ?"
            );
            ps.setInt(1, idUtente);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("DELETE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doUpdate(Utente utente, int idUtente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Utente SET username = ?, email = ?, password = ?, nome = ?, cognome = ?, dataDiNascita = ? WHERE idUtente = ?"
            );
            ps.setString(1, utente.getUsername());
            ps.setString(2, utente.getEmail());
<<<<<<< HEAD:src/main/java/model/DAO/UtenteDAO.java
            ps.setString(3, utente.getUsername());
            ps.setString(4, utente.getPassword());
            ps.setString(5, utente.getNome());
            ps.setString(6, utente.getCognome());
            ps.setDate(7, new java.sql.Date(utente.getDataDiNascita().getTime()));
            ps.setInt(8, idUtente);
=======
            ps.setString(3, utente.getPassword());
            ps.setString(4, utente.getNome());
            ps.setString(5, utente.getCognome());
            ps.setDate(6, new java.sql.Date(utente.getDataDiNascita().getTime()));
            ps.setInt(7, idUtente);
>>>>>>> Gennaro:unigame-site/src/main/java/model/DAO/UtenteDAO.java

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("UPDATE failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePassword(int idUtente, String nuovaPassword) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Utente SET password = ? WHERE idUtente = ?"
            );
            ps.setString(1, nuovaPassword);
            ps.setInt(2, idUtente);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("UPDATE password failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Utente doRetrieveById(int idUtente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT idUtente, admin, email, username, password, nome, cognome, dataDiNascita FROM UTENTE WHERE idUtente = ?"
            );
            ps.setInt(1, idUtente);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Utente utente = new Utente();

                utente.setIdUtente(rs.getInt(1));
                utente.setAdmin(rs.getBoolean("admin"));
                utente.setEmail(rs.getString("email"));
                utente.setUsername(rs.getString("username"));
                utente.setPassword(rs.getString("password"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setDataDiNascita(rs.getDate("dataDiNascita"));

                return utente;
            }
            return null; // Nessun utente trovato
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il recupero dell'utente con ID: " + idUtente, e);
        }
    }

    public Utente doRetrieveByUsername(String username) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT idUtente, admin, email, username, password, nome, cognome, dataDiNascita FROM Utente WHERE username = ?"
            );
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            Utente utente = new Utente();
            if (rs.next()) {
                utente.setIdUtente(rs.getInt("idUtente"));
                utente.setAdmin(rs.getBoolean("admin"));
                utente.setEmail(rs.getString("email"));
                utente.setUsername(rs.getString("username"));
                utente.setPassword(rs.getString("password"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setDataDiNascita(rs.getDate("dataDiNascita"));

                return utente;
            }
            return null; // Nessun utente trovato
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean usernameExists(String username) {
        boolean exists = false;
        try (Connection con = ConPool.getConnection()) {
             PreparedStatement ps = con.prepareStatement(
                     "SELECT 1 FROM Utente WHERE username = ?"
             );
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                exists = rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exists;
    }

    public static boolean emailExists(String email) {
        boolean exists = false;
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT 1 FROM Utente WHERE email = ?"
            );
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                exists = rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exists;
    }

    public boolean usernameExistsForOtherUser(String username, int idUtente) {
        String sql = "SELECT COUNT(*) FROM utente WHERE username = ? AND idUtente != ?";
        try (Connection conn = ConPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setInt(2, idUtente);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean emailExistsForOtherUser(String email, int idUtente) {
        String sql = "SELECT COUNT(*) FROM utente WHERE email = ? AND idUtente != ?";
        try (Connection conn = ConPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setInt(2, idUtente);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}