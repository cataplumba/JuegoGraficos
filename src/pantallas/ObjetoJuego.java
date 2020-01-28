package pantallas;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

import principal.Sprite;

public class ObjetoJuego extends Sprite {
	protected String tipo;
	protected int nivel;
	protected int ataque;
	protected int defensa;
	protected int salud;
	

	public ObjetoJuego(int posX, int posY, int ancho, int alto, int velX, int velY, String ruta) {
		super(posX, posY, ancho, alto, velX, velY, ruta);
		// TODO Auto-generated constructor stub
	}

	public ObjetoJuego(int posX, int posY, int ancho, int alto, int velX, int velY, int nivel,
			int ataque, int defensa, int salud, String ruta) {
		super(posX, posY, ancho, alto, velX, velY, ruta);
		this.nivel = nivel;
		this.ataque = ataque;
		this.defensa = defensa;
		this.salud = salud;
	}
	
	public ObjetoJuego(int posX, int posY, int ancho, int alto, int velX, int velY,
			int ataque, int defensa, int salud,String tipo, String ruta) {
		super(posX, posY, ancho, alto, velX, velY, ruta);
		this.ataque = ataque;
		this.defensa = defensa;
		this.salud = salud;
		this.tipo = tipo;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public int getSalud() {
		return salud;
	}

	public void setSalud(int salud) {
		this.salud = salud;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "ObjetoJuego [nivel=" + nivel + ", ataque=" + ataque + ", defensa=" + defensa + ", salud=" + salud + "]";
	}
	
	

}
