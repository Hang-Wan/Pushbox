/**
 * launcher of game
 * Crate and call GameManager
 * @author Hang Wan
 */

public class LaunchGame {

    /* 
     * call game menu
     */
    public static void main(String[] args) {
        GameManager gameManager = new GameManager(); //Crate GameManager 
        gameManager.menu();
    }
}