package Model;

public class Move
{
    private final int startRow;
    private final int startCol;
    private final int endRow;
    private final int endCol;
    private final boolean isRed;
    private final Player owner;
    
    /**
     * @param startRow
     * @param startCol
     * @param endRow
     * @param endCol
     */
    public Move( int startRow, int startCol, int endRow, int endCol, Player p )
    {
        
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
        this.owner = p;
        isRed = owner.isRed();
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
     * @return Returns the isRed.
     */
    public Player getPlayer()
    {
        return owner;
    }
    public boolean isRed()
    {
        return isRed;
    }
    
    public boolean isJump()
    {
        if(Math.abs(startCol - endCol) >= 1 && Math.abs(startRow - endRow) >= 1 )
        {
            return true;
        }
        return false;
    }
    
    
   
    
    
}
