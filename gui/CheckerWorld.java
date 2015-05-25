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
 * OthelloWorld.java
 * 
 * An <CODE>OthelloWorld</CODE> object represents an Othello world.
 * 
 * @author TODO Your Name
 * @author TODO Id nnnnnnn
 * @version TODO Date
 * @author Period: TODO
 * @author Assignment: GWOthello
 * 
 * @author Sources: TODO I received help from ...
 */
public class CheckerWorld extends World<Piece> implements CheckerBoardGui
{
    /** The Checker game */
    private CheckerBoard game;

    /**
     * A semaphore to prevent getPlayerLocation from executing before
     * setPlayerLocation
     */
    private Semaphore lock;

    /** The last selected player location */
    private Location playerLocation;

    boolean inMove = false;

    Location lastLoc = null;


    /**
     * Construct an Othello world game The Othello game
     */
    public CheckerWorld()
    {
        super( new BoundedGrid<Piece>( 8, 8 ) );

        this.game = new CheckerBoard( this );
        lock = new Semaphore( 0 );
        playerLocation = null;
        setMessage( "Checkers: not connected to other player" );

        System.setProperty( "info.gridworld.gui.selection", "hide" );
        System.setProperty( "info.gridworld.gui.tooltips", "hide" );
        System.setProperty( "info.gridworld.gui.watermark", "hide" );
        
        BoundedGrid<Piece> a = new BoundedGrid<Piece>(8,8);

        updateCheckers();

        String inputValue = JOptionPane.showInputDialog("Please input a value");
        game.connect(inputValue);

    }

    

    /**
     * TODO Write your method description here.
     */
    private void updateCheckers()
    {
        char[][] b = game.getBoard();

        for ( int x = 0; x < b.length; x++ )
        {
            for ( int y = 0; y < b[x].length; y++ )
            {
                if ( b[x][y] == 'b' )
                {
                    add( new Location( x, y ), new Piece( Color.BLACK ) );
                }
                else if ( b[x][y] == 'r' )
                {
                    add( new Location( x, y ), new Piece( Color.RED ) );
                }
                else if( b[x][y] == 'R' )
                {
                    add( new Location( x, y ), new Piece( Color.PINK ) );

                }
                else if( b[x][y] == 'B' )
                {
                    add( new Location( x, y ), new Piece( Color.GRAY ) );

                }
                else
                {

                    remove( new Location(x,y) );
                }
            }
        }
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
        System.out.println( loc );
        if ( lastLoc == null )
        {

            lastLoc = loc;
        }
        else
        {
            Move m = new Move( lastLoc.getRow(),
                lastLoc.getCol(),
                loc.getRow(),
                loc.getCol(),
                game.isBoardRed() );
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
     * Gets the last player location chosen by the human player.
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
            Thread.sleep( 2000 );
        }
        catch ( InterruptedException e )
        {
            System.out.println( "InterruptedException occurred." );
        }
    }


    public static void main( String args[] )
    {
        try
        {
            System.out.println( InetAddress.getLocalHost()
                .toString()
                .substring( InetAddress.getLocalHost()
                    .toString()
                    .indexOf( "/" ) ) );
        }
        catch ( UnknownHostException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        new CheckerWorld().show();
    }


    @Override
    public void doMove( Move m )
    {
        updateCheckers();

    }
}
