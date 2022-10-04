package net.kav.soul.client.gui;


import com.mojang.blaze3d.systems.RenderSystem;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WDynamicLabel;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kav.soul.Soul;

import net.kav.soul.util.GlobalSoul;
import net.kav.soul.util.IEntityDataSaver;
import net.kav.soul.util.SoulData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.resource.LifecycledResourceManager;
import net.minecraft.server.DataPackContents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.system.CallbackI;

@Environment(EnvType.CLIENT)
public class SoulKavOverlay implements HudRenderCallback{

    private static final Identifier Soul_kav = new Identifier(Soul.MOD_ID,"textures/soul/soul_display.png");
    private static final Identifier Soul_kav2= new Identifier(Soul.MOD_ID,"textures/soul/soul_display.png");
    MatrixStack mc;
    public Identifier getSoularray(int x) {
        Identifier[] soularray = new Identifier[3];
        soularray[0] = new Identifier(Soul.MOD_ID,"textures/soul/soul_display.png");
        soularray[1]= new Identifier(Soul.MOD_ID,"textures/soul/soul_display_2.png");
        soularray[2]= new Identifier(Soul.MOD_ID,"textures/soul/bar.png");
        if(x==0)
        {
            return soularray[0];
        }
        else if (x ==1)
        {return soularray[1];}
        else
            return soularray[2];

    }

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
    int x =0;
    int y= 0;

        MinecraftClient client =MinecraftClient.getInstance();
        if(client!=null)
        {
            int width =client.getWindow().getScaledWidth();
            int heigth =client.getWindow().getScaledHeight();
            x=width/2;
            y=heigth;
        }

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);

        RenderSystem.setShaderTexture(0, getSoularray(2));
        DrawableHelper.drawTexture(matrixStack,x+100,y-20,0,0,12,130,45,130,45);

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);

        RenderSystem.setShaderTexture(0, getSoularray(0));
        DrawableHelper.drawTexture(matrixStack,x+135,y-15,0,0,12,12,12,12,12);


        int dx;
        dx=SoulData.getRandom(((IEntityDataSaver) MinecraftClient.getInstance().player));


        int[] xarray = new int[10];

        int b = dx;
        int r;
        for (int i = 0; i < 10; i++) {
            r = b % 10;
            b = (b - r) / 10;
            xarray[i] = r;
            if(r!=0)
                System.out.println(xarray[i]);
        }
        int c= xarray[0]*xarray[1]*xarray[2];
        if(c==0)
        {
            c= xarray[0]*xarray[1];
            if(c==0)
            {c= xarray[0];

            }
        }
        if(c<10000) {
            while (c < 10000) {
                c = c*c;
                System.out.println(c);
            }
        }
        if (c > 99999) {
            while(c>99999)
                c=c/3;
            System.out.println(c);
        }
        System.out.println(c);

//SoulData.getRandom(((IEntityDataSaver) MinecraftClient.getInstance().player))


//
        DrawableHelper.drawCenteredText(matrixStack, MinecraftClient.getInstance().textRenderer, Integer.toString(c), x + 181, y- 40, color.getArgb());

               if(GlobalSoul.getGlobalSoul() >=1000)
               {
                    DrawableHelper.drawCenteredText(matrixStack, MinecraftClient.getInstance().textRenderer, Integer.toString(GlobalSoul.getGlobalSoul()), x + 181, y - 12, color.getArgb());
                }
               else if(GlobalSoul.getGlobalSoul()>=10000){
                    DrawableHelper.drawCenteredText(matrixStack, MinecraftClient.getInstance().textRenderer, Integer.toString(GlobalSoul.getGlobalSoul()), x + 177, y - 12, color.getArgb());
               }
              else if(GlobalSoul.getGlobalSoul()>=100000)
               {
                  DrawableHelper.drawCenteredText(matrixStack, MinecraftClient.getInstance().textRenderer, Integer.toString(GlobalSoul.getGlobalSoul()), x + 173, y - 12, color.getArgb());
                }
               else if(GlobalSoul.getGlobalSoul()>=1000000)
              {
                    DrawableHelper.drawCenteredText(matrixStack, MinecraftClient.getInstance().textRenderer, Integer.toString(GlobalSoul.getGlobalSoul()), x + 169, y - 12, color.getArgb());
               }
               else
                   DrawableHelper.drawCenteredText(matrixStack, MinecraftClient.getInstance().textRenderer, Integer.toString(GlobalSoul.getGlobalSoul()), x + 185, y - 12, color.getArgb());





    }




}



