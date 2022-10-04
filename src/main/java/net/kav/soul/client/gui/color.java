package net.kav.soul.client.gui;

import net.minecraft.util.math.ColorHelper;

public class color {



    public static int colornumber;
    public static int Red=24;
    public static int Blue=255;
    public static int Green=8;
    public static int Argb;
    public color()
    {
        Red= ColorHelper.Argb.getRed(255);
        Blue=ColorHelper.Argb.getBlue(255);
        Green=ColorHelper.Argb.getGreen(255);

    }

    public static int getArgb()
    {
        Argb=ColorHelper.Argb.getArgb(1,255,255,255);
        return Argb;
    }





}
