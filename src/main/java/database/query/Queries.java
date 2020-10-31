package database.query;

import database.MysqliDatabase;

public abstract class Queries {
    MysqliDatabase db;

    public Queries(MysqliDatabase db) {
        this.db = db;
    }
}
