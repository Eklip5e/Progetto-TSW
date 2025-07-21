package model.DAO;

import java.sql.SQLException;

public interface MetodiDAO <T>{
    void doSave(T t);

    void doDelete(int id);

    void doUpdate(T t, int id) throws SQLException;

    T doRetrieveById(int id);
}
