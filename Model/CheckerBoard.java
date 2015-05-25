package Model;

import gui.CheckerWorld;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import network.ChatConnectionHandler;
import network.ChatDisplay;
import network.SocketName;


/**
 * Class that represents a checkerboard. Implements the checkerboard as a 2d
 * array of chars, with r representing red and b representing black. '.' is null
 * space and ' ' is empty space. Capital letters are used for kings
 *
 * @author tchordia619
 * @version May 6, 2015
 * @author Period: TODO
 * @author Assignment: APCSFinal
 *
 * @author Sources: TODO
 */

public class CheckerBoard implements ChatDisplay
{
    char[][] board;

    public static final int port = 1337;

    HashSet<Point> redPieces = new HashSet<Point>();

    HashSet<Point> blackPieces = new HashSet<Point>();

    private boolean isRedTurn = true;

    private boolean gameStarted = false;

    private ChatConnectionHandler networker;

    CheckerWorld gui;

    private boolean isBoardRed;

    public static final char RED_CHECKER = 'r';

    public static final char BLACK_CHECKER = 'b';

    public static final char NULL_SPACE = '.';

    public static final char EMPTY_SQUARE = ' ';

    public static final char RED_KING = 'R';

    public static final char BLACK_KING = 'B';

    public static final char[][] initC = {
        { '.', 'b', '.', 'b', '.', 'b', '.', 'b' },

        { 'b', '.', 'b', '.', 'b', '.', 'b', '.' },
        { '.', 'b', '.', 'b', '.', 'b', '.', 'b' },
        { ' ', '.', ' ', '.', ' ', '.', ' ', '.' },
        { '.', ' ', '.', ' ', '.', ' ', '.', ' ' },
        { 'r', '.', 'r', '.', 'r', '.', 'r', '.' },
        { '.', 'r', '.', 'r', '.', 'r', '.', 'r' },
        { 'r', '.', 'r', '.', 'r', '.', 'r', '.' } };

    protected static final char[][] testA = {
        { '.', 'b', '.', 'b', '.', 'b', '.', 'b' },
        { 'b', '.', 'b', '.', 'b', '.', 'b', '.' },
        { '.', 'b', '.', 'b', '.', 'b', '.', 'b' },
        { ' ', '.', ' ', '.', ' ', '.', ' ', '.' },
        { '.', ' ', '.', ' ', '.', ' ', '.', ' ' },
        { 'r', '.', 'r', '.', 'r', '.', 'r', '.' },
        { '.', 'r', '.', 'r', '.', 'r', '.', 'r' },
        { 'r', '.', 'r', '.', 'r', '.', 'r', '.' } };

    private boolean inCompoundMove = false;

    private int currentRow = -1; // only for use in compound moves;

    private int currentCol = -1;

    private Stack<Move> moves;

    /** Data model for connections list */
    protected DefaultListModel connModel;

    /** List of active connections */
    protected JList connections;


    /**
     * @param game
     *            the game instance that created this checkerboard
     */
    public CheckerBoard( CheckerWorld c )
    {

        connModel = new DefaultListModel();
        connections = new JList( connModel );

        networker = new ChatConnectionHandler( this, port );
        gui = c;

        board = initC;
        isRedTurn = true;

        moves = new Stack<Move>();

        initPieceList();
        // System.out.println( this );

    }


    /**
     * DO NOT USE
     */
    protected CheckerBoard()
    {
        this( null );
    }


    /**
     * 
     * @return the number of red pieces
     */
    public int getNumRed()

    {
        return redPieces.size();

    }


    public boolean isBoardRed()
    {
        return isBoardRed;
    }


    /**
     * 
     * @return total number of black pieces
     */
    public int getNumBlack()
    {
        return blackPieces.size();
    }


    /**
     * initialize the piece list with the original game board
     */
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


    /**
     * TODO Write your method description here.
     * 
     * @return checks if the game is over
     */
    boolean isGameOver()
    {

        Set<Point> myList = isRedTurn ? redPieces : blackPieces;
        if ( myList.isEmpty() )
        {
            return true;
        }
        for ( Point loc : myList )
        {
            if ( listSimpleMoves( loc.x, loc.y ).size() != 0 )
            {
                return false;
            }
        }
        if ( areJumps( isRedTurn ) )
        {
            return false;
        }

        return true;
    }


    /**
     * lists all non compound moves for a certain square
     * 
     * @param row
     * @param col
     * @return
     */
    private ArrayList<Move> listOneStepMoves( int row, int col )
    {
        ArrayList<Move> m = listSimpleMoves( row, col );
        m.addAll( listJumpMoves( row, col ) );
        return m;
    }


    /**
     * List all legal jump moves for a given square
     * 
     * @param row
     * @param col
     * @return
     */
    private ArrayList<Move> listJumpMoves( int row, int col )
    {
        ArrayList<Move> moves = new ArrayList<Move>();
        for ( int a = -2; a < 3; a += 4 )
        {
            for ( int b = -2; b < 3; b += 4 )
            {
                Move m = new Move( row, col, row + a, col + b, isRed( row, col ) );
                if ( isLegal( m ) )
                {
                    moves.add( m );
                }
            }
        }

        return moves;

    }


    public void startGame( boolean isBoardRed )
    {
        gameStarted = true;
        this.isBoardRed = isBoardRed;
        gui.setMessage( "Game Started: you are "
            + ( isBoardRed ? "red" : "black" ) );
    }


    public void endGame()
    {
        gameStarted = false;
    }


    /**
     * List all simple moves for a given square
     * 
     * @param row
     * @param col
     * @return
     */
    private ArrayList<Move> listSimpleMoves( int row, int col )
    {
        ArrayList<Move> moves = new ArrayList<Move>();
        for ( int a = -1; a < 2; a += 2 )
        {
            for ( int b = -1; b < 2; b += 2 )
            {
                Move m = new Move( row, col, row + a, col + b, isRed( row, col ) );
                if ( isLegal( m ) )
                {
                    moves.add( m );
                }
            }
        }
        return moves;

    }


    /**
     * checks if a row col pair is inbounds
     * 
     * @param eRow
     * @param eCol
     * @return
     */
    private boolean inBounds( int eRow, int eCol )
    {
        return ( 0 <= eRow && eRow < board.length && 0 <= eCol && eCol < board[0].length );
    }


    /**
     * checks if a move is legal.
     * 
     * @param m
     * @return
     */
    public boolean isLegal( Move m )
    {
        if ( !gameStarted )
        {
            return false;
        }
//        System.out.println( "herro" + m.isRed() + " "
//            + isRed( m.getStartRow(), m.getStartCol() ) );
        int sr = m.getStartRow();
        int sc = m.getStartCol();
        int er = m.getEndRow();
        int ec = m.getEndCol();
        if ( !inBounds( m.getStartRow(), m.getStartCol() )
            || !inBounds( m.getEndRow(), m.getEndCol() ) )
            return false;
        if ( m.isRed() != isRed( m.getStartRow(), m.getStartCol() ) )
        {
            System.out.println( "not your piece" );
            return false;
        }
        if ( m.isLocal() && ( isBoardRed() != m.isRed() ) )
        {
            return false;
        }
        if ( !m.isLocal() && ( isBoardRed() == m.isRed() ) )
        {
            return false;
        }
        if ( m.isRed() != isRedTurn )
        {
            return false;
        }
        if ( m.isRed() == isBlack( sr, sc ) )
            if ( !m.isSimpleMove() && !m.isJump() )
            {
                System.out.println( "Move must be a simple move or a jump" );
                return false;
            }
        if ( ( board[m.getEndRow()][m.getEndCol()] != ' ' ) )
        {
            // System.out.println( "End square occupied" );
            return false;
        }
        if ( m.isKingMove() )
        {
            System.out.println( "not a king" );
            if ( !isKing( sr, sc ) )
            {
                return false;
            }
        }
        if ( inCompoundMove && !m.isJump() )
        {
            System.out.println( "you are in a compound jump, you must jup" );
            return false;
        }

        if ( inCompoundMove )
        {
            if ( sr != currentRow || sc != currentCol )
            {
                System.out.println( "must move current piece" );
                return false;
            }
        }

        if ( m.isJump() )
        {
            if ( !Character.isLetter( board[( m.getEndRow() + m.getStartRow() ) / 2][( m.getEndCol() + m.getStartCol() ) / 2] ) )
            {
                System.out.println( "that square is empty!" );
                return false;

            }
            if ( isRed( ( m.getEndRow() + m.getStartRow() ) / 2,
                ( m.getEndCol() + m.getStartCol() ) / 2 ) == m.isRed() )
            {
                System.out.println( "Can't capture own piece" );
                return false;
            }

        }
        if ( m.isSimpleMove() && areJumps( m.isRed() ) )
        {
            return false;
        }
        return true;
    }


    //
    // /**
    // * If a multi move is legal, do it
    // *
    // * @param mo
    // * @return
    // */
    // public boolean doMove( MultiMove mo ) // TODO FIX: this method starts
    // doing
    // // the move while checking the
    // // legality of the move. must check
    // // legalitty BEFORE
    // {
    // for ( int i = 0; i < mo.size() - 1; i++ )
    // {
    // if ( mo.get( i ).getEndCol() == mo.get( i + 1 ).getStartCol()
    // && mo.get( i ).getEndRow() == mo.get( i + 1 ).getStartRow() )
    // {
    // if ( !doMove( mo.get( i ) ) )
    // {
    // return false;
    // }
    //
    // }
    // }
    // return doMove( mo.get( mo.size() - 1 ) );
    // }

    /**
     * are there legal jump moves for a certain color
     * 
     * @param isRed
     * @return
     */
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
                try
                {
                    if ( board[row + a][col + b] == oLet
                        && board[row + 2 * a][col + 2 * b] == ' ' )
                    {
                        return true;
                    }
                }
                catch ( ArrayIndexOutOfBoundsException e )
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


    /**
     * Does this square contain a king
     * 
     * @param row
     * @param col
     * @return
     */
    private boolean isKing( int row, int col )
    {
        return Character.isUpperCase( board[row][col] );
    }


    /**
     * is the piece at this square red? false does not imply black, @see
     * isBlack()
     * 
     * @param col
     * @return
     */
    private boolean isRed( int row, int col )
    {
        return ( Character.isLetter( board[row][col] ) && Character.toLowerCase( board[row][col] ) == 'r' );

    }


    /**
     * Returns true iff a piece is black, FALSE DOES NOT IMPLY RED @SEE isRed()
     * 
     * @param row
     * @param col
     * @return
     */
    private boolean isBlack( int row, int col )
    {
        return ( Character.isLetter( board[row][col] ) && Character.toLowerCase( board[row][col] ) == 'b' );

    }


    /**
     * Execute a move. first check legality
     * 
     * @param m
     * @return
     */
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
            System.out.println( "illegal move" );
            return false;
        }
        if ( m.isLocal() )
        {
            networker.send( m.toString() );
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
            if ( hasJumps( er, ec ) )
            {
                inCompoundMove = true;
                currentRow = er;
                currentCol = ec;

            }
            else
            {
                inCompoundMove = false;
                currentRow = -1;
                currentCol = -1;
            }

        }

        if ( er == 0 || er == board.length ) // if the move ends in the end row,
                                             // king the piece
        {
            board[er][ec] = Character.toUpperCase( board[er][ec] );

        }

        isRedTurn = inCompoundMove ? isRedTurn : !isRedTurn;
        moves.push( m );
        System.out.println( moves );
        isGameOver();
        gui.doMove( m );
        System.out.println( toString() );
        // System.out.println( this );
        return true;
    }


    public boolean hasJumps( int row, int col )
    {
        return listJumpMoves( row, col ).size() != 0;
    }


    /**
     * 
     * @return true if it's red turn
     */
    public boolean isRedTurn()
    {
        return isRedTurn;
    }


    public boolean inCompoundMove()
    {
        return inCompoundMove;
    }


    public void undo() // NotFunctional (YET)
    {
        if ( moves.isEmpty() )
            return;

        Move last = moves.pop();
        int sr = last.getStartRow();
        int sc = last.getStartCol();
        int er = last.getEndRow();
        int ec = last.getEndCol();

        char temp = board[last.getEndRow()][last.getEndCol()];
        board[last.getEndRow()][last.getEndCol()] = ' ';
        board[last.getStartRow()][last.getStartCol()] = temp;
        if ( last.isJump() )
        {
            board[( last.getEndRow() + last.getStartRow() ) / 2][( last.getEndCol() + last.getStartCol() ) / 2] = 'b';
        }

    }


    public char[][] getBoard()
    {
        return board;
    }


    // for test purposes/ text based game
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        String ret = "";
        for ( char[] a : board )
        {
            for ( char b : a )
            {
                ret += b;
            }
            ret += '\n';
        }
        return ret;

    }


    /**
     * @see ChatDisplay#createSocket
     */
    public synchronized void createSocket( SocketName name, boolean isRed )
    {
        connModel.addElement( name );
        startGame( isRed );
    }


    public void statusMessage( String message )
    {

    }


    /**
     * @see ChatDisplay#destroySocket
     */
    public void destroySocket( SocketName name )
    {
        if ( connModel.contains( name ) )
        {
            connModel.removeElement( name );
        }
        endGame();

    }


    @Override
    public void chatMessage( SocketName name, String message )
    {
        System.out.println( "line in checkerboard" +  Move.stringToMove( message, false ) );
        doMove( Move.stringToMove( message, false ) );

    }


    // @Override
    // public void createSocket( SocketName name, boolean isRed )
    // {
    // // TODO Auto-generated method stub
    //
    // }

    public void connect( String name )
    {
        try
        {
            SocketName sock = new SocketName( name, 1337, "Other Player" );

            try
            {
                networker.connect( sock, true );
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }

        }
        catch ( IllegalArgumentException iae )
        {
            statusMessage( "Cannot connect: " + iae.getMessage() );
        }

    }

}
