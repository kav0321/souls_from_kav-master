package net.kav.soul.client.gui;


import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.kav.soul.Soul;
import net.kav.soul.util.GlobalSoul;
import net.kav.soul.util.MaxStats;
import net.kav.soul.util.arrayofitemtrading;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SoulKavOverlay implements HudRenderCallback{

    private static final Identifier Soul_kav = new Identifier(Soul.MOD_ID,"textures/soul/soul_display.png");
    private static final Identifier Soul_kav2= new Identifier(Soul.MOD_ID,"textures/soul/soul_display.png");

    private static final Identifier Stamina_StartFull= new Identifier(Soul.MOD_ID,"textures/gui/stamina/stamina_full.png");
    private static final Identifier Stamina_StartEmpty= new Identifier(Soul.MOD_ID,"textures/gui/stamina/stamina_empty.png");

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
        float stamina = GlobalSoul.getStamina();
        float maximumsta= MaxStats.getMaxstamina();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);

        RenderSystem.setShaderTexture(0, getSoularray(2));
        DrawableHelper.drawTexture(matrixStack,x+100,y-20,0,0,12,130,45,130,45);

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);

        RenderSystem.setShaderTexture(0, getSoularray(0));
        DrawableHelper.drawTexture(matrixStack,x+135,y-15,0,0,12,12,12,12,12);



//SoulData.getRandom(((IEntityDataSaver) MinecraftClient.getInstance().player))


//
        DrawableHelper.drawCenteredText(matrixStack, MinecraftClient.getInstance().textRenderer, Integer.toString(arrayofitemtrading.getarray(4)), x + 181, y- 40, color.getArgb());

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

        int number_of_bar;
        int number_of_bars;
        int numbers_of_bar_befor;
        int fixed_bar;
        int y22=0;
       number_of_bars  = ((int) maximumsta)%5;

       if(number_of_bars!=0)
       {
           fixed_bar=1;
       }
       else
           fixed_bar =0;
       numbers_of_bar_befor= ((int) maximumsta)/5;
       number_of_bar= fixed_bar+numbers_of_bar_befor;



        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);

        RenderSystem.setShaderTexture(0, Stamina_StartEmpty);
        DrawableHelper.drawTexture(matrixStack,x-120,y-30,0,0,0,42,5,42,35);

        for(int i=0;i< number_of_bar - 2;i++)
        {y22=x-110+10*i;
        RenderSystem.setShaderTexture(0, Stamina_StartEmpty);
        DrawableHelper.drawTexture(matrixStack,x-110+10*i,y-30,0,0,5,42,5,42,35);}

        int z;
        if(number_of_bars==1)
        {
            RenderSystem.setShaderTexture(0, Stamina_StartEmpty);
            DrawableHelper.drawTexture(matrixStack,y22+10,y-30,0,0,10,42,5,42,35);
        }
        else if(number_of_bars==2)
        {
            RenderSystem.setShaderTexture(0, Stamina_StartEmpty);
            DrawableHelper.drawTexture(matrixStack,y22+10,y-30,0,0,15,42,5,42,35);
        }
        else if(number_of_bars==3)
        {
            RenderSystem.setShaderTexture(0, Stamina_StartEmpty);
            DrawableHelper.drawTexture(matrixStack,y22+10,y-30,0,0,20,42,5,42,35);
        }
        else if(number_of_bars==4)
        {
            RenderSystem.setShaderTexture(0, Stamina_StartEmpty);
            DrawableHelper.drawTexture(matrixStack,y22+10,y-30,0,0,25,42,5,42,35);
        }
        else if(number_of_bars==0)
        {
            RenderSystem.setShaderTexture(0, Stamina_StartEmpty);
            DrawableHelper.drawTexture(matrixStack,y22+10,y-30,0,0,30,42,5,42,35);
        }


       float per=(GlobalSoul.getStamina()/MaxStats.getMaxstamina());
        DrawableHelper.drawCenteredText(matrixStack, MinecraftClient.getInstance().textRenderer,"s"+Float.toString(per), x + 181, y - 50, color.getArgb());
       // client.player.sendMessage(Text.of(Integer.toString(number_of_bar)),false);
       // float per=100;


        float con= ((per)*number_of_bar);



        int fl=0;
        int x23;
        fl = (int) (stamina);
        if(fl<0)
        {
            fl=0;
        }

        x23=32+2*fl;
        if(x23>42)
        {
            x23=42;
        }

            RenderSystem.setShaderTexture(0, Stamina_StartFull);

            DrawableHelper.drawTexture(matrixStack, x - 120, y - 30, 0, 0, 0, x23, 5, 42, 35);








        for (int i = 0; i < number_of_bar - 2; i++) {


            fl =  ((int)stamina-(5+i*5));
            if(fl<0)
            {
                fl=0;
            }

            x23=32+2*fl;
            if(x23>42)
            {
                x23=42;
            }


                y22 = x - 110 + 10 * i;
                RenderSystem.setShaderTexture(0, Stamina_StartFull);
                DrawableHelper.drawTexture(matrixStack, x - 110 + 10 * i, y - 30, 0, 0, 5, x23, 5, 42, 35);



        }



            fl = (int) (stamina-(maximumsta-5));
            x23=32+2*fl;
            if(fl>5)
            {
                fl=5;
            }
        if(fl<0)
        {
            fl=0;
        }
        if(x23>42)
        {
            x23=42;
        }

            //client.player.sendMessage(Text.of(Integer.toString(number_of_bars)),false);
            if (number_of_bars == 1) {
                RenderSystem.setShaderTexture(0, Stamina_StartFull);
                DrawableHelper.drawTexture(matrixStack, y22 + 10, y - 30, 0, 0, 10, x23-7, 5, 42, 35);
            } else if (number_of_bars == 2) {
                RenderSystem.setShaderTexture(0, Stamina_StartFull);
                DrawableHelper.drawTexture(matrixStack, y22 + 10, y - 30, 0, 0, 15, x23-5, 5, 42, 35);
            } else if (number_of_bars == 3) {
                RenderSystem.setShaderTexture(0, Stamina_StartFull);
                DrawableHelper.drawTexture(matrixStack, y22 + 10, y - 30, 0, 0, 20, x23-4, 5, 42, 35);
            } else if (number_of_bars == 4) {
                RenderSystem.setShaderTexture(0, Stamina_StartFull);
                DrawableHelper.drawTexture(matrixStack, y22 + 10, y - 30, 0, 0, 25, x23-2, 5, 42, 35);
            } else  {
                RenderSystem.setShaderTexture(0, Stamina_StartFull);
                DrawableHelper.drawTexture(matrixStack, y22 + 10, y - 30, 0, 0, 30, x23, 5, 42, 35);
            }









    /*
        for(int i=1;i<=numbers_of_bar_befor-2;i++)
        {
            y22=x-90+8*i;
            RenderSystem.setShaderTexture(0, getStaminaf(1));
            DrawableHelper.drawTexture(matrixStack,y22,y-30,0,0,12,8,3,8,3);
            list.add(1);
        }


        if(number_of_bars==1)
        {
            RenderSystem.setShaderTexture(0, getStaminaf(2));
            DrawableHelper.drawTexture(matrixStack,y22+8,y-30,0,0,12,8,3,8,3);
            list.add(2);
        }
        else if(number_of_bars==2)
        {
            RenderSystem.setShaderTexture(0, getStaminaf(3));
            DrawableHelper.drawTexture(matrixStack,y22+8,y-30,0,0,12,8,3,8,3);
            list.add(3);
        }
        else if(number_of_bars==3){
            RenderSystem.setShaderTexture(0, getStaminaf(4));
            DrawableHelper.drawTexture(matrixStack,y22+8,y-30,0,0,12,8,3,8,3);
            list.add(4);
        }
        else if(number_of_bars==4)
        {
            RenderSystem.setShaderTexture(0, getStaminaf(5));
            DrawableHelper.drawTexture(matrixStack,y22+8,y-30,0,0,12,8,3,8,3);
            list.add(5);
        }
        else
        {
            RenderSystem.setShaderTexture(0, getStaminaf(6));
            DrawableHelper.drawTexture(matrixStack,y22+8,y-30,0,0,12,8,3,8,3);
            list.add(6);
        }

*/
    }




}



