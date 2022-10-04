package net.kav.soul.util;

import net.minecraft.client.MinecraftClient;

public class GlobalSoul {


  private static int soulr;

    public static  int getGlobalSoul()
    {
        return soulr;
    }

    public static void setGlobalSoul(int soul)
    {

        soulr=soul;
    }
}
