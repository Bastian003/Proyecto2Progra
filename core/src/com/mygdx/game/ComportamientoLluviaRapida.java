package com.mygdx.game;

public class ComportamientoLluviaRapida implements ComportamientoLluvia {
    @Override
    public void crearGotaDeLluvia(Lluvia lluvia) {
        lluvia.crearGotaDeLluviaRapida();
    }
}
