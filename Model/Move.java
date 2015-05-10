package Model;

import java.util.StringTokenizer;

public class Move
{
    private final int startRow;

    private final int startCol;

    private final int endRow;

    private final int endCol;

    private final boolean isRed;


    /**
     * @param startRow
     * @param startCol
     * @param endRow
     * @param endCol
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
    }
    
    //For network purpose, construct move object from String sent over network
 public static Move stringToMove(String s)
 {
     StringTokenizer a = new StringTokenizer( s,"," );
  
     try{
     Move m = new Move(Integer.parseInt(a.nextToken()), Integer.parseInt(a.nextToken()), Integer.parseInt(a.nextToken()), Integer.parseInt(a.nextToken()), Integer.parseInt(a.nextToken())==1);
     return m;
     }
     catch(Exception e)
     {
     return null;
     }
 }


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


    public boolean isSimpleMove()
    {
        if ( Math.abs( startCol - endCol ) == 1
            && Math.abs( startRow - endRow ) == 1 )
        {
            return true;
        }
        return false;
    }


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
    
    @Override
    public String toString()
    {
        return startRow + "," + startCol + "," + endRow + "," + endCol + "," + (isRed()?1:0);
    }

}
