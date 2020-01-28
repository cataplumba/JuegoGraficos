package pantallas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Character extends ObjetoJuego {
	private int experiencia;
	private int siguienteNivel;
	private ArrayList<ObjetoJuego>inventario;

	public Character(int posX, int posY, int ancho, int alto, int velX, int velY, int nivel, int ataque, int defensa,
			int salud, String ruta) {
		super(posX, posY, ancho, alto, velX, velY, nivel, ataque, defensa, salud, ruta);
		this.nivel = nivel;
		this.ataque = ataque;
		this.defensa = defensa;
		this.salud = salud;
		this.siguienteNivel = 100;
		this.experiencia = 0;
		inventario = new ArrayList<ObjetoJuego>();
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public ArrayList<ObjetoJuego> getInventario() {
		return inventario;
	}

	public void setInventario(ArrayList<ObjetoJuego> inventario) {
		this.inventario = inventario;
	}
	

}
