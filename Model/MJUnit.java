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
     * Test method for {@link Model.CheckerBoard#getNumRed()}.
     */
    @Test
    public void testGetNumRed()
    {
        CheckerBoard c = new CheckerBoard();
        Move[] p1 = { new Move( 5, 0, 4, 1, true ),
            new Move( 2, 3, 3, 2, false ), new Move( 4, 1, 2, 3, true ),
            new Move( 1, 4, 3, 2, false ) };

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
        Move[] p1 = { new Move( 5, 0, 4, 1, true ),
            new Move( 2, 3, 3, 2, false ), new Move( 4, 1, 2, 3, true ),
            new Move( 1, 4, 3, 2, false ) };

        assertTrue( c.getNumBlack() == 12 );
        
        for ( Move a : p1 )
        {
            c.doMove( a );
        }
        assertTrue( c.getNumBlack() == 11 );

    }


    /**
     * Test method for {@link Model.CheckerBoard#isGameOver()}.
     */
    @Test
    public void testIsGameOver()
    {
        CheckerBoard c = new CheckerBoard();

        // create a gameover game

        assertTrue( c.getNumBlack() != 0 || c.getNumRed() != 0 );
    }


    /**
     * Test method for {@link Model.CheckerBoard#isLegal(Model.Move)}.
     */
    @Test
    public void testIsLegal()
    {
        CheckerBoard c = new CheckerBoard();

        Move[] rm = { new Move( 5, 0, 4, 0, true ) };
        Move[] fm = { new Move( 5, 0, 4, 0, true ) };
        Move[] wm = { new Move( 5, 0, 4, 0, true ) };
        Move[] jump = { new Move( 5, 0, 4, 0, true ) };
        Move[] king = { new Move( 5, 0, 4, 0, true ) };

        // jump to wrong place
        for ( Move a : rm )
        {
            c.doMove( a );

            if ( !c.isLegal( a ) )
            {
                assertTrue( true );
            }
        }

        // jump way to far
        for ( Move a : fm )
        {
            c.doMove( a );

            if ( !c.isLegal( a ) )
            {
                assertTrue( true );
            }
        }

        // wrong piece to move
        for ( Move a : jump )
        {
            c.doMove( a );

            if ( !c.isLegal( a ) )
            {
                assertTrue( true );
            }
        }

        // king moves
        for ( Move a : king )
        {
            c.doMove( a );

            if ( !c.isLegal( a ) )
            {
                assertTrue( true );
            }
        }
    }


    /**
     * Test method for {@link Model.CheckerBoard#doMove(Model.Move)}.
     */
    @Test
    public void testDoMove()
    {
        CheckerBoard c = new CheckerBoard();

        Move[] m = { new Move( 5, 0, 4, 1, true ) };

        // regular move
        for ( Move a : m )
        {
            c.doMove( a );

            if ( !c.isLegal( a ) )
            {
                assertTrue( true );
            }
        }
        // jump
        
    }


    /**
     * Test method for {@link Model.CheckerBoard#hasJumps(int, int)}.
     */
    @Test
    public void testHasJumps()
    {
        // move to a place
        // assert true if hasJump = possible moves
    }


    /**
     * Test method for {@link Model.CheckerBoard#isRedTurn()}.
     */
    @Test
    public void testIsRedTurn()
    {
        // CheckerBoard c = ib( b1 );
        // assertTrue( c.isRedTurn() );
    }


    /**
     * Test method for {@link Model.CheckerBoard#inCompoundMove()}.
     */
    @Test
    public void testInCompoundMove()
    {
//        CheckerBoard c = new CheckerBoard();
//        
//        int[][] m = { new Move( 5, 2, 4, 1, true), ( 2, 7, 3, 6, false ), ( 4, 1, 3, 0, true ),
//            ( 1, 6, 2, 7, false ), ( 5, 0, 4, 1, true ), ( 2, 3, 3, 4, false ), ( 6, 3, 5, 2, true ),
//            ( 3, 4, 4, 3, false ), ( 5, 2, 3, 4, true ), ( 3, 4, 1, 6, true ) };
//
//        for ( Move a : m )
//        {
//            c.doMove( a );
//
//            if ( !c.isLegal( a ) )
//            {
//                assertTrue( true );
//            }
//        }
    }
}
