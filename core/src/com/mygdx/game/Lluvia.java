package com.mygdx.game;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
//hola
public class Lluvia implements InputProcessor  {
    private Array<Rectangle> rainDropsPos;
    private Array<Integer> rainDropsType;
    private long lastDropTime;
    private Texture gotaBuena;
    private Texture gotaMala;
    private Sound dropSound;
    private Music rainMusic;

    public Lluvia(Texture gotaBuena, Texture gotaMala, Sound ss, Music mm) {
        this.rainMusic = mm;
        this.dropSound = ss;
        this.gotaBuena = gotaBuena;
        this.gotaMala = gotaMala;
    }

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
        if (TimeUtils.nanoTime() - lastDropTime > 100000000) crearGotaDeLluvia();
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

    public void actualizarDibujoLluvia(SpriteBatch batch) {
        for (int i = 0; i < rainDropsPos.size; i++) {
            Rectangle raindrop = rainDropsPos.get(i);
            if (rainDropsType.get(i) == 1) {
                batch.draw(gotaMala, raindrop.x, raindrop.y); 
            } else {
                batch.draw(gotaBuena, raindrop.x, raindrop.y); 
            }
        }
    }

    public void destruir() {
        dropSound.dispose();
        rainMusic.dispose();
    }
    public boolean keyDown(int keycode) {
        // Lógica para manejar la tecla presionada en la lluvia
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // Lógica para manejar la tecla liberada en la lluvia
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // Lógica para manejar la entrada de teclado de caracteres en la lluvia
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Lógica para manejar el toque en la pantalla en la lluvia
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // Lógica para manejar el levantamiento del dedo de la pantalla en la lluvia
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // Lógica para manejar el arrastre del dedo en la pantalla en la lluvia
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // Lógica para manejar el movimiento del mouse en la lluvia
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // Lógica para manejar el desplazamiento del mouse (scroll) en la lluvia
        return false;
    }
}
