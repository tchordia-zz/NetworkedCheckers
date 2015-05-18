package gui;

import info.gridworld.world.World;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

import javax.swing.JFrame;

import Model.CheckerBoard;

public class BoardGUI
{
    /** The checker game */
    private CheckerBoard game;

    /** A semaphore to prevent getPlayerLocation from executing
     *  before setPlayerLocation */
    private Semaphore lock;

    /** The last selected player location */
    private Location playerLocation;

    /**
     * Construct an checker world
     * game The checker game
     */
    public BoardGUI(CheckerBoard g)
    {
        game = (CheckerBoard)g;
        playerLocation = null;

        add(new Location(0, 1), new Piece(Color.RED));
        add(new Location(0, 3), new Piece(Color.RED));
        add(new Location(0, 5), new Piece(Color.RED));
        add(new Location(0, 7), new Piece(Color.RED));
        add(new Location(1, 0), new Piece(Color.RED));
        add(new Location(1, 2), new Piece(Color.RED));
        add(new Location(1, 4), new Piece(Color.RED));
        add(new Location(1, 6), new Piece(Color.RED));
        add(new Location(2, 1), new Piece(Color.RED));
        add(new Location(2, 3), new Piece(Color.RED));
        add(new Location(2, 5), new Piece(Color.RED));
        add(new Location(2, 7), new Piece(Color.RED));
        
        add(new Location(5, 0), new Piece(Color.BLACK));
        add(new Location(5, 2), new Piece(Color.BLACK));
        add(new Location(5, 4), new Piece(Color.BLACK));
        add(new Location(5, 6), new Piece(Color.BLACK));
        add(new Location(6, 1), new Piece(Color.BLACK));
        add(new Location(6, 3), new Piece(Color.BLACK));
        add(new Location(6, 5), new Piece(Color.BLACK));
        add(new Location(6, 7), new Piece(Color.BLACK));
        add(new Location(7, 0), new Piece(Color.BLACK));
        add(new Location(7, 2), new Piece(Color.BLACK));
        add(new Location(7, 4), new Piece(Color.BLACK));
        add(new Location(7, 6), new Piece(Color.BLACK));
    }

    private void add( Location location, Piece piece )
    {
        // TODO Auto-generated method stub
        
    }
}
