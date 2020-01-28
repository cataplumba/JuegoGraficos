package pantallas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class Character extends ObjetoJuego {
	private int experiencia;
	private int siguienteNivel;
	private Vector<ObjetoJuego>inventario;

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
	
	public void combatir(ObjetoJuego enemigo) {
		while(salud>0||enemigo.getSalud()>0) {
			
		}
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
	
	public void incrementarAtaque(int ataque) {
		this.ataque+=ataque;
	}
	
	public void decrementarAtaque(int ataque) {
		this.ataque-=ataque;
	}
	
	public void incrementarDefensa(int defensa) {
		this.defensa+=defensa;
	}
	
	public void decrementarDefensa(int defensa) {
		this.defensa-=defensa;
	}
	
	public void incrementarVida(ObjetoJuego pocion) {
		if(this.salud<100) {
			this.salud+=pocion.getSalud();
		}else {
			this.salud=100;
		}
	}
	

}
