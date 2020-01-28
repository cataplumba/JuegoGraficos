package pantallas;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import principal.Sprite;

public class Boton extends Sprite {
	private int accion;
	private PantallaJuego pantallaJuego;
	private MouseListener listener;

	public Boton(int posX, int posY, int ancho, int alto, int velX, int velY, Color color, int accion,PantallaJuego pantallaJuego,Vector<ObjetoJuego> listaObjeto) {
		super(posX, posY, ancho, alto, velX, velY, color);
		this.accion = accion;
		this.pantallaJuego = pantallaJuego;

		
	}
	
	public void comprobarClick(MouseEvent e) {
		Point p = e.getPoint();
		
		boolean clickX = (e.getX()>=posX)&&(e.getX()<=posX+ancho);
		boolean clickY = (e.getY()>=posY)&&(e.getY()<=posY+alto);
		
		if(clickX&&clickY) {
			switch(accion) {
			case 1:{

				if(pantallaJuego.personaje.getInventario().size()>=2) {
					//Si el personaje tiene más de 3 objetos, se elimina el primero que recogió
					pantallaJuego.personaje.getInventario().add(pantallaJuego.objetosJuego.get(0));
					pantallaJuego.personaje.getInventario().remove(0);
					pantallaJuego.objetosJuego.remove(0);
				}else {
					pantallaJuego.personaje.getInventario().add(pantallaJuego.objetosJuego.get(0));
					pantallaJuego.objetosJuego.remove(0);
				}
				
				pantallaJuego.reanudarJuego();
				pantallaJuego.vaciarMensaje();
				
				while(!pantallaJuego.listaBotones.isEmpty()) {
					pantallaJuego.listaBotones.remove(0);
				}
				pantallaJuego.desactivarEvento();
				break;
			}
			
			case 0:{

				if(!pantallaJuego.objetosJuego.isEmpty()) {
					pantallaJuego.objetosJuego.remove(0);
				}
				
				pantallaJuego.vaciarMensaje();
				pantallaJuego.reanudarJuego();
				
				while(!pantallaJuego.listaBotones.isEmpty()) {
					pantallaJuego.listaBotones.remove(0);
				}
				pantallaJuego.desactivarEvento();
				break;
			}
			}
		}
	}
	
	
}
