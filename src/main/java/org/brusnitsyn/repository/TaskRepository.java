package org.brusnitsyn.repository;

import org.brusnitsyn.db.DatabaseConnection;

public class TaskRepository {
    private final DatabaseConnection databaseConnection;

    public TaskRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
}
