package gui;

import info.gridworld.world.World;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import javax.swing.JFrame;

import Model.CheckerBoard;


public class BoardGUI
{
    /** The checker game */
    private CheckerBoard game;

    /**
     * A semaphore to prevent getPlayerLocation from executing before
     * setPlayerLocation
     */
    private Semaphore lock;

    /** The last selected player location */
    private Location playerLocation;

    private List pieces = new ArrayList<>();

    public boolean red;


    /**
     * Construct an checker world game The checker game
     */
    public BoardGUI( CheckerBoard g, boolean r )
    {
        game = (CheckerBoard)g;
        red = r;

        playerLocation = null;

    }


    public List getPieces()
    {
        return pieces;
    }

}
