package net.kav.soul.client.gui;

import io.github.cottonmc.cotton.gui.widget.WBox;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import io.github.cottonmc.cotton.gui.widget.icon.Icon;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kav.soul.Soul;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class boxsoul extends WBox {


    private static final Identifier Soul_kav2= new Identifier(Soul.MOD_ID,"textures/gui/back.png");

    public boxsoul(Axis axis) {
        super(axis);
    }


    @Environment(EnvType.CLIENT)
    @Override
    public void paint(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {
        // This is the most important method of a custom widget, where you decide how it'll look.
        // If you want the whole texture
        Icon icon =new TextureIcon(new Identifier("textures/gui/back.png"));
        icon.paint(matrices, x + 1, y + 1, 13);
        //ScreenDrawing.texturedRect(matrices, x, y, width, height, Soul_kav2, 1, 0.5f, 0f, 0f, 0xFFFFFFFF);
        // or for partial textures:
        //ScreenDrawing.texturedRect(matrices, x, y, width, height, Soul_kav2, 0, 0f, 0.5f, 0.5f, 0xFFFFFFFF);
        // ScreenDrawing.texturedRect(matrices, x, y, 20, 20, Soul_kav2, 0, 0.2f, 0.2f, 0f, 0xFFFFFFFF);
        // u1, v1, u2, v2 are fractions of the texture dimensions, so for example for the top half (u: 0-1, v: 0-0.5):
        //ScreenDrawing.texturedRect(matrices, x, y, width, height, Soul_kav2, 0f, 0f, 1f, 0.5f, 0xFFFFFFFF);

        // the 0xFFFFFFFF is the color in ARGB format, which in this case is white
    }
}
