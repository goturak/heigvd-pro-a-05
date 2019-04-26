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

    public Spell getSpellSpecialization(Path p){
        Vector2[] vs=Util.pathToVectorArray(p,2);
        Vector2 dir=vs[vs.length-1].sub(vs[0]);
        return new Attack(this.element,this.quality,this.path,dir);
    }
}
