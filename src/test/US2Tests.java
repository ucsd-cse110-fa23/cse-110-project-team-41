package test;

import org.junit.jupiter.api.Test;

import main.java.server.database;

/*
 * A class to test tasks related to US2.
 * Specifically aimed at testing saving recipes into db.
 */
public class US2Tests {
    @Test
    void testSaveRecipe() {
        database db = new database();
        db.addFileToDb();
    }

    @Test
    void testConnectToDB() {

    }
}
