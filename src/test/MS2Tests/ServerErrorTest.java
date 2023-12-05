package test.MS2Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import main.java.client.Main;
import main.java.server.Server;
import test.MockDatabase;
import test.MockShare;

public class ServerErrorTest { 
    @Test
    void testServerNotRunning() throws IOException { 
        Server sv = new Server(); 
        // sv.startServer(null); 
        Main mn = new Main(); 
        assertEquals(mn.displayError(), 0); 
        // sv.endServer(null); 
    } 

    @Test
    void testServerRunning() throws IOException { 
        Server sv = new Server(); 
        sv.startServer(null); 
        Main mn = new Main(); 
        assertEquals(mn.displayError(), 1); 
        sv.endServer(null); 
    } 
}
