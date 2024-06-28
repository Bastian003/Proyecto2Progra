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
    private ComportamientoLluvia comportamientoLluvia;

    public Lluvia(Texture gotaBuena, Texture gotaMala, Sound dropSound, Music rainMusic, ComportamientoLluvia comportamientoLluvia) {
        this.gotaBuena = gotaBuena;
        this.gotaMala = gotaMala;
        this.dropSound = dropSound;
        this.rainMusic = rainMusic;
        this.comportamientoLluvia = comportamientoLluvia;
    }

    // Constructor adicional si se necesita para otras configuraciones
    public Lluvia(Texture gotaBuena, Texture gotaMala, Sound dropSound, Music rainMusic) {
        this(gotaBuena, gotaMala, dropSound, rainMusic, new ComportamientoLluviaNormal());
    }

    @Override
    public void crear() {
        rainDropsPos = new Array<>();
        rainDropsType = new Array<>();
        comportamientoLluvia.crearGotaDeLluvia(this); // Usar el comportamiento para crear gotas
        rainMusic.setLooping(true);
        rainMusic.play();
    }

    public void crearGotaDeLluvia() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        rainDropsPos.add(raindrop);

        if (MathUtils.random(1, 10) < 3) {
            rainDropsType.add(1); // Gotas malas
        } else {
            rainDropsType.add(2); // Gotas buenas
        }

        lastDropTime = TimeUtils.nanoTime();
    }

    public void crearGotaDeLluviaRapida() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        rainDropsPos.add(raindrop);

        if (MathUtils.random(1, 10) < 5) {
            rainDropsType.add(1); // Gotas malas
        } else {
            rainDropsType.add(2); // Gotas buenas
        }

        lastDropTime = TimeUtils.nanoTime();
    }

    public void actualizarDibujoLluvia(SpriteBatch batch) {
        dibujar(batch); // Llamar al método dibujar de Lluvia para actualizar el dibujo de la lluvia
    }

    public void actualizarMovimiento(Tarro tarro) {
        if (TimeUtils.nanoTime() - lastDropTime > 100000000) {
            comportamientoLluvia.crearGotaDeLluvia(this); // Usar el comportamiento para crear gotas
        }
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

    public void setComportamiento(ComportamientoLluvia comportamiento) {
        this.comportamientoLluvia = comportamiento;
    }

    @Override
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
