package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class FactoryJuegoNormal implements FactoryJuego {
    @Override
    public Tarro crearTarro() {
        Texture bucketTexture = new Texture(Gdx.files.internal("bucket.png"));
        Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
        ComportamientoTarro comportamientoTarro = new ComportamientoTarroNormal();
        return new Tarro(bucketTexture, hurtSound, comportamientoTarro);
    }

    @Override
    public Lluvia crearLluvia() {
        Texture gota = new Texture(Gdx.files.internal("drop.png"));
        Texture gotaMala = new Texture(Gdx.files.internal("dropBad.png"));
        Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        ComportamientoLluvia comportamientoLluvia = new ComportamientoLluviaNormal();
        return new Lluvia(gota, gotaMala, dropSound, rainMusic, comportamientoLluvia);
    }
}

