package com.mygdx.handlers;

public class Box2DConfig {
    // pixels per meter
    public static final float PPM = 100;

    public static float convertSize(float number) {
        return number / PPM;
    }
}
