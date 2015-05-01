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
        board = new CheckerBoard();
        red = redP;
        red.newGame( this, RED );
        black = blackP;
        black.newGame( this, !RED );
        addMoveListener(red);
        addMoveListener(black);
    }


    public boolean doMove(Move move)
    {
        
        if(!board.isLegal(move))
        {
            return false;
        }
        //TODO: board.doMove();
        //
        informListeners(move);
        return true;
    }
    public void addMoveListener(MoveListener m)
    {
        listeners.add(m);
    }
    private void informListeners(Move m)
    {
        for(MoveListener a: listeners)
        {
            a.moveHappened(m);
        }
    }

}
