package com.unigame.model.DAO;

public interface MetodiDAO <T>{
    void doSave(T t);

    void doDelete(int id);

    void doUpdate(T t, int id);

    T doRetrieveById(int id);
}
