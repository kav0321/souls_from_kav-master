package net.kav.soul.client.gui;

import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.icon.Icon;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


@Environment(EnvType.CLIENT)
public class Wbuy extends WButton {

    @Override
    public void paint(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {
        Icon icon;
        if (this.isEnabled()) {
            boolean hovered = (mouseX >= 0 && mouseY >= 0 && mouseX < getWidth() && mouseY < getHeight());
            icon = new TextureIcon(new Identifier("soul:textures/gui/buy.png"));
            if (hovered) {
                icon = new TextureIcon(new Identifier("soul:textures/gui/buyselected.png"));
            }
        } else {
            icon = new TextureIcon(new Identifier("soul:textures/gui/buydisable.png"));
        }
        icon.paint(matrices, x , y, 11);
    }

    @Override
    public void setSize(int x, int y) {
        this.width = 19;
        this.height = 7;
    }


    public Wbuy(Text label) {
        this.alignment = HorizontalAlignment.CENTER;
    }
}
