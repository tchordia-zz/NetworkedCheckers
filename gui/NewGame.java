package gui;

import java.awt.Color;
import java.awt.FlowLayout;

import info.gridworld.grid.Grid;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class NewGame extends JFrame
{
    private static int x = 0;
    private static int y = 0;
    private static int width = 800;
    private static int height = 500;
    
    public NewGame(  )
    {
        JPanel board = new JPanel();
        JPanel chat = new JPanel();
        board.setLayout( new FlowLayout() );
        chat.setLayout( new FlowLayout() );
        
        board.setBounds( x, y, height, height );
        chat.setBounds( x, y, height - width, height );
        
        board.setBackground( Color.BLUE );
        chat.setBackground( Color.RED );
        
        board.setVisible( true );
        chat.setVisible( true );
    }
    
    public static void main(String[] args)
    {
        JFrame window = new JFrame();
        window.setBounds( x, y, width, height );
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        window.setVisible( true );
    }
    
}
