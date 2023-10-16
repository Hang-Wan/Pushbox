/** 
 * record Player, 
 * including location and status of player
 * @author Hang Wan
 */
public record Player(int x_Location, int y_location, PlayerOn on){

    public enum PlayerOn{SPACE, DES}//define where player stand on.

    /* 
     * return which element of map player stand on.
     */
    public char getStatus(){
        char status; //storager elememt status of player
        if(on == PlayerOn.SPACE ){
            status = GameMap.SPACE;
        }else{
            status = GameMap.DES;
        }
        return status;
    }
}