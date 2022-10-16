package net.kav.soul.util;

import net.minecraft.client.MinecraftClient;

import java.util.Random;

public class arrayofitemtrading {
   private static int dx;

    public static int getarray(int n)
    {

        dx=SoulData.getRandom(((IEntityDataSaver) MinecraftClient.getInstance().player));


        int[] xarray = new int[10];

        int b = dx;
        int r;
        for (int i = 0; i < 10; i++) {
            r = b % 10;
            b = (b - r) / 10;
            xarray[i] = r;

                //System.out.println(xarray[i]);
        }
        int c= (xarray[0]+xarray[1]+xarray[2])*69420;
        Random rand= new Random();

        if(c<10000) {
            while (c < 10000) {
                c = c*3;
                if(c==0)
                {
                    break;
                }
                //System.out.println(c);
            }

        }
        if (c > 99999) {
            while(c>99999)
            { c=c/3;
                if(c==0)
                {

                    break;
                }
            }

           // System.out.println(c);
        }
        //System.out.println(c);
        int[] x1array = new int[10];
        for (int i = 0; i <5; i++) {
            r = c % 10;
            b = (c- r) / 10;
            x1array[i] = r;

                //System.out.println(x1array[i]);
        }
        return x1array[n];
    }
}
