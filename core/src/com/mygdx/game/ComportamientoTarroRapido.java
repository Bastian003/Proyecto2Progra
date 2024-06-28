package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class ComportamientoTarroRapido implements ComportamientoTarro {
    @Override
    public void actualizarMovimiento(Tarro tarro) {
        // Implementación de movimiento rápido del tarro
        tarro.actualizarMovimientoRapido();
    }

	@Override
	public void crear(Texture tex) {
		
	}
}
