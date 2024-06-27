package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Dibujable {
    void crear();
    void actualizarMovimiento();
    void dibujar(SpriteBatch batch);
    void destruir();
}