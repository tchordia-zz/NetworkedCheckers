package Model;

import java.util.Stack;

public class CheckerBoard
{
    char[][] board;

    private boolean isRedTurn = true;

    public static final char RED_CHECKER = 'r';

    public static final char BLACK_CHECKER = 'b';

    public static final char NULL_SPACE = '.';

    public static final char EMPTY_SQUARE = ' ';

    public static final char RED_KING = 'R';

    public static final char BLACK_KING = 'B';

    private Stack<Move> moves;

    public CheckerBoard()
    {
        char[][] a = {  { '.', 'b', '.', 'b', '.', 'b', '.', 'b' },
                        { 'b', '.', 'b', '.', 'b', '.', 'b', '.' },
                        { '.', 'b', '.', 'b', '.', 'b', '.', 'b' },
                        { ' ', '.', ' ', '.', ' ', '.', ' ', '.' },
                        { '.', ' ', '.', ' ', '.', ' ', '.', ' ' },
                        { 'r', '.', 'r', '.', 'r', '.', 'r', '.' },
                        { '.', 'r', '.', 'r', '.', 'r', '.', 'r' },
                        { 'r', '.', 'r', '.', 'r', '.', 'r', '.' } };
        board = a;
        isRedTurn = true;
    }


   


    public boolean isLegal( Move m )
    {
        return true; // TODO FIX
    }


    public boolean doMove( Move m )
    {
        if ( !isLegal( m ) )
        {
           return false;
        }
        //TODO MAKE MOVE PROCEDURE
        return true;
    }

}
