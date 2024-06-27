package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameLluvia extends Game {
    private OrthographicCamera camera;
    public SpriteBatch batch;
    private BitmapFont font;
    private SingletonPreferences singletonPrefs; // Instancia del SingletonPreferences
    private Tarro tarro;
    private Lluvia lluvia;
    private boolean gameStarted;

    @Override
    public void create () {
        font = new BitmapFont();
        font.getData().setScale(2);
        singletonPrefs = SingletonPreferences.getInstance(); // Obtener la instancia del SingletonPreferences
        Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
        tarro = new Tarro(new Texture(Gdx.files.internal("bucket.png")), hurtSound);
        Texture gota = new Texture(Gdx.files.internal("drop.png"));
        Texture gotaMala = new Texture(Gdx.files.internal("dropBad.png"));
        Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        lluvia = new Lluvia(gota, gotaMala, dropSound, rainMusic);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        tarro.crear();
        lluvia.crear();
        gameStarted = false;
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        if (!gameStarted) {
            font.draw(batch, "Atrapa la gota", 350, 300);
            font.draw(batch, "Toca cualquier lugar para iniciar el juego", 250, 200);
            if (Gdx.input.isTouched()) {
                gameStarted = true;
            }
        } else if (tarro.getVidas() <= 0) {
            font.draw(batch, "Oh no! Perdiste todas tus vidas", 280, 240);
            font.draw(batch, "Vuelve a intentarlo", 320, 200);
            int highScore = singletonPrefs.getPreferences().getInteger("highScore", 0);
            if (tarro.getPuntos() > highScore) {
                singletonPrefs.getPreferences().putInteger("highScore", tarro.getPuntos());
                singletonPrefs.getPreferences().flush();
            }
        } else {
            font.draw(batch, "Gotas totales: " + tarro.getPuntos(), 5, 475);
            font.draw(batch, "Vidas : " + tarro.getVidas(), 680, 475);
            int highScore = singletonPrefs.getPreferences().getInteger("highScore", 0);
            font.draw(batch, "Puntaje más alto: " + highScore, 5, 450);
            if (!tarro.estaHerido()) {
                tarro.actualizarMovimiento();        
                lluvia.actualizarMovimiento(tarro);   
            }
            tarro.dibujar(batch);
            lluvia.actualizarDibujoLluvia(batch);
        }
        batch.end();  
    }

    @Override
    public void dispose() {
        tarro.destruir();
        lluvia.destruir();
        batch.dispose();
        font.dispose();
    }
}