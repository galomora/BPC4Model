package com.bp.banking.c4.export;

import com.structurizr.io.plantuml.PlantUMLWriter;
import com.structurizr.view.View;

import java.io.FileWriter;
import java.io.IOException;

public class ViewExporter {

    private static ViewExporter instance;
    private static String DIRECTORY = DirectoryReader.getInstance().obtainJarDirectory();

    private static ViewExporter getInstance() {
        if (instance == null) {
            instance = new ViewExporter();
        }
        return instance;
    }

    public static void exportToPlantUml (View view, String fileName) {
        FileWriter fileWriter = null;
        try {
            String fullFileName = createCompleteFileName (fileName);
            fileWriter = new FileWriter(fullFileName);
            PlantUMLWriter plantUMLWriter = new PlantUMLWriter();
            plantUMLWriter.write(view, fileWriter);
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Diagram saved to file: " + fullFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String createCompleteFileName (String fileName) {
        StringBuilder builder = new StringBuilder("");
        builder.append(DIRECTORY);
        if (DIRECTORY.charAt(DIRECTORY.length()-1) != '/') {
            builder.append("/");
        }
        builder.append (fileName + ".uml");
        return builder.toString();
    }

    public static void exportToPlantUml (View view, ExportFileName fileName) {
        exportToPlantUml (view, fileName.getName());
    }


}
