package gui;

import java.awt.Color;


public class Piece
{
    private Color c;


    /**
     * Constructs a piece.
     * 
     * @param color
     *            the color of the piece
     */
    public Piece( Color magical )
    {
        c = magical;
    }


    /**
     * Gets the color of the piece.
     * 
     * @return the color of the piece
     */
    public Color getColor()
    {
        return c;
    }


    /**
     * Sets the color of the piece.
     * 
     * @param newColor
     *            the new color for the piece
     */
    public void setColor( Color newColor )
    {
        c = newColor;
    }
}
