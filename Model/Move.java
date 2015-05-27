package Model;

import java.util.StringTokenizer;

/**
 *  A class that represents on a Move. Contains a start location, and an end location
 *
 *  @version May 26, 2015
 *  @author  Period: 2
 *  @author  Assignment: FinalAPCSProj
 *
 */
public class Move
{
    private final int startRow;

    private final int startCol;

    private final int endRow;

    private final int endCol;

    private final boolean isRed;

    private final boolean isLocal;
    
    /**
     * move constructor assuming local
     * @param startRow 
     * @param startCol
     * @param endRow
     * @param endCol
     * @param isRed
     */
    public Move(
        int startRow,
        int startCol,
        int endRow,
        int endCol,
        boolean isRed )
    {

        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;

        this.isRed = isRed;
        isLocal = true;
    }
   /**
    * Constructor to check if local
    * @param startRow
    * @param startCol
    * @param endRow
    * @param endCol
    * @param isRed
    * @param isLocal
    */
    public Move(
        int startRow,
        int startCol,
        int endRow,
        int endCol,
        boolean isRed, boolean isLocal )
    {

        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;

        this.isRed = isRed;
        this.isLocal = isLocal;
    }
   
    /**
     * 
     * For network purpose, construct move object from String sent over network
     * @param s
     * @param isLocal
     * @return
     */
 public static Move stringToMove(String s, boolean isLocal)
 {
     StringTokenizer a = new StringTokenizer( s,"," );
  
     try{
     Move m = new Move(Integer.parseInt(a.nextToken()), Integer.parseInt(a.nextToken()), Integer.parseInt(a.nextToken()), Integer.parseInt(a.nextToken()), Integer.parseInt(a.nextToken())==1, isLocal);
     return m;
     }
     catch(Exception e)
     {
     return null;
     }
 }


    /**
     * The first move that is sent in the game to initialize
     * @return
     */
    public static Move firstMove()
    {
        return new Move( 0, 0, 0, 0, !Game.RED );
    }


    /**
     * @return Returns the startRow.
     */
    public int getStartRow()
    {
        return startRow;
    }


    /**
     * @return Returns the startCol.
     */
    public int getStartCol()
    {
        return startCol;
    }


    /**
     * @return Returns the endRow.
     */
    public int getEndRow()
    {
        return endRow;
    }


    /**
     * @return Returns the endCol.
     */
    public int getEndCol()
    {
        return endCol;
    }


    /**
     * @return Returns the color of the player.
     */

    public boolean isRed()
    {
        return isRed;
    }


    /**
     * @return Returns if jump is valid.
     */
    public boolean isJump()
    {

        if ( Math.abs( startCol - endCol ) == 2
            && Math.abs( startRow - endRow ) == 2 )

        {
            return true;
        }
        return false;
    }

/**
 * 
 * TODO Write your method description here.
 * @return
 */
    public boolean isSimpleMove()
    {
        if ( Math.abs( startCol - endCol ) == 1
            && Math.abs( startRow - endRow ) == 1 )
        {
            return true;
        }
        return false;
    }
    /**
     * @return Returns the isLocal.
     */
    public boolean isLocal()
    {
        return isLocal;
    }
/**
 * Checks to see if king is moving backwards.
 * @return true if backwards move.
 */
    public boolean isKingMove()
    {
        if ( isRed() )
        {
            return endRow > startRow; // Red moves up on the checkerboard,
                                      // moving down means king move means row
                                      // index increases
        }
        else
        {
            return startRow > endRow; // Black moves down, so a king (backwards
                                      // move) means that the start row is more
                                      // than the end row
        }

    }

/**
 * Checks instance of Move to see if move was made to the same location.
 * @param a Object
 * @return true if red moves
 * 
 */
    @Override
    public boolean equals( Object a )
    {
        if ( ( a instanceof Move ) )
        {
            Move m = (Move)a;
            if ( m.endCol == this.endCol && m.endRow == this.endRow
                && m.startCol == this.startCol && m.startRow == this.startRow )
            {
                return isRed() == m.isRed();
            }
        }
        return false;

    }
    /**
     * toString method lists move
     * @return location of initial location and final location
     */
    @Override
    public String toString()
    {
        return startRow + "," + startCol + "," + endRow + "," + endCol + "," + (isRed()?1:0);
    }

}
