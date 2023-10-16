/**
 * storage the information 
 * about game map.
 * and some operation about game map. 
 * including convert the map to the shapes map
 * @author Hang Wan
 */

public class GameMap {
    public static final int SIZE = 16;// Define the size of the map
    public static final int MAXLEVEL = 10;// Define the maximum level of the map
    public static char[][][] maps = new char[MAXLEVEL][SIZE][SIZE];// Declare and initialize the map array
    public static int level; //the current level
    public static int[] steps = new int[MAXLEVEL]; //the step recording of different level.
    public static Player player;
    /* 
     * the elements of maps.
     */
    public static final char SPACE = '0';  // space
    public static final char WALL = '1';  // wall
    public static final char DES = '2';  // destination
    public static final char BOX = '3';  // box
    public static final char MAN = '4';  // people
    public static final char GOOD = '5';  // box on the destination
    public static final char ON = '6';  // people on the destination
    private static char[] map_chars = {' ','■','●','#','P','◎','P'}; //the shapes of elements
    public static final String[] ANSI_COLOR ={"\u001B[0m", "\u001B[30m", "\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m", "\u001B[36m"}; //the ANSI value of differ colour.


    /* 
    * laod maps and storage in maps array
    */
    public static void loadMap(){
        maps = mapStore();
    }

    /* 
     * Load the game map by print the maps array of level
     */
    public static void readMap(){
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                printMap(maps[level][i][j]);
            }
            System.out.println();
        }
    }

    /* 
     *Receive the elements information and convert it to symbol map
     */
    public static void printMap(char element){
        int index = Integer.parseInt(String.valueOf(element)); // the index of elements
        System.out.print(ANSI_COLOR[index + 1] + map_chars[index] + ANSI_COLOR[0] + " ");
    }

    /**
     * Converts the multi-line String into a 2D array of characters.
     */
    public static char[][] stringToMap(String str) {
        char[][] result;// the result of string to map
        String[] rows; // the row of tansfer       
        rows = str.split("\n");
        result = new char[rows.length][];
        for (int r = 0; r < rows.length; r++) {
            result[r] = rows[r].toCharArray();
        }
        
        return result;
    }

    /* 
     * display the map of specific level
     */
    public static void showMap(){
        level = CheckInput.readInt("pelase enter the level that you want to cheek", 1, MAXLEVEL) - 1;
        readMap();
    }


    /* 
     * storage string information of all maps from tthe maps file and convert them to array
     */
    public static char[][][] mapStore() {
        String[] mapData = FileIO.readMaps();
        // Populate the maps array by converting mapData strings
        for (int i = 0; i < MAXLEVEL; i++) {
            maps[i] = stringToMap(mapData[i]);
        }

        return maps;
    }
}
      

