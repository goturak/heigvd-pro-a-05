package com.example.painttest;


import android.graphics.Path;

public class Spell {
    private Element element;
    private SpellQuality quality;
    private Path path;
    public Spell(Element element, SpellQuality quality,Path path) {
        this.element = element;
        this.quality = quality;
        this.path=path;
    }

    public Element getElement() {
        return element;
    }

    public SpellQuality getQuality() {
        return quality;
    }

    public Path getPath() {
        return path;
    }
}
