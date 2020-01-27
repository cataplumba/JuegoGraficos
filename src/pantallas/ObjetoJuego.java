package pantallas;

import java.awt.Color;

import principal.Sprite;

public class ObjetoJuego extends Sprite {

	private String tipo;
	private int ataque;
	private int defensa;
	private int salud;

	public ObjetoJuego(int posX, int posY, int ancho, int alto, int velX, int velY, String ruta) {
		super(posX, posY, ancho, alto, velX, velY, ruta);
		// TODO Auto-generated constructor stub
	}

	public ObjetoJuego(int posX, int posY, int ancho, int alto, int velX, int velY, String tipo,
			int ataque, int defensa, int salud, String ruta) {
		super(posX, posY, ancho, alto, velX, velY, ruta);
		this.tipo = tipo;
		this.ataque = ataque;
		this.defensa = defensa;
		this.salud = salud;
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

	@Override
	public String toString() {
		return "ObjetoJuego [tipo=" + tipo + ", ataque=" + ataque + ", defensa=" + defensa + ", salud=" + salud + "]";
	}
	
	

}
