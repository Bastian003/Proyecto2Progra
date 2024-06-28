package com.mygdx.game;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Gdx;

public class SingletonPreferences {
    private static SingletonPreferences instance;
    private Preferences preferences;

    // Constructor privado para evitar instanciación directa
    private SingletonPreferences() {
        preferences = Gdx.app.getPreferences("MyPreferences");
    }

    // Método estático para obtener la única instancia
    public static SingletonPreferences getInstance() {
        if (instance == null) {
            instance = new SingletonPreferences();
        }
        return instance;
    }

    // Método para obtener las preferencias
    public Preferences getPreferences() {
        return preferences;
    }
}
