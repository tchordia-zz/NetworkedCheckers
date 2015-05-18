package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.util.ResourceBundle;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.gui.DisplayMap;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class NewGame extends JFrame
{
    private static int x = 0;

    private static int y = 0;

    private static int width = 500;

    private static int height = 500;

    DisplayMap map = new DisplayMap();

    BoundedGrid<Piece> gr = new BoundedGrid<Piece>( 8, 8 );
    

    public NewGame()
    {
        
        GridPanel board = new GridPanel( map );
        board.setGrid( gr );
        board.setVisible( true );
        getContentPane().add( board );
        board.minCellSize = this.getHeight()/board.DEFAULT_CELL_COUNT;
        board.defaultCellSize = this.getHeight()/board.DEFAULT_CELL_COUNT;
        
    }


    public static void main( String[] args )
    {
        JFrame window = new NewGame();
        window.setBounds( x, y, width, height );
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setVisible( true );
        window.setLayout( new GridLayout() );
        
    }
}
