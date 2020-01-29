package pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Vector;

import principal.PanelJuego;
import principal.Sprite;

/**
 * @author Daniel Simón Mateo
 */

public class PantallaJuego implements Pantalla {

	final Font fuenteInicio = new Font("", 2, 25);
	public boolean juegoEnMarcha;

//	final static int ANCHO_FONDO = 1200;

	/** SPRITES **/
	Character personaje;

	/** VARIABLES PARA TIEMPO **/
//	private double tiempoInicial = System.nanoTime();
//	private double tiempoTranscurrido;
//	private DecimalFormat formato = new DecimalFormat("#");
//	private int aleatorizadorEventos;

	/** FONDO **/
	Vector<Sprite> parallaxSuelo;
	Vector<Sprite> parallaxBosque;
	Vector<Sprite> parallaxCielo;
	Vector<ObjetoJuego> objetosJuego;
	Vector<Boton> listaBotones;
	Vector<Sprite> mensajes;
	private String mensaje;
	private boolean eventoActivo;

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
		objetosJuego = new Vector<ObjetoJuego>();
		listaBotones = new Vector<Boton>();
		eventoActivo = false;


		personaje = new Character(40, panelJuego.getHeight() - 200, 80, 80, 0, 0, 1, rd.nextInt(15), rd.nextInt(15), 20,
				"Imagenes/personaje.png");

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

		generarObjeto();

	}

	@Override
	public void pintarPantalla(Graphics g) {

		rellenarFondo(g);

		// CIELO
		for (int i = 0; i < parallaxCielo.size(); i++) {
			parallaxCielo.get(i).pintarEnMundo(g);
		}

		// BOSQUE
		for (int i = 0; i < parallaxBosque.size(); i++) {
			parallaxBosque.get(i).pintarEnMundo(g);
		}

		// SUELO
		for (int i = 0; i < parallaxSuelo.size(); i++) {
			parallaxSuelo.get(i).pintarEnMundo(g);
		}

		// PERSONAJE
		personaje.pintarEnMundo(g);

		// OBJETOS JUEGO
		for (int i = 0; i < objetosJuego.size(); i++) {
			objetosJuego.get(i).pintarEnMundo(g);
		}

		// BOTONEs
		for (int i = 0; i < listaBotones.size(); i++) {
			listaBotones.get(i).pintarEnMundo(g);
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

		// INVENTARIO
		g.drawString("Inventario:", 10, 125);
		for (int i = 0; i < personaje.getInventario().size(); i++) {
			switch (i) {
			case 0: {
				personaje.getInventario().get(i).setPosX(15);
				personaje.getInventario().get(i).setPosY(140);

				// Se dibuja el atribujo del objeto debajo del mismo
				switch (personaje.getInventario().get(i).getTipo()) {
				case "espada": {
					g.setColor(Color.red);
					g.drawString(Integer.toString(personaje.getInventario().get(i).getAtaque()), 15, 230);
					break;
				}

				case "escudo": {
					g.setColor(Color.green);
					g.drawString(Integer.toString(personaje.getInventario().get(i).getDefensa()), 15, 230);
					break;
				}
				}
				break;
			}

			case 1: {
				personaje.getInventario().get(i).setPosX(45);
				personaje.getInventario().get(i).setPosY(140);
				
				switch (personaje.getInventario().get(i).getTipo()) {
				case "espada": {
					g.setColor(Color.red);
					g.drawString(Integer.toString(personaje.getInventario().get(i).getAtaque()), 50, 230);
					break;
				}

				case "escudo": {
					g.setColor(Color.green);
					g.drawString(Integer.toString(personaje.getInventario().get(i).getDefensa()), 50, 230);
					break;
				}
				}
				break;
			}

			case 2: {
				personaje.getInventario().get(i).setPosX(75);
				personaje.getInventario().get(i).setPosY(140);
				
				switch (personaje.getInventario().get(i).getTipo()) {
				case "espada": {
					g.setColor(Color.red);
					g.drawString(Integer.toString(personaje.getInventario().get(i).getAtaque()), 85, 230);
					break;
				}

				case "escudo": {
					g.setColor(Color.green);
					g.drawString(Integer.toString(personaje.getInventario().get(i).getDefensa()), 85, 230);
					break;
				}
				}
				break;
			}
			}
//			if ((personaje.getInventario().size()<2)) {
//				personaje.getInventario().get(i).setPosX(15);
//				personaje.getInventario().get(i).setPosY(125);
//			} else {
//				personaje.getInventario().get(i).setPosY(125);
//				personaje.getInventario().get(i).setPosX(500);
//				
//			}
			personaje.getInventario().get(i).pintarEnMundo(g);
		}

		// MENSAJE
		g.setColor(Color.white);
		if (mensaje != null) {
			g.drawString(mensaje, personaje.getPosX() - 20, personaje.getPosY() + 125);
		}

		if (eventoActivo) {
			g.drawString("Evento Activo", panelJuego.getWidth() - 200, 20);
		} else {
			g.drawString("Evento No Activo", panelJuego.getWidth() - 250, 20);
		}

		if (juegoEnMarcha) {
			g.drawString("Juego en Marcha", panelJuego.getWidth() - 250, 50);
		} else {
			g.drawString("Juego pausado", panelJuego.getWidth() - 200, 50);
		}

		// g.drawString(Integer.toString(aleatorizadorEventos), panelJuego.getWidth() -
		// 150, 150);

	}

	@Override
	public void ejecutarFrame() {
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
				parallaxBosque.add(new Sprite(panelJuego.getWidth() - 5, 0, panelJuego.getWidth(),
						panelJuego.getHeight(), -3, 0, "Imagenes/capaBosque.png"));
			}

			if ((parallaxCielo.get(0).getPosX() + parallaxCielo.get(0).getAncho()) <= 0) {
				parallaxCielo.remove(0);
				parallaxCielo.add(new Sprite(panelJuego.getWidth(), 0, panelJuego.getWidth(), panelJuego.getHeight(),
						-1, 0, "Imagenes/capaCielo.png"));
			}

			// generarObjeto();

			moverSprites();
			comprobarColisiones();
		}

	}

	/**
	 * Genera un objeto en el mundo
	 */
	public void generarObjeto() {
		Runnable hilo = new Runnable() {

			@Override
			public void run() {

				Random rd = new Random();
				int num;

				while (true) {
					num = rd.nextInt(4);

					switch (num) {
					case 0: {
						if (juegoEnMarcha) {
							objetosJuego.add(new ObjetoJuego(panelJuego.getWidth() + 150, panelJuego.getHeight() - 175,
									40, 50, -7, 0, 0, 0, rd.nextInt(25), "pocion", "Imagenes/pocion.png"));
						}
						break;
					}

					case 1: {
						if (juegoEnMarcha) {
							objetosJuego.add(new ObjetoJuego(panelJuego.getWidth() + 150, panelJuego.getHeight() - 175,
									70, 70, -7, 0, personaje.getNivel() * (rd.nextInt(10) + 1), 0, 0, "espada",
									"Imagenes/espada.png"));
						}

						break;
					}

					case 2: {
						if (juegoEnMarcha) {
							objetosJuego.add(new ObjetoJuego(panelJuego.getWidth() + 150, panelJuego.getHeight() - 175,
									70, 70, -7, 0, 0, personaje.getNivel() * (rd.nextInt(10) + 1), 0, "escudo",
									"Imagenes/escudo.png"));
						}

						break;
					}

					case 3: {
						if (juegoEnMarcha) {
							int tamanioEnemigo = rd.nextInt(80) + 20;
							objetosJuego.add(new ObjetoJuego(panelJuego.getWidth() + 150, panelJuego.getHeight() - 175,
									tamanioEnemigo, tamanioEnemigo, -7, 0, 0, 0, rd.nextInt(25), "enemigo",
									"Imagenes/enemigo.png"));
						}

						break;
					}
					}

					try {
						Thread.sleep(rd.nextInt(2000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread hiloGenerador = new Thread(hilo);
		hiloGenerador.start();

	}

	/**
	 * Detiene el juego, dando a todos los objetos velocidad 0
	 */
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

		for (int i = 0; i < objetosJuego.size(); i++) {
			objetosJuego.get(i).setVelX(0);
		}

		juegoEnMarcha = false;
	}

	/**
	 * Reanuda el juego, dándole velocidad a los objetos
	 */
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

		for (int i = 0; i < objetosJuego.size(); i++) {
			objetosJuego.get(i).setVelX(-7);
		}

		juegoEnMarcha = true;
	}

	@Override
	public void pulsarRaton(MouseEvent e) {

		for (int i = 0; i < listaBotones.size(); i++) {
			listaBotones.get(i).comprobarClick(e);
		}

//		if (juegoEnMarcha) {
//			detenerJuego();
//			juegoEnMarcha = false;
//		} else {
//			reanudarJuego();
//			juegoEnMarcha = true;
//		}
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

	public void generarBotones() {
		listaBotones.add(new Boton(personaje.getPosX() - 25, personaje.getPosY() - 75, 50, 50, 0, 0, Color.RED, 0, this,
				objetosJuego));
		listaBotones.add(new Boton(personaje.getPosX() + 35, personaje.getPosY() - 75, 50, 50, 0, 0, Color.GREEN, 1,
				this, objetosJuego));
	}

	public void vaciarMensaje() {
		this.mensaje = null;
	}

	public void desactivarEvento() {
		this.eventoActivo = false;
	}

	private void comprobarColisiones() {

		for (int i = 0; i < objetosJuego.size(); i++) {
			if (personaje.colisiona(objetosJuego.get(i))) {
				switch (objetosJuego.get(i).getTipo()) {

				case "pocion": {
					personaje.incrementarVida(objetosJuego.get(i));
					objetosJuego.remove(i);
					break;
				}

				case "espada": {
					detenerJuego();

					if (!eventoActivo) {
						eventoActivo = true;
						mensaje = "¿Coger " + objetosJuego.get(i).getTipo() + " +" + objetosJuego.get(i).getAtaque()
								+ "?";
						generarBotones();
					}

					break;
				}

				case "escudo": {
					detenerJuego();

					if (!eventoActivo) {
						eventoActivo = true;
						mensaje = "¿Coger " + objetosJuego.get(i).getTipo() + " +" + objetosJuego.get(i).getDefensa()
								+ "?";
						generarBotones();

					}

					break;

				}

				case "enemigo": {
					detenerJuego();

					if (!eventoActivo) {
						eventoActivo = true;
						mensaje = "¿Intentar evasión o combate?";
						generarBotones();
					}

					break;

				}
				}
			}
		}
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
