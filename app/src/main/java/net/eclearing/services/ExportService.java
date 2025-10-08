package net.eclearing.services;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.*;
import java.util.*;

public class ExportService {



    private Path exportDirectory;
    private int highNumber = 0;

    public ExportService() {
        this.exportDirectory = Paths.get("app/exported_chats");
    }

    //(lege neues verzeichniss an)
    public ExportService(Path exportDirectory) {
        this.exportDirectory = exportDirectory;
        ensureDirectoryExists();
    }

    public Path getExportDirectory() {
        return exportDirectory;
    }

    public String getFileNameToBe(){
        return "";
    }

    //prüfe, ob ein verzeichnis existiert: wenn nein erstlelle es (bzw versuche es)
    public void ensureDirectoryExists(){
        try {
            if (!Files.exists(exportDirectory)) {
                Files.createDirectories(exportDirectory);
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant create export directory: " + exportDirectory, e);
        }
    }

    public String getFileNameNoExtention(String pFileName){

        if(pFileName == null){ //ungültige eingabe (leer) abfangen
            throw new IllegalArgumentException("File Name is empty, Illegal File name");
        }

        String fileNameTrimmed = pFileName.trim(); // hier in auto vorschlag gucken
        //System.out.println("truimmed name:" + fileNameTrimmed);
        if( fileNameTrimmed.isEmpty() ){ //ungültige eingabe (leer) abfangen
            throw new IllegalArgumentException("File Name is empty, Illegal File name (after trim)");
        }
        if(!(fileNameTrimmed.contains("."))){// //ungültige eingabe (kein "." im names) abfangen
           // System.out.println(fileNameTrimmed+ "FILE NAME TRIMMED''''''''''");
            throw new IllegalArgumentException(" File name does not conatin a . charachter, Illegal File name");
        } //soweitr hier zu beurteilen gültiger name der gesplittet werden kann


        String firstPart = fileNameTrimmed.split("\\.")[0]; // Punkt escapen

        if( firstPart.isEmpty() ){ //ungültige eingabe (leer) abfangen
            throw new IllegalArgumentException("File Name is empty, Illegal File name (Post Split)");
        }

        return firstPart;
    }

    public int getNumberFromFileName(String pfileName){


        String frontPart = getFileNameNoExtention(pfileName); // sollen vom äusseren aufrufer gecatched werden? glaube ich
        System.out.println("frontpart from file name: " +frontPart);
        //prüfe ob der name nur ziffern enthählt
        if (!(frontPart.matches("[0-9]+"))) {                //man könnte als RegEx auch \\d schreiben als platzhalter für (dezimal)zahlen
            throw new IllegalArgumentException("Filename contains non-Number(s). Not allowed. Found: " + frontPart);
        }

        //wir haben nur zahlen im namen
        try {
            return Integer.parseInt(frontPart);

        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("filename (numer ) is too large. Found:" + frontPart, e);

        }
    }

    public List<String> getFileNamesFromDirectory() throws IOException {

        List<String> fileNameList = new ArrayList<>();

        try(DirectoryStream<Path> dirStream = Files.newDirectoryStream(this.exportDirectory)){

            boolean isEmpty = true;

            for (Path entry : dirStream) {

                isEmpty = false;

                // System.out.println("is entry?" + Files.isRegularFile(entry));
                //System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                if (Files.isRegularFile(entry)){
                    Path fileNameFromPath = entry.getFileName(); // sollte bereits nur den namen enthalten, nicht den ganzen pfand /src/export/....
                    String fileName = fileNameFromPath.toString();
                    //System.out.println("filePath: "+ fileNameFromPath +" file Name:" + fileName);
                    fileNameList.add(fileName);

                }
            }
            //   System.out.println("directory is empty?: "+isEmpty);
            return fileNameList;
        }
    }




    public Integer getBiggestNumber() throws IOException { //oder catch
        //1. checke ob ein verzeichniss existiert
        ensureDirectoryExists();

        //get filenames from dir
        List<String> fileNames = getFileNamesFromDirectory();
        List<Integer> fileNamesAsNumbers = new ArrayList<Integer>();

        for(String  name: fileNames){
            fileNamesAsNumbers.add(getNumberFromFileName(name));
        }

        if (fileNamesAsNumbers.isEmpty()) {
            System.out.println("the directory was empty");
            return 1; //es gibt keine dateien im verzeichniss, also können wir mit 1 anfangen
            //  throw new IllegalStateException("No valid Files in export directory.");
        }

        // fileNamesAsNumbers.sort(Comparator.naturalOrder());

        // return fileNamesAsNumbers.getLast();

        return Collections.max(fileNamesAsNumbers); //so wie es gelöst ist nicht die beste kösung, ich glaube besser die höchste nummer als atribut halten und gegebenen falls aktualisieren

    }

    public int getHighNumberAfterBiggest() throws IOException {
        return getBiggestNumber()+1;
    }

    public String getNewFileName() throws IOException {
        System.out.println("getNewFileName got called");
        return (getHighNumberAfterBiggest()) + ".txt";
    }


    //sollte ich das mit buffered reader/writer machen?
    public void saveStringToFile(String allInformation, String chatMessages) throws IOException {
        String newFileNameAll = getNewFileName();
        String newFileNameMessages = "0"+getNewFileName();
        Path newFilePathAll = exportDirectory.resolve(newFileNameAll);
        Path newFilePathMessages = exportDirectory.resolve(newFileNameMessages);


        try {

            // Datei neu anlegen, Fehler wenn sie schon existiert
            Files.writeString(newFilePathAll, allInformation); //kein buffered writer, da der gesamte string bereits im speicher ist
            System.out.println("Saved New File");
            System.out.println("  Path: " + newFilePathAll.toAbsolutePath());
            System.out.println("  Name: " + newFileNameAll);

            Files.writeString(newFilePathMessages, chatMessages); //kein buffered writer, da der gesamte string bereits im speicher ist
            System.out.println("Saved New File");
            System.out.println("  Path: " + newFilePathAll.toAbsolutePath());
            System.out.println("  Name: " + newFileNameAll);

        } catch (FileAlreadyExistsException e) {
            throw new IllegalStateException("File already exists: " + newFilePathAll, e);

        } catch (IOException e) {
            throw new UncheckedIOException("Fehler beim Speichern der Datei: " + newFilePathAll, e);
        }

    }

}

/*
split names (done)
finde höchste nummer
 */
