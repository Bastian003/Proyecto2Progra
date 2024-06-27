package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia extends ElementoJuego {
    private Array<Rectangle> rainDropsPos;
    private Array<Integer> rainDropsType;
    private long lastDropTime;
    private Texture gotaBuena;
    private Texture gotaMala;
    private Sound dropSound;
    private Music rainMusic;
    
    public void actualizarDibujoLluvia(SpriteBatch batch) {
        dibujar(batch); // Llamar al método dibujar de Lluvia para actualizar el dibujo de la lluvia
    }


    public Lluvia(Texture gotaBuena, Texture gotaMala, Sound dropSound, Music rainMusic) {
        this.gotaBuena = gotaBuena;
        this.gotaMala = gotaMala;
        this.dropSound = dropSound;
        this.rainMusic = rainMusic;
    }

    @Override
    public void crear() {
        rainDropsPos = new Array<>();
        rainDropsType = new Array<>();
        crearGotaDeLluvia();
        rainMusic.setLooping(true);
        rainMusic.play();
    }

    private void crearGotaDeLluvia() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        rainDropsPos.add(raindrop);
        if (MathUtils.random(1, 10) < 3)
            rainDropsType.add(1);
        else
            rainDropsType.add(2);
        lastDropTime = TimeUtils.nanoTime();
    }

    public void actualizarMovimiento(Tarro tarro) {
        if (TimeUtils.nanoTime() - lastDropTime > 100000000)
            crearGotaDeLluvia();
        for (int i = 0; i < rainDropsPos.size; i++) {
            Rectangle raindrop = rainDropsPos.get(i);
            raindrop.y -= 300 * Gdx.graphics.getDeltaTime();
            if (raindrop.y + 64 < 0) {
                rainDropsPos.removeIndex(i);
                rainDropsType.removeIndex(i);
            }
            if (raindrop.overlaps(tarro.getArea())) {
                if (rainDropsType.get(i) == 1) {
                    tarro.dañar();
                } else {
                    tarro.sumarPuntos(10);
                    dropSound.play();
                }
                rainDropsPos.removeIndex(i);
                rainDropsType.removeIndex(i);
            }
        }
    }

    public void dibujar(SpriteBatch batch) {
        for (int i = 0; i < rainDropsPos.size; i++) {
            Rectangle raindrop = rainDropsPos.get(i);
            if (rainDropsType.get(i) == 1) {
                batch.draw(gotaMala, raindrop.x, raindrop.y);
            } else {
                batch.draw(gotaBuena, raindrop.x, raindrop.y);
            }
        }
    }

    @Override
    public void destruir() {
        dropSound.dispose();
        rainMusic.dispose();
    }

    @Override
    public void actualizarMovimiento() {
        // Método de la superclase, no se utiliza aquí
        throw new UnsupportedOperationException("Not supported yet.");
    }
}