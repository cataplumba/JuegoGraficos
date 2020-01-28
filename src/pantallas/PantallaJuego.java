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
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;

import principal.PanelJuego;
import principal.Sprite;

/**
 * @author Daniel Simón Mateo
 */

public class PantallaJuego implements Pantalla {

	final Font fuenteInicio = new Font("", 2, 25);
	public boolean juegoEnMarcha;

//	final static int ANCHO_FONDO = 1200;

	private Vector<Sprite> generadorEventos;

	/** SPRITES **/
	Character personaje;

	/** VARIABLES PARA TIEMPO **/
	private double tiempoInicial = System.nanoTime();
	private double tiempoTranscurrido;
	private DecimalFormat formato = new DecimalFormat("#");
	private int aleatorizadorEventos;

	/** FONDO **/

	BufferedImage canvasPersonaje;
	Image imagenPersonaje;
	Vector<Sprite> parallaxSuelo;
	Vector<Sprite> parallaxBosque;
	Vector<Sprite> parallaxCielo;
	Vector<Sprite>objetosJuego;

	PanelJuego panelJuego;

	public PantallaJuego(PanelJuego panel) {
		panelJuego = panel;
		inicializarPantalla(panel);
	}

	@Override
	public void inicializarPantalla(PanelJuego panel) {
		Random rd = new Random();
		panelJuego = panel;
		juegoEnMarcha = true;
		parallaxSuelo = new Vector<Sprite>();
		parallaxBosque = new Vector<Sprite>();
		parallaxCielo = new Vector<Sprite>();
		generadorEventos = new Vector<Sprite>();
		objetosJuego = new Vector<Sprite>();
		
		aleatorizadorEventos = rd.nextInt(10)+1;
		
				
		

		try {
			// canvasFondo = ImageIO.read(new File("Imagenes/capaSuelo.png"));
			canvasPersonaje = ImageIO.read(new File("Imagenes/personaje.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

//		imagenFondo = canvasFondo;
//		imagenPersonaje = canvasPersonaje;

		personaje = new Character(40, panelJuego.getHeight() - 200, 80, 80, 0, 0, 1, rd.nextInt(15), rd.nextInt(15),
				100, "Imagenes/personaje.png");

		parallaxSuelo
				.add(new Sprite(0, 0, panelJuego.getWidth(), panelJuego.getHeight(), -7, 0, "Imagenes/capaSuelo.png"));

		parallaxBosque
				.add(new Sprite(0, 0, panelJuego.getWidth(), panelJuego.getHeight(), -3, 0, "Imagenes/capaBosque.png"));

		parallaxCielo
				.add(new Sprite(0, 0, panelJuego.getWidth(), panelJuego.getHeight(), -1, 0, "Imagenes/capaCielo.png"));

		parallaxSuelo.add(new Sprite(panelJuego.getWidth(), 0, panelJuego.getWidth(), panelJuego.getHeight(), -7, 0,
				"Imagenes/capaSuelo.png"));

		parallaxBosque.add(new Sprite(panelJuego.getWidth(), 0, panelJuego.getWidth(), panelJuego.getHeight(), -3, 0,
				"Imagenes/capaBosque.png"));

		parallaxCielo.add(new Sprite(panelJuego.getWidth(), 0, panelJuego.getWidth(), panelJuego.getHeight(), -1, 0,
				"Imagenes/capaCielo.png"));

	}

	@Override
	public void pintarPantalla(Graphics g) {
		
		rellenarFondo(g);

		//CIELO
		for (int i = 0; i < parallaxCielo.size(); i++) {
			parallaxCielo.get(i).pintarEnMundo(g);
		}

		//BOSQUE
		for (int i = 0; i < parallaxBosque.size(); i++) {
			parallaxBosque.get(i).pintarEnMundo(g);
		}

		//SUELO
		for (int i = 0; i < parallaxSuelo.size(); i++) {
			parallaxSuelo.get(i).pintarEnMundo(g);
		}

		//PERSONAJE
		personaje.pintarEnMundo(g);
		
		//OBJETOS JUEGO
		for (int i = 0; i < objetosJuego.size(); i++) {
			objetosJuego.get(i).pintarEnMundo(g);
		}
		
		g.setFont(fuenteInicio);

		// NIVEL
		g.setColor(Color.white);
		g.drawString("Nivel: " + Integer.toString(personaje.getNivel()), 10, 25);

		// ATAQUE Y DEFENSA
		g.setColor(Color.BLUE);
		g.drawString("Ataque: " + Integer.toString(personaje.getAtaque()), 10, 50);
		g.drawString("Defensa: " + Integer.toString(personaje.getDefensa()), 10, 75);

		// SALUD
		g.setColor(Color.red);
		g.drawString("Salud: " + Integer.toString(personaje.getSalud()), 10, 100);

		// TIEMPO
		g.setFont(fuenteInicio);
		g.setColor(Color.ORANGE);
		
		if(juegoEnMarcha) {
			tiempoTranscurrido = System.nanoTime() - tiempoInicial;
		}
		
		 g.drawString(formato.format((tiempoTranscurrido) / 1e9), panelJuego.getWidth()-150,
		 50);

		// INVENTARIO
		for (int i = 0; i < personaje.getInventario().size(); i++) {
			personaje.getInventario().get(i).pintarEnMundo(g);
		}
		
		g.drawString(Integer.toString(aleatorizadorEventos), panelJuego.getWidth()-150, 150);
		
		

	}

	@Override
	public void ejecutarFrame() {
		Random rd = new Random();
		try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (juegoEnMarcha) {
			if ((parallaxSuelo.get(0).getPosX() + parallaxSuelo.get(0).getAncho()) <= 0) {
				parallaxSuelo.remove(0);
				parallaxSuelo.add(new Sprite(panelJuego.getWidth() - 4, 0, panelJuego.getWidth(),
						panelJuego.getHeight(), -7, 0, "Imagenes/capaSuelo.png"));
			}

			if ((parallaxBosque.get(0).getPosX() + parallaxBosque.get(0).getAncho()) <= 0) {
				parallaxBosque.remove(0);
				parallaxBosque.add(new Sprite(panelJuego.getWidth()-5, 0, panelJuego.getWidth(), panelJuego.getHeight(),
						-3, 0, "Imagenes/capaBosque.png"));
			}

			if ((parallaxCielo.get(0).getPosX() + parallaxCielo.get(0).getAncho()) <= 0) {
				parallaxCielo.remove(0);
				parallaxCielo.add(new Sprite(panelJuego.getWidth(), 0, panelJuego.getWidth(), panelJuego.getHeight(),
						-1, 0, "Imagenes/capaCielo.png"));
			}
			
			int tiempoAux = (int) (tiempoTranscurrido / 1e9);
			System.out.println(tiempoAux);
			if(aleatorizadorEventos==tiempoAux) {
				aleatorizadorEventos = rd.nextInt(10);
				tiempoInicial = System.nanoTime();
				tiempoTranscurrido = System.nanoTime()-tiempoInicial;
				objetosJuego.add(new Character(panelJuego.getWidth()+150, panelJuego.getHeight() - 150, 30, 40, -7, 0, 1, rd.nextInt(15), rd.nextInt(15),
						100, "Imagenes/pocion.png"));
				
				System.out.println("Hola");
			}
			

			moverSprites();
			comprobarColisiones();
		}

	}

	public void detenerJuego() {

		// Detiene el movimiento del suelo
		for (int i = 0; i < parallaxSuelo.size(); i++) {
			parallaxSuelo.get(i).setVelX(0);
		}

		// Detiene el movimiento del suelo
		for (int i = 0; i < parallaxBosque.size(); i++) {
			parallaxBosque.get(i).setVelX(0);
		}

		// Detiene el movimiento del suelo
		for (int i = 0; i < parallaxCielo.size(); i++) {
			parallaxCielo.get(i).setVelX(0);
		}
	}

	public void reanudarJuego() {

		// Detiene el movimiento del suelo
		for (int i = 0; i < parallaxSuelo.size(); i++) {
			parallaxSuelo.get(i).setVelX(-7);
		}

		// Detiene el movimiento del bosque
		for (int i = 0; i < parallaxBosque.size(); i++) {
			parallaxBosque.get(i).setVelX(-3);
		}

		// Detiene el movimiento dsueloel cielo
		for (int i = 0; i < parallaxCielo.size(); i++) {
			parallaxCielo.get(i).setVelX(-1);
		}
	}

	@Override
	public void pulsarRaton(MouseEvent e) {
		if (juegoEnMarcha) {
			detenerJuego();
			juegoEnMarcha = false;
		} else {
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
		
		for (int i = 0; i < objetosJuego.size(); i++) {
			objetosJuego.get(i).actualizarPosicionFondo(panelJuego);
		}

	}

	private void comprobarColisiones() {

	}
}
