package net.kav.soul.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.kav.soul.Soul;
import net.minecraft.item.Item;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

public class ItemLoader implements SimpleSynchronousResourceReloadListener {


    @Override
    public Identifier getFabricId() {
        return new Identifier("soul", "item_Loader");
    }


    @Override
    public void reload(ResourceManager manager) {
        ItemLoader.clearEveryList();

        for (Identifier id : manager.findResources("items", path -> path.endsWith(".json"))) {

            try {
                InputStream stream = manager.getResource(id).getInputStream();
                JsonObject data = JsonParser.parseReader(new InputStreamReader(stream)).getAsJsonObject();


                if (ItemData.ItemName.contains(data.get("item_name").getAsString())) {
                    Soul.LOGGER.error("Error occurred while loading resource {}", id.toString(), data.get("item_name").getAsString());
                } else {
                    // Id
                    // ItemData.ID.add(data.get("id").getAsInt());
                    //  ItemData.ID_1_9.add(data.get("id_1_9").getAsInt());
                    // Title
                    //ItemData.ItemName.add(data.get("item_name").getAsString());
                    // Level

                    //    ItemData.soulnumber.add(data.get("soul").getAsInt());

                    // Reward
                    ArrayList<Object> itemname = new ArrayList<>();
                    ArrayList<Object> itemnames = new ArrayList<>();
                    for (int i = 0; i < data.getAsJsonArray("item_name").size(); i++) {
                        if (data.getAsJsonArray("item_name").get(i).toString().matches("-?(0|[1-9]\\d*)")) {
                            itemnames.add(data.getAsJsonArray("item_name").get(i).getAsInt());
                        } else {
                            itemname.add(data.getAsJsonArray("item_name").get(i).getAsString());
                            if (!Registry.ITEM.containsId(new Identifier((String) itemname.get(itemname.size() - 1))))
                                Soul.LOGGER.error("Error occurred ",
                                        data.getAsJsonArray("item_name").get(i).getAsString());
                        }
                    }
                    ItemData.ItemName.add(itemname);
                    ItemData.soulnumber.add(itemnames);



                }

            } catch (Exception e) {
                Soul.LOGGER.error("Error occurred while loading resource {}. {}", id.toString(), e.toString());
            }
        }
    }

    public static void clearEveryList() {
        ItemData.ItemName.clear();
        ItemData.itemname.clear();
        ItemData.soulnumber.clear();
    }
}
