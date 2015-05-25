package gui;

import java.awt.Color;


public class Piece
{
    private Color color;

    int x;

    int y;


    /**
     * Constructs a piece.
     * 
     * @param color
     *            the color of the piece
     */
    public Piece( Color c )
    {
        this.color = c;
        x = 1;
        y = 1;
    }


    /**
     * Gets the color of the piece.
     * 
     * @return the color of the piece
     */
    public Color getColor()
    {
        return color;
    }


    /**
     * Sets the color of the piece.
     * 
     * @param newColor
     *            the new color for the piece
     */
    public void setColor( Color newColor )
    {
        color = newColor;
    }

/**
 * 
 * return x value
 * @return
 */
    public int getX()
    {
        return x;
    }

/**
 * 
 * return y value
 * @return
 */
    public int getY()
    {
        return y;
    }

/**
 * 
 * set x value
 * @param a
 */
    public void setX( int a )
    {
        x = a;
    }

/**
 * 
 * set y value
 * @param b
 */
    public void setY( int b )
    {
        y = b;
    }
}