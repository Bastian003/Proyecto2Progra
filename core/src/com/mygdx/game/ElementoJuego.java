package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public abstract class ElementoJuego {

 public abstract void crear();
 public abstract void actualizarMovimiento();
 public abstract void dibujar(SpriteBatch batch);
 public abstract void destruir();
 public void actualizarMovimiento(Tarro tarro) {
	// TODO Auto-generated method stub
	
}
public void dañar() {
	// TODO Auto-generated method stub
	
}
}