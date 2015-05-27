package Model;

/**
 * 
 * Abstract class for player object.
 *
 * 
 * @version May 26, 2015
 * @author Period: 2
 * @author Assignment: FinalAPCSProj
 *
 */
public abstract class Player
{

    private String username;

    private boolean isRed;

    private boolean isMyTurn;

    Game currentGame;

    private int numWins = 0;

    private int numLosses = 0;

    private int numDraws = 0;


    /**
     * sets player's username
     * 
     * @param username
     */
    public Player( String username )
    {
        this.username = username;

    }


    /**
     * 
     * returns if player is red.
     * 
     * @return
     */
    public boolean isRed()
    {
        return isRed;
    }


    /**
     * 
     * declares new game and sets turn to red player.
     * 
     * @param g
     * @param isRed
     */
    public void newGame( Game g, boolean isRed )
    {
        currentGame = g;
        this.isRed = isRed;

        isMyTurn = isRed;
        // if(isMyTurn)
        // {
        // moveHappened(Move.firstMove());
        // }
    }


    /**
     * checks to see who won and lost when game is over. TODO Write your method
     * description here.
     * 
     * @param isRedWinner
     */
    public void gameOver( boolean isRedWinner )
    {
        currentGame = null;
        if ( isRedWinner == isRed )
            numWins++;
        else
            numLosses++;
    }


    /**
     * 
     * doMove abstract method
     */
    public abstract void doMove();


    /**
     * 
     * returns true if current game is not null.
     * 
     * @return
     */
    public boolean inGame()
    {
        return currentGame != null;
    }


    /**
     * 
     * return current game.
     * 
     * @return
     */
    public Game getGame()
    {
        return currentGame;
    }

}
