package utils;

import java.util.logging.Level;

public class Logs {

    public static void hide() {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
    }

}
