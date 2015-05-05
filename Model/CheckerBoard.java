package Model;

import java.util.ArrayList;
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

    private int numRed;

    private int numRedKings;

    private int numBlack;

    private int numBlackKings;

    private Stack<Move> moves;

    private Game game;


    public CheckerBoard( Game game )
    {
        char[][] a = { { '.', 'b', '.', 'b', '.', 'b', '.', 'b' },
            { 'b', '.', 'b', '.', 'b', '.', 'b', '.' },
            { '.', 'b', '.', 'b', '.', 'b', '.', 'b' },
            { ' ', '.', ' ', '.', ' ', '.', ' ', '.' },
            { '.', ' ', '.', ' ', '.', ' ', '.', ' ' },
            { 'r', '.', 'r', '.', 'r', '.', 'r', '.' },
            { '.', 'r', '.', 'r', '.', 'r', '.', 'r' },
            { 'r', '.', 'r', '.', 'r', '.', 'r', '.' } };
        board = a;
        isRedTurn = true;
        numRed = 12;
        numBlack = 12;
        numRedKings = 0;
        numBlackKings = 0;
        this.game = game;
    }


    protected boolean isGameOver()
    {

        for ( int a = 0; a < board.length; a++ )
        {
            for ( int b = 0; b < board[0].length; b++ )
            {
                char t = board[a][b];
                if ( isRedTurn && ( t == 'r' || t == 'R' ) )
                {
                    if ( listOneStepMoves( a, b ).size() > 0 )
                        return false;
                }
                else if ( !isRedTurn && ( t == 'b' || t == 'B' ) )
                {
                    if ( listOneStepMoves( a, b ).size() > 0 )
                        return false;
                }
            }
        }
        return true;
    }


    private ArrayList<Move> listOneStepMoves( int row, int col )
    {
        ArrayList<Move> m = listSimpleMoves( row, col );
        m.addAll( listJumpMoves( row, col ) );
        return m;
    }


    private ArrayList<Move> listJumpMoves( int row, int col )
    {
        ArrayList<Move> moves = new ArrayList<>();
        if ( !Character.isLetter( board[row][col] ) )
        {
            return null;
        }
        if ( board[row][col] == 'r' || board[row][col] == 'R' )
        {
            int rowDir = -1;
            int colDir = -1;
            for ( colDir = -1; colDir < 2; colDir += 2 )
            {
                if ( inBounds( row + rowDir, col + colDir )
                    && Character.toLowerCase( board[row + rowDir][col + colDir] ) == 'b'
                    && board[row + 2 * rowDir][col + 2 * colDir] == ' ' ) // check
                // square
                {
                    moves.add( new Move( row, col, row + 2 * rowDir, col + 2
                        * colDir, Game.RED ) );
                }
            }

            if ( board[row][col] == 'R' )
            {
                rowDir = 1;

                for ( colDir = -1; colDir < 2; colDir += 2 )
                {
                    if ( inBounds( row + rowDir, col + colDir )
                        && Character.toLowerCase( board[row + rowDir][col
                            + colDir] ) == 'b'
                        && board[row + 2 * rowDir][col + 2 * colDir] == ' ' ) // check
                    // square
                    {
                        moves.add( new Move( row, col, row + 2 * rowDir, col
                            + 2 * colDir, Game.RED ) );
                    }
                }
            }

        }
        if ( board[row][col] == 'b' || board[row][col] == 'B' )
        {

            int rowDir = -1;
            int colDir;
            for ( colDir = -1; colDir < 2; colDir += 2 )
            {
                if ( inBounds( row + rowDir, col + colDir )
                    && Character.toLowerCase( board[row + rowDir][col + colDir] ) == 'r'
                    && board[row + 2 * rowDir][col + 2 * colDir] == ' ' ) // check
                // square
                {
                    moves.add( new Move( row, col, row + 2 * rowDir, col + 2
                        * colDir, !Game.RED ) );
                }
            }

            if ( board[row][col] == 'R' )
            {
                rowDir = 1;

                for ( colDir = -1; colDir < 2; colDir += 2 )
                {
                    if ( inBounds( row + rowDir, col + colDir )
                        && Character.toLowerCase( board[row + rowDir][col
                            + colDir] ) == 'r'
                        && board[row + 2 * rowDir][col + 2 * colDir] == ' ' ) // check
                    // square
                    {
                        moves.add( new Move( row, col, row + 2 * rowDir, col
                            + 2 * colDir, !Game.RED ) );
                    }
                }
            }

        }
        return moves;

    }


    private ArrayList<Move> listSimpleMoves( int row, int col )
    {
        ArrayList<Move> moves = new ArrayList<>();
        if ( !Character.isLetter( board[row][col] ) )
        {
            return null;
        }
        if ( board[row][col] == 'r' || board[row][col] == 'R'
            || board[row][col] == 'B' )
        {
            int eRow = row - 1;
            int eCol = col - 1;
            if ( inBounds( eRow, eCol ) && board[eRow][eCol] == ' ' ) // check
            // square
            {
                moves.add( new Move( row, col, eRow, eCol, Game.RED ) );
            }

            eCol = col + 1;
            if ( inBounds( eRow, eCol ) && board[eRow][eCol] == ' ' ) // check
                                                                      // square
            {
                moves.add( new Move( row, col, eRow, eCol, Game.RED ) );
            }

        }
        if ( board[row][col] == 'b' || board[row][col] == 'R'
            || board[row][col] == 'B' )
        {
            int eRow = row + 1;
            int eCol = col - 1;
            if ( inBounds( eRow, eCol ) && board[eRow][eCol] == ' ' ) // check
            // square
            {
                moves.add( new Move( row, col, eRow, eCol, !Game.RED ) );
            }

            eCol = col + 1;
            if ( inBounds( eRow, eCol ) && board[eRow][eCol] == ' ' ) // check
                                                                      // square
            {
                moves.add( new Move( row, col, eRow, eCol, !Game.RED ) );
            }

        }
        return moves;

    }


    private boolean inBounds( int eRow, int eCol )
    {
        return ( 0 <= eRow && eRow < board.length && 0 <= eCol && eCol < board[0].length );
    }


    public boolean isLegal( Move m )
    {

        if ( !inBounds( m.getStartRow(), m.getStartCol() )
            || !inBounds( m.getEndRow(), m.getEndCol() ) )
            return false;
        if ( m.isRed() != isRed( m.getStartRow(), m.getStartCol() ) )
        {
            return false;
        }
        if ( !m.isSimpleMove() && !m.isJump() )
        {
            return false;
        }
        if ( ( board[m.getEndRow()][m.getEndCol()] != ' ' ) )
        {
            return false;
        }
        if ( m.isJump() )
        {
            if ( !Character.isLetter( board[( m.getEndRow() + m.getStartRow() ) / 2][( m.getEndCol() + m.getStartCol() ) / 2] ) )
            {
                return false;
            }
            if ( isRed( ( m.getEndRow() + m.getStartRow() ) / 2,
                ( m.getEndCol() + m.getStartCol() ) / 2 ) == m.isRed() )
                ;
            {
                return false;
            }

        }
        // TODO: if m is a simple move and a jump move is possible, return false
        return true;
    }


    private boolean isRed( int row, int col )
    {
        return ( Character.isLetter( board[row][col] ) && Character.toLowerCase( board[row][col] ) == 'r' );
    }


    public boolean doMove( Move m )
    {
        if ( !isLegal( m ) )
        {
            return false;
        }
        // TODO MAKE MOVE PROCEDURE
        return true;
    }

}
