package com.example.painttest;


import android.graphics.Path;

public class Attack extends Spell {
    Vector2 direction;

    public Attack(Element element, SpellQuality quality, Path p, Vector2 direction) {
        super(element, quality,p);
        this.direction=direction;
    }
}
