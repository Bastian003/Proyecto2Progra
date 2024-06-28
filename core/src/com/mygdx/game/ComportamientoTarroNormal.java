package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class ComportamientoTarroNormal implements ComportamientoTarro {
	@Override
    public void actualizarMovimiento(Tarro tarro) {
		int velx = 400;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) tarro.getArea().x -= velx * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) tarro.getArea().x += velx * Gdx.graphics.getDeltaTime();
        if (tarro.getArea().x < 0) tarro.getArea().x = 0;
        if (tarro.getArea().x > 800 - 64) tarro.getArea().x = 800 - 64;
    }

	@Override
	public void crear(Texture tex) {
		
	}
}
