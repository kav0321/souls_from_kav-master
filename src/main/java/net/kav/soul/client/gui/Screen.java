package net.kav.soul.client.gui;


import com.mojang.blaze3d.systems.RenderSystem;
import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.client.LibGui;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kav.soul.init.RenderInits;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class Screen extends CottonClientScreen {

    public Screen(GuiDescription description)
    {
        super(description);

    }



}
