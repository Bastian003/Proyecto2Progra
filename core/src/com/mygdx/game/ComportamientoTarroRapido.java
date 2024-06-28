package com.mygdx.game;

public class ComportamientoTarroRapido implements ComportamientoTarro {
    @Override
    public void actualizarMovimiento(Tarro tarro) {
        // Implementación de movimiento rápido del tarro
        tarro.actualizarMovimientoRapido();
    }
}
