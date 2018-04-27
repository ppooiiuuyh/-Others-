package com.ourproject.myapp.dbinterfaces;

/**
 * Created by ppooi on 2017-05-01.
 */
public abstract class DBManager {

    abstract public void execQuery(String query);
    abstract public boolean execute(String query);

}
