package com.example.painttest;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;

import java.nio.file.Watchable;

public class Element {
    final static Element FIRE = new Element(new Util().pathFromSVGPath(Util.firePathString),"Fire");
    final static Element EARTH = new Element(new Util().pathFromSVGPath(Util.earthPathString),"Earth");
    final static Element WATER = new Element(new Util().pathFromSVGPath(Util.waterPathString),"Water");
    final static Element LIGHTNING = new Element(new Util().pathFromSVGPath(Util.lightningPathString),"Lightning");
    private final Path path;
    private final String name;
    private RectF bounds;


    public Path getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public RectF getBounds() {
        return bounds;
    }

    private Element(Path path, String name) {
        this.path = path;
        this.name=name;
        this.bounds=new RectF();
        path.computeBounds(bounds,true);
    }


    public static Spell findClosestElement(Path p){
        RectF pBounds = new RectF();
        PathMeasure pm = new PathMeasure(p, false);

        float length= pm.getLength();
        if( length<Util.minTolerableLength){
            return null;
        }
        Vector2 [] pathVec= Util.pathToVectorArray(p,20);
        float minDistance=Float.MAX_VALUE;
                //Util.maxTolerableDistance*(length/Util.minTolerableLength);

        Element[] elements= {FIRE, WATER, EARTH,LIGHTNING};
        p.computeBounds(pBounds,true);
        float pHeight=pBounds.height();
        float pWidth=pBounds.width();
        Path result= null;
        Element elementFound=null;
        for (int i=0; i <elements.length;i++){

            float eWidth= elements[i].getBounds().width();
            float eHeight= elements[i].getBounds().height();

            float widthRatio=pWidth/eWidth;
            float heightRatio= pHeight/eHeight;
            float scale= Math.max(widthRatio,heightRatio);
            float xTranslate=pBounds.centerX()-elements[i].getBounds().centerX();
            float yTranslate=pBounds.centerY()-elements[i].getBounds().centerY();
            Path ePath= new Path(elements[i].getPath());
            Matrix s= new Matrix();


            s.setTranslate(xTranslate,yTranslate);

            ePath.transform(s);
            s.setScale(scale,scale,elements[i].getBounds().centerX()+xTranslate,elements[i].getBounds().centerY()+yTranslate);
            ePath.transform(s);
            Vector2 [] eVec= Util.pathToVectorArray(ePath,20);
            float dist= new FrechetDistance().distance(pathVec,eVec);
            if(dist<minDistance){
                result=ePath;
                elementFound=elements[i];
                minDistance=dist;
            }
        }

        SpellQuality sq=SpellQuality.qualityFromDistance((int)minDistance,(int)(Util.maxTolerableDistance),(int)(Util.maxPerfectDistance));
        return new Spell(elementFound,sq,result);
    }


}
