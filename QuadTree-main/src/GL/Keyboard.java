package GL;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//classe que cuida dos eventos do teclado
public class Keyboard implements KeyListener{

	
	boolean W = false;
	boolean A = false;
	boolean S = false;
	boolean D = false;
	
	boolean um = false;
	boolean dois = false;
	
	boolean esc = false;
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		
		if(e.getKeyCode() == e.VK_W)
		{
			W = true;
		}
		if(e.getKeyCode() == e.VK_A)
		{
			A = true;
		}
		if(e.getKeyCode() == e.VK_S)
		{
			S = true;
		}
		if(e.getKeyCode() == e.VK_D)
		{
			D = true;
		}
		
		if(e.getKeyCode() == e.VK_1)
		{
			um = true;
		}
		if(e.getKeyCode() == e.VK_2)
		{
			dois = true;
		}
		
		if(e.getKeyCode() == e.VK_ESCAPE)
		{
			esc = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == e.VK_W)
		{
			W = false;
		}
		if(e.getKeyCode() == e.VK_A)
		{
			A = false;
		}
		if(e.getKeyCode() == e.VK_S)
		{
			S = false;
		}
		if(e.getKeyCode() == e.VK_D)
		{
			D = false;
		}
		
		if(e.getKeyCode() == e.VK_1)
		{
			um = false;
		}
		if(e.getKeyCode() == e.VK_2)
		{
			dois = false;
		}
		
		if(e.getKeyCode() == e.VK_ESCAPE)
		{
			esc = false;
		}
		
	}
	
	
	public boolean Return_W() {
		return W;
	}
	public boolean Return_A() {
		return A;
	}
	public boolean Return_S() {
		return S;
	}
	public boolean Return_D() {
		return D;
	}
	
	public boolean Return_Um() {
		return um;
	}
	public boolean Return_Dois() {
		return dois;
	}
	
	public boolean Return_Esc() {
		return esc;
	}
	
	
}
