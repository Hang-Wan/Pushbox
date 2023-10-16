import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * all the file operation
 * of the game including crate, 
 * read and write functions.
 * @author Hang Wan
 */

public class FileIO {

    /* 
     * Check the record text file,
     * if there is no file, crate it.
     */
    public static void crateRecord() {
        File file = new File("record.txt"); //file record.tet
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.println("Creat recording fail: "+ e.getMessage());
                }
            }
    }


    /**
     * Reads all lines of text from the record.txt
     * and storage it in steps array 
     */
    public static void readRecord() {
        try {
            Reader reader = new FileReader("record.txt");//file record.txt.
            BufferedReader record = new BufferedReader(reader); //reader
            String line; //The line of record file
            for (int i = 0; i < GameMap.MAXLEVEL; i++) {    
                line = record.readLine();
                if (line == null) {
                    GameMap.steps[i] = 0;
                } else {
                    GameMap.steps[i] = Integer.parseInt(line);
                }
            }
            
            record.close();
        } catch (IOException e) {
            System.out.println("Unable to read file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Unable to parse integer: " + e.getMessage());
        }
    }
    
    /**
     * Reads all maps of text from the maps.txt.
     * and convert them to storage it in map data array 
     */
    public static String[] readMaps() {
        List<String> mapDataList = new ArrayList<>(); // a list that can extend
        StringBuilder currentMap = new StringBuilder();// used to build and modify strings
        String[] mapData = new String[GameMap.MAXLEVEL]; // storage the map data

        try (BufferedReader reader = new BufferedReader(new FileReader("maps.txt"))) {
            String line; //The line of record file
            int mapCount = 0; // count the number of map
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    if (currentMap.length() > 0) {
                        mapDataList.add(currentMap.toString());
                        currentMap = new StringBuilder();
                        mapCount++;                     
                        if (mapCount == GameMap.MAXLEVEL) {
                            break; 
                        }
                    }
                } else {
                    currentMap.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            System.out.println("unable to find map data, please check maps.txt file: " + e.getMessage());
        }

        mapData = mapDataList.toArray(mapData);
        return mapData;
    }

    /* 
     * read all record from steps array and write it on the record.txt file.
     */
    public static void writeRecord() {
        try {
            FileWriter writer = new FileWriter("record.txt");//file writer record txt.
            for (int i = 0; i < GameMap.MAXLEVEL; i++) {
                writer.write(GameMap.steps[i] + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Unable to write file: " + e.getMessage());
        }
    }
    
}
