package Model;

import java.util.LinkedList;
import java.util.List;


public class Game
{
    Player red;

    Player black;

    private CheckerBoard board;

    public static final boolean RED = true;



    public Game( Player redP, Player blackP )
    {
        board = new CheckerBoard( this );
        red = redP;
        red.newGame( this, RED );
        black = blackP;
        black.newGame( this, !RED );

    }


    public boolean doMove( Move move )
    {

        return board.doMove( move );
    }


    public void addMoveListener( MoveListener m )
    {
        board.addMoveListener( m );
    }

    public boolean isRedTurn()
    {
        return board.isRedTurn();
    }
    public CheckerBoard getBoard()
    {
        return board;
    }


    public static void main( String[] args )
    {
        Player a = new HumanPlayer( "Bob" );
        Player b = new HumanPlayer( "Sally" );
        Game game = new Game( a, b );
    }

}
