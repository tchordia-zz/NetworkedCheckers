package Model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;


public class CheckerBoard
{
    char[][] board;

    HashSet<Point> redPieces = new HashSet<Point>();

    HashSet<Point> blackPieces = new HashSet<Point>();

    private boolean isRedTurn = true;

    public static final char RED_CHECKER = 'r';

    public static final char BLACK_CHECKER = 'b';

    public static final char NULL_SPACE = '.';

    public static final char EMPTY_SQUARE = ' ';

    public static final char RED_KING = 'R';

    public static final char BLACK_KING = 'B';

   

   
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
        
        moves = new Stack<Move>();
        this.game = game;
        initPieceList();
        System.out.println(this);
        
        
    }
   

    public int getNumRed()
    {
        return redPieces.size();
        
    }
    public int getNumBlack()
    {
        return blackPieces.size();
    }

    private void initPieceList()
    {
        for ( int row = 0; row < board.length; row++ )
        {
            for ( int col = 0; col < board[0].length; col++ )
            {
                if ( board[row][col] == 'r' )
                {
                    redPieces.add( new Point( row, col ) );
                }
                else if ( board[row][col] == 'b' )
                {
                    blackPieces.add( new Point( row, col ) );
                }

            }
        }
    }


    boolean isGameOver()
    {

        Set<Point> myList = isRedTurn?redPieces:blackPieces;
        if(myList.isEmpty())
        {
            return true;
        }
        for(Point loc:myList)
        {
            if (listSimpleMoves(loc.x, loc.y).size() != 0)
            {
              return false;  
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
        int sr = m.getStartRow();
        int sc = m.getStartCol();
        int er = m.getEndRow();
        int ec = m.getEndCol();
        if ( !inBounds( m.getStartRow(), m.getStartCol() )
            || !inBounds( m.getEndRow(), m.getEndCol() ) )
            return false;
        if ( m.isRed() != isRed( m.getStartRow(), m.getStartCol() ) )
        {
            System.out.println("not your piece");
            return false;
        }
        if ( !m.isSimpleMove() && !m.isJump() )
        {
            System.out.println("Move must be a simple move or a jump");
            return false;
        }
        if ( ( board[m.getEndRow()][m.getEndCol()] != ' ' ) )
        {
            System.out.println("End square occupied");
            return false;
        }
        if ( m.isKingMove() )
        {
            System.out.println("not a king");
            if ( !isKing( sr, sc ) )
            {
                return false;
            }
        }
        if ( m.isJump() )
        {
            if ( !Character.isLetter( board[( m.getEndRow() + m.getStartRow() ) / 2][( m.getEndCol() + m.getStartCol() ) / 2] ) )
            {
                System.out.println("that square is empty!");
                return false;
                
            }
            if ( isRed( ( m.getEndRow() + m.getStartRow() ) / 2,
                ( m.getEndCol() + m.getStartCol() ) / 2 ) == m.isRed() )
            {
                System.out.println("Can't capture own piece");
                return false;
            }

        }
        if ( m.isSimpleMove() && areJumps( m.isRed() ) )
        {
            return false;
        }
        return true;
    }


    private boolean areJumps( boolean isRed )
    {

        Set<Point> temp = isRed ? redPieces : blackPieces; // select
                                                           // either list
                                                           // of black
                                                           // pieces or
                                                           // red pieces
        char myLet = isRed ? 'r' : 'b'; // if isRed, char = 'r' else char = 'b'
        char oLet = isRed ? 'b' : 'r'; // olet is the other char
        int row;
        int col;
        for ( Point loc : temp ) // Iterate through list of pieces, check if
                                 // they can jump
        {
            row = loc.x;
            col = loc.y;

            int a = isRed ? -1 : 1;
            for ( int b = -1; b < 2; b += 2 )
            {
                try{
                if ( board[row + a][col + b] == oLet
                    && board[row + 2 * a][col + 2 * b] == ' ' )
                {
                    return true;
                }
                }
                catch(ArrayIndexOutOfBoundsException e)
                {
                    
                }
            }

            if ( Character.isUpperCase( board[row][col] ) )
            {
                a = -a;
                for ( int b = -1; b < 2; b += 2 )
                {
                    if ( board[row + a][col + b] == oLet
                        && board[row + 2 * a][col + 2 * b] == ' ' )
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }


    private boolean isKing( int row, int col )
    {
        return Character.isUpperCase( board[row][col] );
    }


    private boolean isRed( int row, int col )
    {
        return ( Character.isLetter( board[row][col] ) && Character.toLowerCase( board[row][col] ) == 'r' );

    }


    public boolean doMove( Move m )
    {
        int sr = m.getStartRow();
        int sc = m.getStartCol();
        int er = m.getEndRow();
        int ec = m.getEndCol();
        Set<Point> myset = m.isRed() ? redPieces : blackPieces;

        Set<Point> oset = m.isRed() ? blackPieces : redPieces;

        if ( !isLegal( m ) )
        {
            System.out.println("illegal move");
            return false;
        }
        char a = board[m.getStartRow()][m.getStartCol()];
        board[m.getStartRow()][m.getStartCol()] = ' ';
        board[m.getEndRow()][m.getEndCol()] = a;
        myset.remove( new Point( sr, sc ) );
        myset.add( new Point( er, ec ) );

        if ( m.isJump() )
        {
            board[( sr + er ) / 2][( sc + ec ) / 2] = ' ';
            oset.remove( new Point( ( sr + er ) / 2, ( sc + ec ) / 2 ) );

        }

        if ( er == 0 || er == board.length ) // if the move ends in the end row,
                                             // king the piece
        {
            board[er][ec] = Character.toUpperCase( board[er][ec] );
           
        }
        isRedTurn = !isRedTurn;
        moves.push( m );    
        isGameOver();
       
        System.out.println(this);
        return true;
    }
  
    
   
    public boolean isRedTurn()
    {
        return isRedTurn;
    }
    @Override
    public String toString()
    {
        String ret = "";
        for(char[] a:board)
        {
            for(char b:a)
            {
                ret+= b;
            }
            ret+= '\n';
        }
        return ret;

    }
}
