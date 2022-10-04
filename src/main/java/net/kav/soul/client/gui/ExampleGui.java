package net.kav.soul.client.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.icon.Icon;
import io.github.cottonmc.cotton.gui.widget.icon.ItemIcon;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.soul.init.RenderInits;
import net.kav.soul.item.Moditems;
import net.kav.soul.networking.ModMessages;
import net.kav.soul.networking.packet.PlayerStatsClient;
import net.kav.soul.networking.packet.commandspackets;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import static net.kav.soul.networking.ModMessages.COMMAND;

public class ExampleGui extends LightweightGuiDescription {
    public ExampleGui(){
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 240);
        root.setInsets(Insets.ROOT_PANEL);

        WSprite[] iconARRAY = new WSprite[10];
       iconARRAY[0] = new WSprite(new Identifier("minecraft:textures/item/redstone.png"));
        iconARRAY[1] = new WSprite(new Identifier("minecraft:textures/item/bamboo.png"));
        iconARRAY[2] = new WSprite(new Identifier("minecraft:textures/item/bell.png"));
        iconARRAY[3] = new WSprite(new Identifier("minecraft:textures/item/birch_sign.png"));
        iconARRAY[4] = new WSprite(new Identifier("minecraft:textures/item/arrow.png"));
        iconARRAY[5] = new WSprite(new Identifier("minecraft:textures/item/arrow.png"));
        iconARRAY[6] = new WSprite(new Identifier("minecraft:textures/item/arrow.png"));
        iconARRAY[7] = new WSprite(new Identifier("minecraft:textures/item/arrow.png"));
        iconARRAY[8] = new WSprite(new Identifier("minecraft:textures/item/beef.png"));
        iconARRAY[9] = new WSprite(new Identifier("minecraft:textures/item/beef.png"));


        int i;

        Random rand = new Random();
        i= rand.nextInt(0,9);
        root.add(iconARRAY[i], 0, 2, 1, 1);

        WButton button = new WButton(new TranslatableText("gui.examplemod.examplebutton"));
        root.add(button, 0, 3, 4, 1);
        //add if and adds items from other mods and set array of items to get the randomizer
        button.setIcon(new ItemIcon(new ItemStack(Moditems.Soul_Ingot)));
        WButton wButton = button.setOnClick(() ->
        {
            //create new nbt that register the 5random digits
            ClientPlayNetworking.send(COMMAND, PacketByteBufs.create());
        });


        root.validate(this);
    }


}

