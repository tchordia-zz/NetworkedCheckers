package Model;

import java.util.EventListener;

public interface MoveListener extends EventListener
{
    public void moveHappened(Move move);
}
