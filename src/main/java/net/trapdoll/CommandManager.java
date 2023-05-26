package net.trapdoll;

import static net.trapdoll.Main.Start;
import static net.trapdoll.Main.*;

public class CommandManager {

    public static boolean booleanlast = true;

    public static int command(String command) {
        if (command.contains("/td") || command.contains("/trapdoll")) {
            if (command.contains("start")) { Start(); return 2; }
            if (command.contains("options")) { callOptions(); return 2; }
            if (command.contains("remap")) { extremeremaping = booleanmanager(command); return 1; }
            if (command.contains("rename")) { variablerename = booleanmanager(command); return 1; }
            if (command.contains("stringec")) { bytestringecrypt = booleanmanager(command); return 1; }
            if (command.contains("swapper")) { inclassswapper = booleanmanager(command); return 1; }
            return 0;

        } else {
            if (command.contains("/help")) {
                callSettings();
                return(2);
            } else { return 0; }
        }
    }

    private static boolean booleanmanager (String command) {booleanlast = command.contains("true"); return command.contains("true");}
}
