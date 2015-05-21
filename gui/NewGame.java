//package gui;
//
//import info.gridworld.grid.Location;
//import info.gridworld.gui.DisplayMap;
//
//import java.awt.Color;
//import java.awt.GridLayout;
//
//import javax.swing.JFrame;
//
//import Model.CheckerBoard;
//import Model.CheckerBoardGui;
//import Model.Move;
//
//
//public class NewGame extends JFrame implements CheckerBoardGui
//{
//    DisplayMap map = new DisplayMap();
//
//    BoundedGrid<Piece> gr = new BoundedGrid<Piece>( 8, 8 );
//
//    GridPanel board;
//
//    CheckerBoard chec;
//
//
//    public NewGame()
//    {
//        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//        setVisible( true );
//
//        chec = new CheckerBoard( this );
//        setLayout( new GridLayout() );
//        board = new GridPanel( map );
//        board.setGrid( gr );
//        board.setVisible( true );
//        getContentPane().add( board );
//
//        pack();
//        boardColor();
//        updateCheckers();
//    }
//
//
//    public void updateCheckers()
//    {
//        char[][] b = chec.getBoard();
//        for ( int i = 0; i < b.length; i++ )
//        {
//            for ( int j = 0; j < b[0].length; j++ )
//            {
//                if ( b[i][j] == 'b' )
//                {
//                    gr.put( new Location( i, j ), new Piece( board.minCellSize,
//                        board.minCellSize,
//                        Color.black ) );
//                }
//                else if ( b[i][j] == 'r' )
//                {
//                    gr.put( new Location( i, j ), new Piece( board.minCellSize,
//                        board.minCellSize,
//                        Color.red ) );
//                }
//            }
//        }
//    }
//
//
//    public void boardColor()
//    {
//        char[][] b = chec.getBoard();
//
//        for ( int x = 0; x < b.length; x++ )
//        {
//            for ( int y = 0; y < b[0].length; y++ )
//            {
//                
//                if ( ( x + y ) % 2 == 1 )
//                {
//                    
//                }
//            }
//        }
//    }
//
//
//    public static void main( String[] args )
//    {
//        JFrame window = new NewGame();
//        window.setSize( 510, 530 );
//        window.setResizable( false );
//    }
//
//
//    @Override
//    public void doMove( Move m )
//    {
//        updateCheckers(); 
//    }
//}