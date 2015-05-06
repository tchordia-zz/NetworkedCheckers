package Model;



public class Game
{
    Player red;

    Player black;

    private CheckerBoard board;

    public static final boolean RED = true;



    /**
     * @param redP the red player object
     * @param blackP the black player object
     */
    public Game( Player redP, Player blackP )
    {
        red = redP;
        red.newGame( this, RED );
      
        black = blackP;  
        black.newGame( this, !RED );
        
        board = new CheckerBoard( this );
        while(!board.isGameOver())
        {
            Player c = board.isRedTurn()?red:black;
            c.doMove();
        }
   

    }


    /**
     * Execute the Checkerboard's 
     * @param move
     * @return
     */
    public boolean doMove( Move move )
    {

        return board.doMove( move );
    }




    public boolean isRedTurn()
    {
        return board.isRedTurn();
    }
    public CheckerBoard getBoard()
    {
        return board;
    }

    @Override
    public String toString()
    {
        if(board == null)
        {
            System.out.println("WHAT");
        }
        return board.toString();
    }
    
    public static void main( String[] args )
    {
       System.out.println("GELLO");
        Player a = new TextHumanPlayer( "Bob" );
        Player b = new TextHumanPlayer( "Sally" );
        Game game = new Game( a, b );
    }

}
