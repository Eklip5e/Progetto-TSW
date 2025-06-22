package model.DAO;

import model.DAO.MetodiDAO;
import model.Img;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImgDAO implements MetodiDAO<Img> {

    @Override
    public void doSave(Img img) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Img (copertina, banner, IDVideogioco) VALUES (?, ?, ?)");

            ps.setString(1, img.getCopertina());
            ps.setString(2, img.getBanner());
            ps.setInt(3, img.getIdVideogioco());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doDelete(int id) {

    }

    @Override
    public void doUpdate(Img img, int id) {

    }

    @Override
    public Img doRetrieveById(int id) {
        return null;
    }

    // Altri metodi come doDelete, doUpdate, doRetrieveById ecc. possono essere implementati in modo simile
}