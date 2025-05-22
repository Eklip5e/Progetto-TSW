package com.unigame.model.DAO;

import com.unigame.model.Screenshot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ScreenshotDAO implements MetodiDAO<Screenshot> {

    @Override
    public void doSave(Screenshot screenshot) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Screenshots (Screenshot, IDVideogioco) VALUES (?, ?)");

            ps.setString(1, screenshot.getScreenshot());
            ps.setInt(2, screenshot.getIdVideogioco());

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
    public void doUpdate(Screenshot screenshot, int id) {

    }

    @Override
    public Screenshot doRetrieveById(int id) {
        return null;
    }

    // Altri metodi come doDelete, doUpdate, doRetrieveById ecc. possono essere implementati in modo simile
}