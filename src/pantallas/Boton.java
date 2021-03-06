package pantallas;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Vector;

import principal.PanelJuego;
import principal.Sprite;

public class Boton extends Sprite {
	private int accion;
	private PantallaJuego pantallaJuego;
	private PanelJuego panel;

	public Boton(int posX, int posY, int ancho, int alto, int velX, int velY, Color color, int accion,
			PantallaJuego pantallaJuego, PanelJuego panel, Vector<ObjetoJuego> listaObjeto) {
		super(posX, posY, ancho, alto, velX, velY, color);
		this.accion = accion;
		this.pantallaJuego = pantallaJuego;
		this.panel = panel;

	}

	/**
	 * Registra un evento de ratón en la posición del Sprite
	 * 
	 * @param e
	 */
	public void comprobarClick(MouseEvent e) {
		switch (pantallaJuego.objetosJuego.get(0).getTipo()) {
		case ("espada"): {
			eventoObjeto(e);
			break;
		}

		case ("escudo"): {
			eventoObjeto(e);
			break;
		}

		case ("enemigo"): {
			eventoEnemigo(e);
			break;
		}
		}
	}

	/**
	 * Evento que se da cuando el jugador se encuentra con un enemigo
	 * 
	 * @param e
	 * 
	 *          El jugador debe decidir si luchar contra el enemigo o intentar
	 *          esquivarlo
	 */
	public void eventoEnemigo(MouseEvent e) {
		Random rd = new Random();
		boolean clickX = (e.getX() >= posX) && (e.getX() <= posX + ancho);
		boolean clickY = (e.getY() >= posY) && (e.getY() <= posY + alto);

		if (clickX && clickY) {
			System.out.println("Click");
			switch (accion) {
			// Caso afirmativo. Se procede al combate.
			case 1: {
				pantallaJuego.setMensaje("Combatiendo....");

				if (pantallaJuego.personaje.combatir(pantallaJuego.objetosJuego.get(0)) == 0) {
					panel.setPantalla(new PantallaFinal("Imagenes/youdied.jpg", panel));
				} else {
					pantallaJuego.objetosJuego.remove(0);
					pantallaJuego.desactivarEvento();
					pantallaJuego.reanudarJuego();
					pantallaJuego.personaje.ganarExperiencia(rd.nextInt(50) * (pantallaJuego.personaje.getNivel()));
					pantallaJuego.setMensaje("Has vencido a enemigo. Te faltan "
							+ (pantallaJuego.personaje.getSiguienteNivel() - pantallaJuego.personaje.getExperiencia())
							+ " puntos de experiencia para el siguiente nivel");
				}
				while (!pantallaJuego.listaBotones.isEmpty()) {
					pantallaJuego.listaBotones.remove(0);
				}
				break;
			}

			// Caso negativo, se intenta evadir al enemigo. Si no se consigue, se combate
			// contra él.
			case 0: {
				System.out.println("Click");
				int num = rd.nextInt();

				if (num == 0) {
					pantallaJuego.setMensaje("Enemigo evadido con éxito");
					pantallaJuego.reanudarJuego();
					pantallaJuego.desactivarEvento();
					pantallaJuego.objetosJuego.remove(0);
				} else {
					pantallaJuego.personaje.combatir(pantallaJuego.objetosJuego.get(0));
					pantallaJuego.reanudarJuego();
					pantallaJuego.desactivarEvento();
					pantallaJuego.personaje.ganarExperiencia(rd.nextInt(50) * pantallaJuego.personaje.getNivel());
					pantallaJuego.setMensaje("Has vencido a enemigo. Te faltan "
							+ (pantallaJuego.personaje.getSiguienteNivel() - pantallaJuego.personaje.getExperiencia())
							+ " puntos de experiencia para el siguiente nivel");
				}
				while (!pantallaJuego.listaBotones.isEmpty()) {
					pantallaJuego.listaBotones.remove(0);
				}
				break;
			}
				
			}
		}
	}

	/**
	 * El jugador encuentra un objeto y debe decidir si cogerlo o dejarlo en el
	 * suelo
	 * 
	 * @param e
	 */
	public void eventoObjeto(MouseEvent e) {

		boolean clickX = (e.getX() >= posX) && (e.getX() <= posX + ancho);
		boolean clickY = (e.getY() >= posY) && (e.getY() <= posY + alto);

		if (clickX && clickY) {
			switch (accion) {
			// Caso afirmativo. Se recoge el objeto.
			case 1: {

				// Si el personaje tiene más de 3 objetos, se elimina el primero que recogió
				if (pantallaJuego.personaje.getInventario().size() >= 3) {

					if (pantallaJuego.personaje.getInventario().get(0).getTipo().equalsIgnoreCase("espada")) {
						pantallaJuego.personaje
								.decrementarAtaque(pantallaJuego.personaje.getInventario().get(0).getAtaque());
					} else {
						pantallaJuego.personaje
								.decrementarDefensa(pantallaJuego.personaje.getInventario().get(0).getDefensa());
					}

					// Se incrementa el ataque o la defensa del personaje en función del objeto que
					// recoge
					if (pantallaJuego.objetosJuego.get(0).getTipo().equalsIgnoreCase("espada")) {
						pantallaJuego.personaje.incrementarAtaque(pantallaJuego.objetosJuego.get(0).getAtaque());
					} else {
						pantallaJuego.personaje.incrementarDefensa(pantallaJuego.objetosJuego.get(0).getDefensa());
					}

					pantallaJuego.personaje.getInventario().add(pantallaJuego.objetosJuego.get(0));
					pantallaJuego.personaje.getInventario().remove(0);
					pantallaJuego.objetosJuego.remove(0);
				} else {
					// Se incrementa el ataque o la defensa del personaje en función del objeto que
					// recoge
					if (pantallaJuego.objetosJuego.get(0).getTipo().equalsIgnoreCase("espada")) {
						pantallaJuego.personaje.incrementarAtaque(pantallaJuego.objetosJuego.get(0).getAtaque());
					} else {
						pantallaJuego.personaje.incrementarDefensa(pantallaJuego.objetosJuego.get(0).getDefensa());
					}
					pantallaJuego.personaje.getInventario().add(pantallaJuego.objetosJuego.get(0));

					pantallaJuego.objetosJuego.remove(0);
				}

				pantallaJuego.reanudarJuego();
				pantallaJuego.vaciarMensaje();

				while (!pantallaJuego.listaBotones.isEmpty()) {
					pantallaJuego.listaBotones.remove(0);
				}
				pantallaJuego.desactivarEvento();
				break;
			}

			// Caso negativo. Se rechaza el objeto.
			case 0: {

				if (!pantallaJuego.objetosJuego.isEmpty()) {
					pantallaJuego.objetosJuego.remove(0);
				}

				pantallaJuego.vaciarMensaje();
				pantallaJuego.reanudarJuego();

				while (!pantallaJuego.listaBotones.isEmpty()) {
					pantallaJuego.listaBotones.remove(0);
				}
				pantallaJuego.desactivarEvento();
				break;
			}
			}
		}
	}

}
