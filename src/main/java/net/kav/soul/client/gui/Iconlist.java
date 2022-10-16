package net.kav.soul.client.gui;

import net.kav.soul.Soul;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class Iconlist {
   private static List<Identifier> idArrayoutmod= new ArrayList();
   private static List<Identifier> idArrayinmod= new ArrayList();
    private static final Identifier Soul_kav2= new Identifier(Soul.MOD_ID,"textures/soul/");





    public static void setIdArray(String string, int n)
    {
        idArrayoutmod.add(n,new Identifier(string));


    }


}
