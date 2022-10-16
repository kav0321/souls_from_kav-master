package net.kav.soul.util;

public class MaxStats {
    public static float maxstamina=21;
    public static float oldmaxstamina;

    public static float getMaxstamina()
    {
        return maxstamina;
    }

    public static void setMaxstamina(float a)
    {
        maxstamina=a;
    }

    public static void increaseMaxstamina(float amount)
    {
        oldmaxstamina=maxstamina;
        maxstamina+=amount;
    }

    public float getOldmaxstamina()
    {
        return oldmaxstamina;
    }
}
