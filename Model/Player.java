package Model;

public abstract class Player implements MoveListener
{
  

    private String username;

    private boolean isRed;

    Game currentGame;


    public Player( String username )
    {
        this.username = username;
        
    }

    public boolean isRed()
    {
        return isRed;
    }


    public void newGame( Game g , boolean isRed)
    {
        currentGame = g;
        this.isRed = isRed;
    }


    public Game getGame()
    {
        return currentGame;
    }

}


