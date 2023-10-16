import java.util.Scanner;

/**
 * Logical class, including the locgical method,
 * realize the fimction of game,
 * such as move, receive and deal with command form user.
 * @author Hang Wan
 */
public class Logical {
    private static Scanner in = new Scanner(System.in); //Scanner in
    
    /* 
     * Iterate through maps array of level to find
     * the location of player and return the location
     */
    public static Player getPlayer(){
        int x = 0 ; //x location of player
        int y = 0; //y location of player
        Player.PlayerOn on; //which element of map that palyer stand on.
        for(int i = 0; i < GameMap.SIZE; i++){
            for(int j = 0; j < GameMap.SIZE; j++){
                if(GameMap.maps[GameMap.level][i][j] == GameMap.MAN || GameMap.maps[GameMap.level][i][j] == GameMap.ON){
                    x = i;
                    y = j;
                }
            }
        }
        on = checkPlayer(x, y);
        return new Player(x, y, on);
    }

    /* 
     * Detects whether the player is on the space or on the target
     */
    public static Player.PlayerOn checkPlayer(int x, int y){
        if(GameMap.maps[GameMap.level][x][y] == GameMap.MAN){
            return Player.PlayerOn.SPACE;
        }else if(GameMap.maps[GameMap.level][x][y] == GameMap.ON){
            return Player.PlayerOn.DES;
        }
        return null;
    }


    /* 
     * Iterate the map check if there have destination with out box on the map
     * to check if the game has pass.
     */
    public static boolean checkPass(int step){
        boolean ifPass = true; //confirm if player pass current level
        for(int i = 0; i < GameMap.SIZE; i++){
            for(int j = 0; j < GameMap.SIZE; j++){
                if(GameMap.maps[GameMap.level][i][j] == GameMap.DES || GameMap.maps[GameMap.level][i][j] == GameMap.ON){
                    ifPass = false;
                    return ifPass;
                }}
        }
        GameMap.readMap();
        System.out.println("Step count: "+ step);
        /* 
        * when crate a new record, renew record file.
        */
        if(step < GameMap.steps[GameMap.level] || GameMap.steps[GameMap.level] ==0){
            GameMap.steps[GameMap.level] = step;
            FileIO.writeRecord();
            FileIO.readRecord();
            System.out.println("you have set a new record! ");
            System.out.println("your best record is: "+ step);
        }
        return ifPass;
    }

    /* 
     * check the level that player have challenged.
     */
    public static int checkRecord(){
        int bestLevel = GameMap.MAXLEVEL; //the best record level
        for(int i = 0; i < GameMap.MAXLEVEL; i++){
            if(GameMap.steps[i] == 0){
                bestLevel = i + 1;
                break;
            }
        }
        return bestLevel;
    }


    /* 
     * Determine how a player's move changes the map.
     */
    public static void checkMove(int x, int y, int direction_x, int direction_y) {

        if(GameMap.maps[GameMap.level][x + direction_x][y + direction_y] == GameMap.SPACE ){    //next step is space
            GameMap.maps[GameMap.level][x + direction_x][y + direction_y] = GameMap.MAN;
            GameMap.maps[GameMap.level][x][y] = GameMap.player.getStatus();
        }
        else if(GameMap.maps[GameMap.level][x + direction_x][y + direction_y] == GameMap.DES){   //next step is destination
            GameMap.maps[GameMap.level][x + direction_x][y + direction_y] = GameMap.ON;
            GameMap.maps[GameMap.level][x][y] = GameMap.player.getStatus();
        }
        //next step is box and box next to sapce in same direction
        else if(GameMap.maps[GameMap.level][x + direction_x][y + direction_y] == GameMap.BOX && GameMap.maps[GameMap.level][x + direction_x * 2][y + direction_y * 2] == GameMap.SPACE){
            GameMap.maps[GameMap.level][x + direction_x * 2][y + direction_y * 2] = GameMap.BOX;
            GameMap.maps[GameMap.level][x + direction_x][y + direction_y] = GameMap.MAN;
            GameMap.maps[GameMap.level][x][y] = GameMap.player.getStatus();
        }
        //next step is box and box next to destination in same direction
        else if(GameMap.maps[GameMap.level][x + direction_x][y + direction_y] == GameMap.BOX && GameMap.maps[GameMap.level][x + direction_x * 2][y + direction_y * 2] == GameMap.DES){
            GameMap.maps[GameMap.level][x + direction_x * 2][y + direction_y * 2] = GameMap.GOOD;
            GameMap.maps[GameMap.level][x + direction_x][y + direction_y] = GameMap.MAN;
            GameMap.maps[GameMap.level][x][y] = GameMap.player.getStatus();
            }

        //next step is box on the destination and box next to space in same direction
        else if(GameMap.maps[GameMap.level][x + direction_x][y + direction_y] == GameMap.GOOD && GameMap.maps[GameMap.level][x + direction_x * 2][y + direction_y * 2] == GameMap.SPACE){
            GameMap.maps[GameMap.level][x + direction_x * 2][y + direction_y * 2] = GameMap.BOX;
            GameMap.maps[GameMap.level][x + direction_x][y + direction_y] = GameMap.ON;
            GameMap.maps[GameMap.level][x][y] = GameMap.player.getStatus();
        }
        //next step is box on the destination and box next to destination in same direction
        else if(GameMap.maps[GameMap.level][x + direction_x][y + direction_y] == GameMap.GOOD && GameMap.maps[GameMap.level][x + direction_x * 2][y + direction_y * 2] == GameMap.DES){
            GameMap.maps[GameMap.level][x + direction_x * 2][y + direction_y * 2] = GameMap.GOOD;
            GameMap.maps[GameMap.level][x + direction_x][y + direction_y] = GameMap.ON;
            GameMap.maps[GameMap.level][x][y] = GameMap.player.getStatus();
            }
    }

    /* 
     * read and deal with move
     * command form user.
     */
    public static char readMove() {
        char move =' ';//initialize the command
        try {
            System.out.print("Please enter your action: ");  //avoid incorrect input
            move = in.nextLine().toUpperCase().charAt(0);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println();
            System.out.println("Please entry correct command");
        }
        return move;
    }

    /* 
     * receive  command from user
     * and process the game 
     */
    public static void startGame(){
        int x;//the row of character
        int y;//the column of character
        int direction_x;// direction row of move 
        int direction_y;// direction column of move 
        char move; // the action of character which entered by player
        int levelRecord; // the level that player have challenged.
        int step;// the step of level
        levelRecord = checkRecord();
        GameMap.level = CheckInput.readInt("pelase enter the level that you want to challenge(You can't pick a level that you haven't challenged): ", 1, levelRecord) - 1;
        System.out.println("left=4 or A, right=6 or D, up=8 or W, down = 2 or S, exit game = E");
        step = 0;
        do{
            direction_x = 0;
            direction_y = 0;
            GameMap.player = getPlayer();
            x = GameMap.player.x_Location();
            y = GameMap.player.y_location();
            GameMap.readMap();
            System.out.println("Step count: "+ step);
            move = readMove();   
            switch (move) {
                case 'W', '8':
                    direction_x = -1;            
                    break;

                case 'S', '2':
                    direction_x = 1; 
                    break;
                
                case 'A', '4':
                    direction_y = -1; 
                    break;

                case 'D', '6':
                    direction_y = 1;     
                    break;

                default:
                    break;
        }
            checkMove(x, y, direction_x, direction_y);
            step++;
            if(checkPass(step)){
                System.out.println("You succeeded!");
                System.out.println("countiune next level?");
                if(CheckInput.readYesNo("please entry correct choice")){
                    step = 0;
                    GameMap.level += 1;
                }else{
                    break;
                }
            }
        }while(move != 'E');
        System.out.println("Game Stop!");
        System.out.println("Return to menu.");
        System.out.println();
    }

}