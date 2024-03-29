import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Keyboard extends KeyAdapter {
    public static ArrayList<Integer> pressedButtons=new ArrayList<>();


    public static boolean isKeyPressed(int keyCode){
        return pressedButtons.contains(keyCode);
    }

    public void keyPressed(KeyEvent e){
        super.keyPressed(e);
        if(!pressedButtons.contains(e.getKeyCode())) pressedButtons.add(e.getKeyCode());
    }
    public void keyReleased(KeyEvent e){
        super.keyReleased(e);
        pressedButtons.remove((Integer)e.getKeyCode());
    }
}
