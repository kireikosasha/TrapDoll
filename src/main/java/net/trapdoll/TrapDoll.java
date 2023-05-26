package net.trapdoll;

import java.io.File;

import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class TrapDoll {

    public static void TrapDoll(Boolean StringEcrypt, Boolean remaping, Boolean inclassswap, Boolean variablerename, File project, File out) throws IOException {
        message("Creating copy in " + out);
        FileUtils.copyFile(project, out);

    }

    static void message(String s) { System.out.println(s); }
}
