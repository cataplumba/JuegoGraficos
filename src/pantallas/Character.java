package pantallas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class Character extends ObjetoJuego {
	private int experiencia;
	private int siguienteNivel;
	private Vector<ObjetoJuego> inventario;

	public Character(int posX, int posY, int ancho, int alto, int velX, int velY, int nivel, int ataque, int defensa,
			int salud, String ruta) {
		super(posX, posY, ancho, alto, velX, velY, nivel, ataque, defensa, salud, ruta);
		this.nivel = nivel;
		this.ataque = ataque;
		this.defensa = defensa;

		this.salud = salud;
		this.siguienteNivel = 100;
		this.experiencia = 0;
		inventario = new Vector<ObjetoJuego>();
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public Vector<ObjetoJuego> getInventario() {
		return inventario;
	}

	public void setInventario(Vector<ObjetoJuego> inventario) {
		this.inventario = inventario;
	}

	/**
	 * Le da al personaje la experiencia que entra como parámetro
	 * 
	 * @param experienciaGanada
	 */
	public void ganarExperiencia(int experienciaGanada) {
		this.experiencia += experienciaGanada;

		// Si conseguimos la experiencia necesaria, subiremos de nivel
		if (experiencia == siguienteNivel) {
			nivel++;
			experiencia = 0;
			siguienteNivel *= 0.5;
		}
	}

	/**
	 * Incrementa el ataque del personaje
	 * 
	 * @param ataque
	 * 
	 *               Si el ataque del personaje es superior o igual a 100 no se
	 *               incrementa, y se establece en 100
	 */
	public void incrementarAtaque(int ataque) {
		if ((this.ataque + ataque) >= 100) {
			this.ataque = 100;
		} else {
			this.ataque += ataque;
		}
	}

	/**
	 * Decrementa el ataque del personaje
	 * 
	 * @param ataque
	 * 
	 *               Si el ataque del personaje es inferior o igual a 1 no se
	 *               decrementa, y se establece en 1
	 */
	public void decrementarAtaque(int ataque) {
		if ((this.ataque - ataque) <= 1) {
			this.ataque = 1;
		} else {
			this.ataque -= ataque;
		}
	}

	/**
	 * Incrementa la defensa del personaje
	 * 
	 * @param ataque
	 * 
	 *               Si la defensa del personaje es superior o igual a 100 no se
	 *               incrementa, y se establece en 100
	 */
	public void incrementarDefensa(int defensa) {
		if ((this.defensa + defensa) >= 100) {
			this.defensa = 100;
		} else {
			this.defensa += defensa;
		}
	}

	/**
	 * Decrementa la defensa del personaje
	 * 
	 * @param ataque
	 * 
	 *               Si la defensa del personaje es inferior o igual a 1 no se
	 *               decrementa, y se establece en 1
	 */
	public void decrementarDefensa(int defensa) {
		if ((this.defensa - defensa) <= 1) {
			this.defensa = 1;
		} else {
			this.defensa -= defensa;
		}
	}

	/**
	 * El personaje combate contra un objeto
	 * 
	 * @param enemigo
	 */
	public int combatir(ObjetoJuego enemigo) {
		while (salud > 0 && enemigo.getSalud() > 0) {
			System.out.println("Salud Personaje: " + salud);
			System.out.println("Salud Enemigo: " + enemigo.getSalud());
			enemigo.setSalud(enemigo.getSalud() - this.ataque);
			if (enemigo.getSalud() > 0) {
				this.salud -= enemigo.getAtaque();
			}
		}
		return salud > 0 ? 1 : 0;
	}

	public void incrementarVida(ObjetoJuego pocion) {
		if (this.salud < 100) {
			this.salud += pocion.getSalud();
		} else {
			this.salud = 100;
		}
	}

	public int getSiguienteNivel() {
		return siguienteNivel;
	}

	public void setSiguienteNivel(int siguienteNivel) {
		this.siguienteNivel = siguienteNivel;
	}

}
