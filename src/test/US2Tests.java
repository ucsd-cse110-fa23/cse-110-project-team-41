<<<<<<< Updated upstream
package test;
=======
package test; 
import main.java.server.*; 
import main.java.client.*; 
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
>>>>>>> Stashed changes

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
