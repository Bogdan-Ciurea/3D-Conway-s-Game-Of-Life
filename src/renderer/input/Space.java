package renderer.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// The class that will take care of the 'space' press
public class Space implements KeyListener{

    public boolean spaceIsPressed = false;
    public boolean hasExecutedOnce = false;

    @Override
    public void keyTyped(KeyEvent event) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_SPACE){
            spaceIsPressed = true;
            hasExecutedOnce = false;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_SPACE){
            spaceIsPressed = false;
            hasExecutedOnce = false;
        }
        
    }
    
}
