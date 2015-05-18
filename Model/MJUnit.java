package Model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *  
 *
 *  @author  Tanmay Chordia
 *  @version May 18, 2015
 *  @author  Period: 2
 *  @author  Assignment: APCSFinal
 *
 *  @author  Sources: TODO
 */
public class MJUnit
{

    
    /**
     * TODO Write your method description here.
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
    }


    /**
     * TODO Write your method description here.
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    }


    /**
     * TODO Write your method description here.
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
    }


    /**
     * TODO Write your method description here.
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception
    {
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
        assertTrue(c.getNumRed() == 12);
        
        Move[] b = {new Move(5,0,4,1,true), new Move(2,1,3,2,false), new Move(5,2,4,3,true), new Move(3,2,5,0,false)  };
        for(Move a:b)
        {
            c.doMove( a );
        }
        assertTrue(c.getNumRed() == 11);
       
        
    }


    /**
     * Test method for {@link Model.CheckerBoard#getNumBlack()}.
     */
    @Test
    public void testGetNumBlack()
    {
        fail( "Not yet implemented" );
    }


    /**
     * Test method for {@link Model.CheckerBoard#isGameOver()}.
     */
    @Test
    public void testIsGameOver()
    {
        fail( "Not yet implemented" );
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
        fail( "Not yet implemented" );
    }


    /**
     * Test method for {@link Model.CheckerBoard#isRedTurn()}.
     */
    @Test
    public void testIsRedTurn()
    {
        fail( "Not yet implemented" );
    }


    /**
     * Test method for {@link Model.CheckerBoard#inCompoundMove()}.
     */
    @Test
    public void testInCompoundMove()
    {
        fail( "Not yet implemented" );
    }


    /**
     * Test method for {@link Model.CheckerBoard#toString()}.
     */
    @Test
    public void testToString()
    {
        fail( "Not yet implemented" );
    }

}
