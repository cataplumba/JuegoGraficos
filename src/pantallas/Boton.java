package pantallas;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import principal.PanelJuego;
import principal.Sprite;

public class Boton extends Sprite implements MouseListener {
	private int accion;
	private PanelJuego panelJuego;
	private Vector<ObjetoJuego>inventarioPersonaje;
	private ObjetoJuego objeto;

	public Boton(int posX, int posY, int ancho, int alto, int velX, int velY, Color color, int accion) {
		super(posX, posY, ancho, alto, velX, velY, color);
		this.accion = accion;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(accion==1) {
			inventarioPersonaje.remove(0);
			inventarioPersonaje.add(objeto);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
