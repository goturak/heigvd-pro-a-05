package com.example.painttest;

import android.graphics.Color;

public enum SpellQuality {PERFECT,EXCELLENT,GOOD,OK,POOR,FAILED;

    public static SpellQuality qualityFromDistance(int dist,int maxDist,int perfectDist){
        int step= (maxDist-perfectDist)/4;
        if(dist<perfectDist){
            return PERFECT;
        }else if(dist<perfectDist+step*1){
            return EXCELLENT;
        }else if(dist<perfectDist+step*2){
            return GOOD;
        }else if(dist<perfectDist+step*3){
            return OK;
        }
        else if(dist<perfectDist+step*4){
            return POOR;
        }else{
            return FAILED;
        }
    }
    public static int colorFromQuality(SpellQuality sq){
        switch (sq){
            case PERFECT: return Color.GREEN;
            case EXCELLENT: return Color.CYAN;
            case GOOD:return Color.MAGENTA;
            case OK: return Color.YELLOW;
            case POOR: return Color.RED;
            default: return Color.BLACK;
        }
    }
}
