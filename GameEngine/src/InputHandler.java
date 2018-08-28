import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
	
	boolean[] keys;
	
	public InputHandler(Component c){
		c.addKeyListener(this);
		keys = new boolean[256];
	}
	
	public boolean isKeyDown(int keyCode){
		if(keyCode > 0 && keyCode < 256){
			return keys[keyCode];
		}
		return false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()>0 && e.getKeyCode()< 256)
		{
			keys[e.getKeyCode()] = true;
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()>0 &&e.getKeyCode()< 256){
			keys[e.getKeyCode()] = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
