package net.kav.soul.data;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemType {
    private static String ItemType;
    public static Map<Item,Integer> item= new HashMap<>();
    public static Map<String,Integer> itemcustom= new HashMap<>();
    private static List<Item> items= new ArrayList<>();
    private static List<String> itemcustomlist= new ArrayList<>();
    public static int number_of_items;
    public static int number_of_items_custom;

public ItemType()
{

    item.put(Items.DIAMOND_SWORD,2);
    item.put(Items.NETHERITE_SWORD,2);
    item.put(Items.IRON_SWORD,2);
    item.put(Items.WOODEN_SWORD,2);
    item.put(Items.GOLDEN_SWORD,2);
    item.put(Items.DIAMOND_AXE,4);
    item.put(Items.NETHERITE_AXE,4);
    item.put(Items.IRON_AXE,4);
    item.put(Items.GOLDEN_AXE,5);
    item.put(Items.WOODEN_AXE,5);
    number_of_items=10;
    number_of_items_custom=0;
    items.add(Items.DIAMOND_SWORD);
    items.add(Items.NETHERITE_SWORD);
    items.add(Items.IRON_SWORD);
    items.add(Items.WOODEN_SWORD);
    items.add(Items.GOLDEN_SWORD);
    items.add(Items.DIAMOND_AXE);
    items.add(Items.NETHERITE_AXE);
    items.add(Items.IRON_AXE);
    items.add(Items.GOLDEN_AXE);
    items.add(Items.WOODEN_AXE);


}


    public static void ItemSortadd(String itemt,int i)
    {
        itemcustomlist.add(itemt);
        itemcustom.put(itemt,i);
        number_of_items_custom++;
    }


    public static boolean ItemNames(Item itemt)
    {
        for(int i=0;i<number_of_items;i++)
        {
            System.out.println("List:"+ items.get(i)+"$end&");
            System.out.println("Deez nute:"+itemt+"$end&");
            String string= "Diamond Sword";
            if(items.get(i).getTranslationKey()==itemt.getTranslationKey())
            {
                System.out.println("Deez nute:"+itemt);
                return true;
            }

        }
        return false;
    }

    public static boolean ItemNames(String itemt)
    {
        for(int i=0;i<number_of_items;i++)
        {
            System.out.println("List:"+ items.get(i)+"$end&");
            System.out.println("Deez nute:"+itemt+"$end&");
            String string= "Diamond Sword";
            if(items.get(i).toString()==itemt)
            {
                System.out.println("Deez nute:"+itemt);
                return true;
            }

        }
        return false;
    }



}
