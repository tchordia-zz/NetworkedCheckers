package Model;

import java.util.ArrayList;


public class MultiMove extends ArrayList<Move>
{

    boolean isValid;


    /**
     * 
     * Adds all it
     * @param a
     */
    public MultiMove( Move[] a )
    {

        for ( Move i : a )
        {
            boolean t = add( i );
            if ( !t )
            {
                isValid = false;
            }
        }
    }


    /**
     * @param a a list of Move objects
     */
    public MultiMove( ArrayList<Move> a )
    {
       this(new Move[a.size()]);
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.util.ArrayList#add(java.lang.Object)
     */
    @Override
    public boolean add( Move a )
    {
        if ( a.isJump() && get( size() - 1 ).getEndRow() == a.getStartRow()
            && get( size() - 1 ).getEndCol() == a.getStartCol() )
        {
            super.add( a );
            return true;
        }
        return false;
    }


    /**
     * Returns true if all Moves in this list are consecutive jumps, does NOT
     * check if each move actually captures a piece of the opposite color
     * 
     * @return
     */
    public boolean isValid()
    {
        return isValid();
    }

}
