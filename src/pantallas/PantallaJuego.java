package pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;

import principal.PanelJuego;
import principal.Sprite;

/**
 * @author Alejandro Pascual Clemente, Álvaro Sánchez Hernández, Daniel Simón
 *         Mateo
 */

public class PantallaJuego implements Pantalla {

	final Font fuenteInicio = new Font("", 2, 25);
	public boolean juegoEnMarcha;
	
	final static int ANCHO_FONDO = 1200;
	
	private Vector<Sprite> generadorEventos;

	/** SPRITES **/
	ObjetoJuego personaje;

	/** VARIABLES PARA TIEMPO **/
	private double tiempoInicial = 0;
	private double tiempoTranscurrido;
	private DecimalFormat formato = new DecimalFormat("#.##");

	/** FONDO **/
//	BufferedImage canvasFondo;
//	Image imagenFondo;
	
	BufferedImage canvasPersonaje;
	Image imagenPersonaje;
	Vector<Sprite> parallaxSuelo;
	Vector<Sprite> parallaxBosque;
	Vector<Sprite> parallaxCielo;

	PanelJuego panelJuego;

	public PantallaJuego(PanelJuego panel) {
		panelJuego = panel;
		inicializarPantalla(panel);
	}

	@Override
	public void inicializarPantalla(PanelJuego panel) {
		panelJuego = panel;
		juegoEnMarcha = true;
		parallaxSuelo = new Vector<Sprite>();
		parallaxBosque = new Vector<Sprite>();
		parallaxCielo = new Vector<Sprite>();
		generadorEventos = new Vector<Sprite>();
		Random rd = new Random();
		

		try {
			//canvasFondo = ImageIO.read(new File("Imagenes/capaSuelo.png"));
			canvasPersonaje = ImageIO.read(new File("Imagenes/personaje.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		
//		imagenFondo = canvasFondo;
//		imagenPersonaje = canvasPersonaje;

		
		personaje = new ObjetoJuego(40, panelJuego.getHeight()-200, 80, 80, 0, 0, "personaje",
				rd.nextInt(15), rd.nextInt(15), 100, "Imagenes/personaje.png");
		
		parallaxSuelo.add(new Sprite(0, 0, ANCHO_FONDO, panelJuego.getHeight(), -7, 0,
				"Imagenes/capaSuelo.png"));
		
		parallaxBosque.add(new Sprite(0, 0, ANCHO_FONDO, panelJuego.getHeight(), -3, 0, "Imagenes/capaBosque.png"));
		
		parallaxCielo.add(new Sprite(0, 0, panelJuego.getWidth(), panelJuego.getHeight(), -1, 0, "Imagenes/capaCielo.png"));

		parallaxSuelo.add(new Sprite(panelJuego.getWidth(), 0, ANCHO_FONDO, panelJuego.getHeight(), -7, 0,
				"Imagenes/capaSuelo.png"));
		
		parallaxBosque.add(new Sprite(panelJuego.getWidth(), 0, ANCHO_FONDO, panelJuego.getHeight(), -3, 0,
				"Imagenes/capaBosque.png"));
		
		parallaxCielo.add(new Sprite(panelJuego.getWidth(), 0, ANCHO_FONDO, panelJuego.getHeight(), -1, 0,
				"Imagenes/capaCielo.png"));

		// tiempoInicial = System.nanoTime();

	}

	@Override
	public void pintarPantalla(Graphics g) {
		rellenarFondo(g);
		
		for (int i = 0; i < parallaxCielo.size(); i++) {
			parallaxCielo.get(i).pintarEnMundo(g);
		}
		
		for (int i = 0; i < parallaxBosque.size(); i++) {
			parallaxBosque.get(i).pintarEnMundo(g);
		}
		

		for (int i = 0; i < parallaxSuelo.size(); i++) {
			parallaxSuelo.get(i).pintarEnMundo(g);
		}
		

		
		personaje.pintarEnMundo(g);
		g.setFont(fuenteInicio);

		g.drawString("Salud: "+Integer.toString(personaje.getSalud()), 10, 25);
		g.drawString("Ataque: "+Integer.toString(personaje.getAtaque()), 10, 50);
		g.drawString("Defensa: "+Integer.toString(personaje.getDefensa()), 10, 75);
//		g.setFont(fuenteInicio);
//		g.setColor(Color.ORANGE);
//		tiempoTranscurrido = System.nanoTime() - tiempoInicial;
//		g.drawString(formato.format((System.nanoTime() - tiempoInicial) / 1e9), 50, 50);

	}

	@Override
	public void ejecutarFrame() {
		try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if ((parallaxSuelo.get(0).getPosX() + parallaxSuelo.get(0).getAncho()) <= 0) {
			parallaxSuelo.remove(0);
			parallaxSuelo.add(new Sprite(panelJuego.getWidth()-4, 0, ANCHO_FONDO, panelJuego.getHeight(), -7, 0,
					"Imagenes/capaSuelo.png"));
		}
		
		if ((parallaxBosque.get(0).getPosX() + parallaxBosque.get(0).getAncho()) <= 0) {
			parallaxBosque.remove(0);
			parallaxBosque.add(new Sprite(panelJuego.getWidth(), 0, ANCHO_FONDO, panelJuego.getHeight(), -3, 0,
					"Imagenes/capaBosque.png"));
		}
		
		if ((parallaxCielo.get(0).getPosX() + parallaxCielo.get(0).getAncho()) <= 0) {
			parallaxCielo.remove(0);
			parallaxCielo.add(new Sprite(panelJuego.getWidth(), 0, ANCHO_FONDO, panelJuego.getHeight(), -1, 0,
					"Imagenes/capaCielo.png"));
		}
		

		moverSprites();

	}
	
	public void detenerJuego() {
		
		//Detiene el movimiento del suelo
		for (int i = 0; i < parallaxSuelo.size(); i++) {
			parallaxSuelo.get(i).setVelX(0);
		}
		
		//Detiene el movimiento del suelo
		for (int i = 0; i < parallaxBosque.size(); i++) {
			parallaxBosque.get(i).setVelX(0);
		}
		
		//Detiene el movimiento del suelo
		for (int i = 0; i < parallaxCielo.size(); i++) {
			parallaxCielo.get(i).setVelX(0);
		}
	}
	
	public void reanudarJuego() {
		
		//Detiene el movimiento del suelo
		for (int i = 0; i < parallaxSuelo.size(); i++) {
			parallaxSuelo.get(i).setVelX(-7);
		}
		
		//Detiene el movimiento del suelo
		for (int i = 0; i < parallaxBosque.size(); i++) {
			parallaxBosque.get(i).setVelX(-3);
		}
		
		//Detiene el movimiento del suelo
		for (int i = 0; i < parallaxCielo.size(); i++) {
			parallaxCielo.get(i).setVelX(-1);
		}
	}

	@Override
	public void pulsarRaton(MouseEvent e) {
		if(juegoEnMarcha) {
			detenerJuego();
			juegoEnMarcha = false;
		}else {
			reanudarJuego();
			juegoEnMarcha = true;
		}
	}

	@Override
	public void moverRaton(MouseEvent e) {

	}

	@Override
	public void redimensionar() {

	}

	private void rellenarFondo(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, panelJuego.getWidth(), panelJuego.getHeight());
	}

	/**
	 * Mueve todos los sprites del PanelJuego
	 */
	private void moverSprites() {

		for (int i = 0; i < parallaxSuelo.size(); i++) {
			parallaxSuelo.get(i).actualizarPosicionFondo(panelJuego);
		}
		
		for (int i = 0; i < parallaxBosque.size(); i++) {
			parallaxBosque.get(i).actualizarPosicionFondo(panelJuego);
		}
		
		for (int i = 0; i < parallaxCielo.size(); i++) {
			parallaxCielo.get(i).actualizarPosicionFondo(panelJuego);
		}

	}

	private void comprobarColisiones() {

	}
}
