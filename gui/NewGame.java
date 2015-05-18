package gui;

import info.gridworld.gui.DisplayMap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;


public class NewGame extends JFrame
{
    DisplayMap map = new DisplayMap();

    BoundedGrid<Piece> gr = new BoundedGrid<Piece>( 8, 8 );

    GridPanel board;


    public NewGame()
    {
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setVisible( true );
        setLayout( new GridLayout() );
        board = new GridPanel( map );
        board.setGrid( gr );
        board.setVisible( true );
        getContentPane().add( board );
        board.minCellSize = this.getHeight() / board.DEFAULT_CELL_COUNT;
        board.defaultCellSize = this.getHeight() / board.DEFAULT_CELL_COUNT;
        pack();
    }


    public void paint( Graphics g )
    {
        for ( int row = 0; row < 8; row++ )
        {
            for ( int col = 0; col < 8; col++ )
            {
                if ( row % 2 == col % 2 )
                    g.setColor( Color.red );
                else
                    g.setColor( Color.gray );

                g.fillRect( row * board.minCellSize, col* board.minCellSize, board.minCellSize, board.minCellSize );
            }
        }
    }


    public static void main( String[] args )
    {
        JFrame window = new NewGame();


    }
}
