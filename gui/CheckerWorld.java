package gui;

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

import java.awt.Color;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Semaphore;

import javax.swing.JOptionPane;

import Model.CheckerBoard;
import Model.CheckerBoardGui;
import Model.Move;


/**
 * 
 * Based on Grid World Othello project, this class creates a grid world
 * interface. Includes a
 *
 * @version May 20, 2015
 * @author Period: 2
 * @author Assignment: FinalAPCSProj
 *
 * @author Sources: GWOthello
 */
public class CheckerWorld extends World<Piece> implements CheckerBoardGui
{

    private CheckerBoard game;

    private Semaphore lock;

    private Location playerLocation;

    boolean inMove = false;

    Location lastLoc = null;


    /**
     * Constructs the Checker world game using Gridword GUI. Creates new
     * Semaphore lock and creates an 8x8 bounded grid. Declares a new game and
     * sets the player location to null. Updates Checkers and opens a pop up
     * that takes the IP of the other player.
     */
    public CheckerWorld()
    {
        super( new BoundedGrid<Piece>( 8, 8 ) );

        this.game = new CheckerBoard( this );
        lock = new Semaphore( 0 );
        playerLocation = null;

        System.setProperty( "info.gridworld.gui.selection", "hide" );
        System.setProperty( "info.gridworld.gui.tooltips", "hide" );
        System.setProperty( "info.gridworld.gui.watermark", "hide" );

        updateCheckers();

        String inputValue = JOptionPane.showInputDialog( "Please input a value" );
        System.out.println( inputValue );
        if ( inputValue != null )
        {
            game.connect( inputValue );
        }
        else
        {
            try
            {
                setDefaultString();
            }
            catch ( UnknownHostException e )
            {
                e.printStackTrace();
            }
        }

    }


    /**
     * Sets initial message on top of the checkerboard to
     * "Checkers: not connected to other player." then lists your ip address
     * while the TCP connection has not been established.
     * 
     * @throws UnknownHostException
     *             if the TCP connection does not exist
     */
    public void setDefaultString() throws UnknownHostException
    {
        super.setMessage( "Checkers: not connected to other player. Your IP: "
            + InetAddress.getLocalHost()
                .toString()
                .substring( InetAddress.getLocalHost().toString().indexOf( "/" ) + 1 ) );
    }


    /**
     * Checks the toString of checkerboard to determine where to place the
     * checkers. Checks to see if checker is black or red. Replicates the
     * opponents move on local board based on shared array. Lets player know
     * when turn is over.
     */
    public void updateCheckers()
    {
        char[][] b = game.getBoard();

        for ( int x = 0; x < b.length; x++ )
        {
            for ( int y = 0; y < b[x].length; y++ )
            {
                int lx = x;
                int ly = y;
                // System.out.println(game.isBoardRed());
                if ( !game.isBoardRed() )
                {
                    lx = ( b.length - 1 ) - x;
                    ly = ( b[x].length - 1 ) - y;
                }
                if ( b[x][y] == 'b' )
                {
                    add( new Location( lx, ly ), new Piece( Color.BLACK ) );
                }
                else if ( b[x][y] == 'r' )
                {
                    add( new Location( lx, ly ), new Piece( Color.RED ) );
                }
                else if ( b[x][y] == 'R' )
                {
                    add( new Location( lx, ly ), new Piece( Color.PINK ) );

                }
                else if ( b[x][y] == 'B' )
                {
                    add( new Location( lx, ly ), new Piece( Color.GRAY ) );

                }
                else
                {

                    remove( new Location( lx, ly ) );
                }
            }
        }
        setMessage( "It is " + ( game.isRedTurn() ? "red" : "black" )
            + "'s turn" );
    }


    /**
     * Handles the mouse location click.
     * 
     * @param loc
     *            the location that was clicked
     * @return true because the click has been handled
     */
    @Override
    public boolean locationClicked( Location loc )
    {
        setPlayerLocation( loc );
        // System.out.println( loc );
        if ( lastLoc == null )
        {

            lastLoc = loc;
        }
        else
        {
            char[][] b = game.getBoard();
            Move m;
            if ( !game.isBoardRed() )
            {

                m = new Move( b.length - 1 - lastLoc.getRow(), b.length - 1
                    - lastLoc.getCol(), b.length - 1 - loc.getRow(), b.length
                    - 1 - loc.getCol(), game.isBoardRed() );
            }
            else
            {
                m = new Move( lastLoc.getRow(),
                    lastLoc.getCol(),
                    loc.getRow(),
                    loc.getCol(),
                    game.isBoardRed() );
            }

            game.doMove( m );
            lastLoc = null;
        }

        return true;
    }


    /**
     * Sets <CODE>playerLocation</CODE>.
     * 
     * @param loc
     *            the location to be used to set the player location
     */
    private void setPlayerLocation( Location loc )
    {
        lock.drainPermits(); // Remove all permits
        playerLocation = loc;
        lock.release(); // Allow getPlayerLocation to run once
    }


    /**
     * Gets the last player location chosen by the human player. Blocks until
     * setPlayerLocation runs
     * 
     * @return the last location chosen by the human player
     */
    public Location getPlayerLocation()
    {
        try
        {
            lock.acquire(); // Block until setPlayerLocation runs
            return playerLocation;
        }
        catch ( InterruptedException e )
        {
            throw new RuntimeException( "Had catastrophic InterruptedException" );
        }
    }


    /**
     * Sets the message in the GridWorld GUI.<br>
     * Postcondition: At least a second has elapsed before returning.
     * 
     * @param msg
     *            the message text
     */
    @Override
    public void setMessage( String msg )
    {
        super.setMessage( msg );
        try
        {
            Thread.sleep( 4000 );
        }
        catch ( InterruptedException e )
        {
            System.out.println( "InterruptedException occurred." );
        }
    }


    /**
     * 
     * Gets and prints out IP address. Sets CheckerWorld to visible.
     * 
     * @param args
     */
    public static void main( String args[] )
    {
        try
        {
            System.out.println( InetAddress.getLocalHost()
                .toString()
                .substring( InetAddress.getLocalHost().toString().indexOf( "/" ) ) );
        }
        catch ( UnknownHostException e )
        {

            e.printStackTrace();
        }
        new CheckerWorld().show();
    }


    /**
     * doMove from CheckerBoardGui interface class updates checkers
     * 
     * @param m
     */
    @Override
    public void doMove( Move m )
    {
        if ( !game.isGameOver() )
            updateCheckers();
        else
        {
            updateCheckers();
            setMessage( "GameOver" );
            int inputValue = JOptionPane.showConfirmDialog( null,
                ( game.isRedTurn() ? "Black" : "Red" ) + " won! Reset game?" );
            // System.out.println("INPUT VALUE" + inputValue);
            if ( inputValue == JOptionPane.OK_OPTION )
            {
                boolean a = game.isBoardRed();
                game.endGame();
                game.startGame( a );

            }
        }

    }
}
