package Model;

import java.util.Scanner;


public class TextHumanPlayer extends Player
{
    Scanner scan;


    public TextHumanPlayer( String username )
    {

        super( username );
        scan = new Scanner( System.in );
    }


    @Override
    public void doMove()
    {

        if ( inGame() )
        {
            System.out.println( getGame() );
            int sr = scan.nextInt();
            int sc = scan.nextInt();
            int er = scan.nextInt();
            int ec = scan.nextInt();
            System.out.println( "trying move" );
            Move m =  new Move( sr, sc, er, ec, isRed() ) ;
            while ( !getGame().doMove(m) )
            {
                System.out.println( getGame() );
                System.out.println(m.isJump());
                System.out.println( "try again" );
                sr = scan.nextInt();
                sc = scan.nextInt();
                er = scan.nextInt();
                ec = scan.nextInt();
                m =  new Move( sr, sc, er, ec, isRed() ) ;
            }
        }
    }

}
