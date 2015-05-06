package Model;

public abstract class Player 
{

    private String username;

    private boolean isRed;

    private boolean isMyTurn;

    Game currentGame;
    
    private int numWins = 0;
    private int numLosses = 0;
    private int numDraws = 0;
    


    public Player( String username )
    {
        this.username = username;

    }


    public boolean isRed()
    {
        return isRed;
    }


    public void newGame( Game g, boolean isRed )
    {
        currentGame = g;
        this.isRed = isRed;

        isMyTurn = isRed;
//        if(isMyTurn)
//        {
//            moveHappened(Move.firstMove());
//        }
    }
    public void gameOver(boolean isRedWinner)
    {
       currentGame = null;
       if(isRedWinner == isRed)
           numWins++;
       else
           numLosses++;
    }
    public abstract void doMove();

    public boolean inGame()
    {
        return currentGame!=null;
    }
    public Game getGame()
    {
        return currentGame;
    }

}
