package net.kav.soul.util;

import net.minecraft.client.MinecraftClient;

public class GlobalSoul {


  private static int soulr;
  private static float stamina;
  private static int Itemtype;



    public static  int getGlobalSoul()
    {
        return soulr;
    }

    public static void setGlobalSoul(int soul)
    {

        soulr=soul;
    }

  public static  int getItemtype()
  {
    return Itemtype;
  }

  public static void setItemtype(int soul)
  {

    Itemtype=soul;
  }

    public static float getStamina(){return stamina;};
    public static void setStamina(float amount){stamina=amount;}
}
