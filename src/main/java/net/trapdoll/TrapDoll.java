package net.trapdoll;

import java.io.File;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.apache.commons.io.FileUtils;

import static org.apache.commons.io.FileUtils.delete;

public class TrapDoll {

    private static String pathlocal;
    private static String namelocal;
    private static String grouplocal;
    final static char E = '"';
    final static char R = '\'';

    private static int pos;
    private static int localpos;
    private static long lenght;
    private static long lenghtfix;
    private static Boolean break_strings;
    private static String break_reluts;

    static ArrayList<String> tree = new ArrayList<>();
    static ArrayList<String> treenames = new ArrayList<>();
    static ArrayList<String> treesubpath = new ArrayList<>();
    static ArrayList<String> treedirectory = new ArrayList<>();

    public static void TrapDoll(Boolean StringEcrypt, Boolean remaping, Boolean inclassswap, Boolean variablerename, File project, File out) throws IOException, InterruptedException {
        tree.clear();
        treenames.clear();
        treesubpath.clear();
        treedirectory.clear();
        message("Creating copy in " + out);
        try (Stream<Path> filePathStream=Files.walk(Paths.get(project.toURI()))) {
            filePathStream.forEach(filePath -> {
                if (Files.isDirectory(filePath)) {
                    treedirectory.add(String.valueOf(filePath.getFileName()));
                }
            });
        }
        if (treedirectory.contains(".git")) { message("[WARNING] .git file is problematic! Delete it if its cause error \n[WARNING] Start after 5 second..."); TimeUnit.SECONDS.sleep(5);}
        FileUtils.copyDirectory(project, out);
        message("Initializing...");
        try (Stream<Path> filePathStream=Files.walk(Paths.get(out.toURI()))) {
            filePathStream.forEach(filePath -> {
                if (Files.isReadable(filePath) && Files.isRegularFile(filePath) && String.valueOf(filePath).contains(".java")) {
                    pathlocal = String.valueOf(filePath);
                    tree.add(pathlocal);
                    namelocal = String.valueOf(filePath.getFileName());
                    treenames.add(namelocal);
                    grouplocal = pathlocal.replace(namelocal, "");
                    treesubpath.add(grouplocal);
                    message("Added: " + namelocal + "\nGroup: " + grouplocal);
                }
            });
        }
        if (StringEcrypt) {
            break_strings = false;
            pos = 0;
            localpos = 0;
            lenght = tree.size();
            HashMap<String, String> local_map = new HashMap<>();
            ArrayList<String> getstrings = new ArrayList<>();
            String fileread;
            int filereadlght;
            while (pos != lenght) {
                break_reluts = "";
                break_strings = false;
                fileread = Files.readString(Paths.get(tree.get(pos)));
                filereadlght = fileread.length();
                localpos = 0;
                message("NEW FILE!");
                while (localpos != filereadlght - 1) {
                    if (fileread.charAt(localpos) == E) {
                        if (break_strings) {
                            if (break_reluts.length() < 400 && !break_reluts.contains(";") && !break_reluts.contains("{")) {
                                getstrings.add(break_reluts);
                            }
                            break_reluts = "";
                            break_strings = false;
                        } else {
                            break_strings = true;
                        }
                    } else {
                        if (fileread.charAt(localpos) == R) { break_strings = false; break_reluts = "";}
                        if (break_strings) {
                            break_reluts = break_reluts.concat(String.valueOf(fileread.charAt(localpos)));
                        }
                    }
                    localpos++;
                }
                message("Strings to ecrypt in file " + pos + " - " + getstrings.size());
                String get_local_group = treesubpath.get(pos);

                pos++;
            }
        }


    }




    static void message(String s) { System.out.println(s); }
}
