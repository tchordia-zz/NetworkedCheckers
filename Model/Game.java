package Model;



/**
 * A testing class that served as the main before the GUI was written. Uses two players and calls each in succession
 *
 *  @author  Tanmay
 *  @version May 26, 2015
 *  @author  Period: TODO
 *  @author  Assignment: FinalAPCSProj
 *
 *  @author  Sources: TODO
 */
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
        
        board = new CheckerBoard();
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



/**
 * 
 * TODO Write your method description here.
 * @return
 */
    public boolean isRedTurn()
    {
        return board.isRedTurn();
    }
    public CheckerBoard getBoard()
    {
        return board;
    }
/**
 * toString converts board array list into a string
 */
    @Override
    public String toString()
    {
        if(board == null)
        {
            System.err.println("Board is null");
        }
        return board.toString();
    }
    /**
     * 
     * main method to test between two players.
     * @param args
     */
    public static void main( String[] args )
    {
       
        Player a = new TextHumanPlayer( "Bob" );
        Player b = new TextHumanPlayer( "Sally" );
        Game game = new Game( a, b );
    }

}
