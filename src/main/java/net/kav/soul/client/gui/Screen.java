package net.kav.soul.client.gui;


import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class Screen extends CottonClientScreen {
    @Override
    public boolean shouldPause() {
        return false;
    }


    public Screen(GuiDescription description)
    {
        super(description);

    }



}
