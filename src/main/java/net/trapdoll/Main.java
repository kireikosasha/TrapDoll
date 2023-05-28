package net.trapdoll;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static net.trapdoll.CommandManager.*;
import static net.trapdoll.TrapDoll.*;

public class Main  {

    public static boolean work = true;
    public static boolean bytestringecrypt = true;
    public static boolean extremeremaping = true;
    public static boolean variablerename = true;
    public static boolean inclassswapper = true;
    public static int action;
    public static void main(String[] args) throws IOException, InterruptedException {

        reset();
        message("""
                ========================
                Welcome!\s
                created by Sasha Kireiko (pawsashatoy)\s
                https://github.com/kireikosasha/TrapDoll\s
                ========================""");
        callSettings();
    }

    public static void callSettings() throws IOException, InterruptedException {
        work = true;
        message("""
                 [TRAPDOLL V1.0]\s
                 /trapdoll - call this panel\s
                 /trapdoll start - ready to protection! +\s
                 /trapdoll options - show options status\s
                 /td remaping true/false - remapping classes\s
                 /td stringecrypt true/false - string protection\s
                 /td rename true/false - rename variables\s
                 /td swapper true/false - extreme inclass swapper\
                """);

        while (work) {
            if (!work) { break; }
            action = command(callcommand());
            if (action == 0) { message("Unknown command! Try /help to see all commands"); }
            if (action == 1) { message("Success changes! (to " + booleanlast + ")"); }
        }

    }

    public static void callOptions() {
        message(" ReMapping: " + extremeremaping + "\n String Ecrypt: "
                + bytestringecrypt + "\n ReName Variables: " + variablerename + "\n InClass Code Swapper: " + inclassswapper);
    }

    public static void Start() throws IOException, InterruptedException {
        work = false;
        message("$ Write your project path: \n Example: C://Users/Name/IdeaProjects/MyProject");
        Scanner getPath = new Scanner(System.in);
        File path = new File(getPath.nextLine());
        if (path.isDirectory()) {
            File out = new File("C:/trapdoll_out/");
            TrapDoll(bytestringecrypt, extremeremaping, inclassswapper, variablerename, path, out);
        } else { message("File is not directory! \nExit..."); callSettings();}
    }
    static void message(String s) { System.out.println(s); }

    private static void reset() {
        work = true;
        bytestringecrypt = true;
        extremeremaping = true;
        variablerename = true;
        inclassswapper = true;
    }
    public static String callcommand() {
        message("$: ");
        Scanner read = new Scanner(System.in);
        return read.nextLine();
    }

}
