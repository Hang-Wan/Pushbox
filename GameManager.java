/**
 * some method about
 * menu and text of game
 * call specific method in
 * menu depents on choice of user 
 * @author Hang Wan
 */

public class GameManager {
    private int choice; //the choice of user

    /* 
     * the main menu of game. 
     */
    public void menu(){
        FileIO.crateRecord();
        FileIO.readRecord();
        showTitle();
        do{
            GameMap.loadMap();
            System.out.println("1.Start Game");
            System.out.println("2.Viewing a specific map");
            System.out.println("3.Show Recording");
            System.out.println("4.Exit");
            System.out.println();
            choice = CheckInput.readInt("Please entry your choice: ", 1, 4);
            System.out.println();
            switch (choice) {
                case 1:
                    Logical.startGame();
                    break;
                
                case 2:
                    GameMap.showMap();
                    break;

                case 3:
                    showList();
                    break;

                case 4:
                    break;

                default:
                    break;
        }}while(choice != 4);
        System.out.println("Game End!");
    }



    /* 
     * read and show record list form steps array. 
     */
    public void showList() {
        System.out.println("");
        for(int i = 0; i < GameMap.MAXLEVEL; i++){
            System.out.println(i + 1 +". " + GameMap.steps[i] );
        }
        System.out.println("");
    }

    /**
    * display the title
    */
    public void showTitle() {
        System.out.println("-".repeat(10) + "Push Box" + "-".repeat(10));
        System.out.println("");
    }
}
