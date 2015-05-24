package Model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;


/**
 * 
 *
 * @author Tanmay Chordia
 * @version May 18, 2015
 * @author Period: 2
 * @author Assignment: APCSFinal
 *
 * @author Sources: TODO
 */
public class MJUnit
{

    private final Move[] p1 = { new Move( 5, 0, 4, 1, true ),
        new Move( 2, 1, 3, 2, false ), new Move( 5, 2, 4, 3, true ),
        new Move( 3, 2, 5, 0, false ) };

    private final int[][] b1 = { { 5, 2, 4, 3 }, { 2, 1, 3, 2 }, { 4, 3, 2, 1 } };

    private final int[][] b2 = { { 5, 2, 4, 3 }, { 2, 1, 3, 2 } };


    private List<Move> g( int[][] mat )
    {
        List<Move> m = new LinkedList<Move>();

        boolean b = true;
        for ( int[] a : mat )
        {
            m.add( new Move( a[0], a[1], a[2], a[3], b ) );
            b = !b;

        }
        return m;
    }


    private CheckerBoard ib( int[][] mat )
    {
        CheckerBoard c = new CheckerBoard();
        List<Move> a = g( mat );
        for ( Move b : a )
        {
            c.doMove( b );
        }
        return c;
    }


    /**
     * Test method for {@link Model.CheckerBoard#CheckerBoard(Model.Game)}.
     */
    @Test
    public void testCheckerBoard()
    {

        fail( "Not yet implemented" );
    }


    /**
     * Test method for {@link Model.CheckerBoard#getNumRed()}.
     */
    @Test
    public void testGetNumRed()
    {
        CheckerBoard c = new CheckerBoard();
        assertTrue( c.getNumRed() == 12 );

        for ( Move a : p1 )
        {
            c.doMove( a );
        }
        assertTrue( c.getNumRed() == 11 );

    }


    /**
     * Test method for {@link Model.CheckerBoard#getNumBlack()}.
     */
    @Test
    public void testGetNumBlack()
    {
        CheckerBoard c = new CheckerBoard();
        assertTrue( c.getNumBlack() == 12 );
        c = ib( b1 );
        assertTrue( c.getNumBlack() == 11 );

    }


    /**
     * Test method for {@link Model.CheckerBoard#isGameOver()}.
     */
    @Test
    public void testIsGameOver()
    {
        CheckerBoard c = new CheckerBoard();

        assertTrue( c.getNumBlack() == 0 );
        assertTrue( c.getNumRed() == 0 );
    }


    /**
     * Test method for {@link Model.CheckerBoard#isLegal(Model.Move)}.
     */
    @Test
    public void testIsLegal()
    {
        fail( "Not yet implemented" );
    }


    /**
     * Test method for {@link Model.CheckerBoard#doMove(Model.Move)}.
     */
    @Test
    public void testDoMove()
    {
        fail( "Not yet implemented" );
    }


    /**
     * Test method for {@link Model.CheckerBoard#hasJumps(int, int)}.
     */
    @Test
    public void testHasJumps()
    {
        CheckerBoard c = ib( b2 );
        System.out.println( c );
        assertTrue( c.hasJumps( 1, 2 ) );
        assertTrue( !c.hasJumps( 2, 3 ) );
        assertTrue( !c.hasJumps( 2, 1 ) );

    }


    /**
     * Test method for {@link Model.CheckerBoard#isRedTurn()}.
     */
    @Test
    public void testIsRedTurn()
    {
        CheckerBoard c = ib( b1 );
        assertTrue( !c.isRedTurn() );
    }


    /**
     * Test method for {@link Model.CheckerBoard#inCompoundMove()}.
     */
    @Test
    public void testInCompoundMove()
    {
        fail( "Not yet implemented" );
    }

}
