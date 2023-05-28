package net.trapdoll;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        boolean deleted = out.delete();
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
                if (Files.isReadable(filePath) && Files.isWritable(filePath) && String.valueOf(filePath).contains(".java")) {
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


    }


    static void message(String s) { System.out.println(s); }
}
