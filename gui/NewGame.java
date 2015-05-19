package gui;

import info.gridworld.grid.Location;
import info.gridworld.gui.DisplayMap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;

import Model.CheckerBoard;


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
        addCheckers();
    }


    public void addCheckers()
    {
       char[][] b = { { '.', 'b', '.', 'b', '.', 'b', '.', 'b' },

            { 'b', '.', 'b', '.', 'b', '.', 'b', '.' },
            { '.', 'b', '.', 'b', '.', 'b', '.', 'b' },
            { ' ', '.', ' ', '.', ' ', '.', ' ', '.' },
            { '.', ' ', '.', ' ', '.', ' ', '.', ' ' },
            { 'r', '.', 'r', '.', 'r', '.', 'r', '.' },
            { '.', 'r', '.', 'r', '.', 'r', '.', 'r' },
            { 'r', '.', 'r', '.', 'r', '.', 'r', '.' } };
        for ( int i = 0; i < b.length; i++ )
        {
            for ( int j = 0; j < b[0].length; j++ )
            {
                if ( b[i][j] == 'b' )
                {
                    gr.put( new Location (i,j), new Piece (board.minCellSize, board.minCellSize, Color.black) );
                }
                else if (b[i][j]=='r')
                {
                    gr.put( new Location (i,j), new Piece (board.minCellSize, board.minCellSize, Color.red) );
                }
            }
        }

    }


    public static void main( String[] args )
    {
        JFrame window = new NewGame();

    }
}