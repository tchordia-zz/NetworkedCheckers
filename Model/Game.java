package Model;

import java.util.LinkedList;
import java.util.List;


public class Game
{
    Player red;

    Player black;

    private CheckerBoard board;

    public static final boolean RED = true;

    List<MoveListener> listeners = new LinkedList<MoveListener>();


    public Game( Player redP, Player blackP )
    {
        board = new CheckerBoard(this);
        red = redP;
        red.newGame( this, RED );
        black = blackP;
        black.newGame( this, !RED );

    }


    public boolean doMove( Move move )
    {

        if ( !board.isLegal( move ) )
        {
            return false;
        }
        // TODO: board.doMove();
        //
        if ( move.isRed() )
        {
            red.doMove();
        }
        else
        {
            black.doMove();
        }
        if ( board.isGameOver() )
        {
            gameOver();
        }
        else
            informListeners( move );

        return true;
    }


    public void addMoveListener( MoveListener m )
    {
        listeners.add( m );
    }


    private void informListeners( Move m )
    {
        for ( MoveListener a : listeners )
        {
            a.moveHappened( m );
        }
    }


    private void gameOver()
    {
        for ( MoveListener a : listeners )
        {
            a.gameOver();
        }
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
