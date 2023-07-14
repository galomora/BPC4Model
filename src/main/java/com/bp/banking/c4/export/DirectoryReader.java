package com.bp.banking.c4.export;

public class DirectoryReader {

    private DirectoryReader () {

    }

    private static DirectoryReader instance;

    public static DirectoryReader getInstance () {
        if (instance == null) {
            instance = new DirectoryReader();
        }
        return instance;
    }
    public String obtainJarDirectory () {
        String directoryWithJar = getClass().getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
        System.out.println("Current directory: " + directoryWithJar.substring(0, directoryWithJar.lastIndexOf("/") + 1));
        return directoryWithJar.substring(0, directoryWithJar.lastIndexOf("/") + 1);
    }
}
