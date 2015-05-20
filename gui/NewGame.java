package gui;

import info.gridworld.grid.Location;
import info.gridworld.gui.DisplayMap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;

import Model.CheckerBoard;
import Model.CheckerBoardGui;
import Model.Move;


public class NewGame extends JFrame implements CheckerBoardGui
{
    DisplayMap map = new DisplayMap();

    BoundedGrid<Piece> gr = new BoundedGrid<Piece>( 8, 8 );

    GridPanel board;

    CheckerBoard chec;


    public NewGame()
    {
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setVisible( true );

        chec = new CheckerBoard( this );
        setLayout( new GridLayout() );
        board = new GridPanel( map );
        board.setGrid( gr );
        board.setVisible( true );
        getContentPane().add( board );
        board.minCellSize = this.getHeight() / board.DEFAULT_CELL_COUNT;
        board.defaultCellSize = this.getHeight() / board.DEFAULT_CELL_COUNT;

        pack();
        updateCheckers();
    }


    public void updateCheckers()
    {
        char[][] b = chec.getBoard();
        for ( int i = 0; i < b.length; i++ )
        {
            for ( int j = 0; j < b[0].length; j++ )
            {
                if ( b[i][j] == 'b' )
                {
                    gr.put( new Location( i, j ), new Piece( board.minCellSize,
                        board.minCellSize,
                        Color.black ) );
                }
                else if ( b[i][j] == 'r' )
                {
                    gr.put( new Location( i, j ), new Piece( board.minCellSize,
                        board.minCellSize,
                        Color.red ) );
                }
            }
        }

    }


    public static void main( String[] args )
    {
        JFrame window = new NewGame();

    }


    @Override
    public void doMove( Move m )
    {
        updateCheckers();

    }
}