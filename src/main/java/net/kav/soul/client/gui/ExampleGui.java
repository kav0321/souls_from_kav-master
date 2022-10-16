package net.kav.soul.client.gui;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.data.VerticalAlignment;
import io.github.cottonmc.cotton.gui.widget.icon.ItemIcon;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kav.soul.Soul;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static io.github.cottonmc.cotton.gui.client.BackgroundPainter.createLightDarkVariants;
import static io.github.cottonmc.cotton.gui.client.BackgroundPainter.createNinePatch;
import static net.kav.soul.networking.ModMessages.COMMAND;

public class ExampleGui extends LightweightGuiDescription{
    private static int number_of = 0;
    private static String item = null;
    private static String send = null;
    private static final Identifier Soul_kav2= new Identifier(Soul.MOD_ID,"textures/gui/panel_dark.png");
    private static final Identifier Soul_kav22= new Identifier(Soul.MOD_ID,"textures/gui/back.png");
    private static final Identifier Soul_kav21= new Identifier(Soul.MOD_ID,"textures/gui/small_back.png");

    WGridPanel root = (WGridPanel) rootPanel;
    BackgroundPainter SOUL= createLightDarkVariants((createNinePatch(Soul_kav2)), createNinePatch(Soul_kav2));
   // @Override
    //    public void addPainters()
    //    {
    //      if (this.root != null && !this.fullscreen) {
    //           this.root.setBackgroundPainter(SOUL);
    //       }
    //    }

    public ExampleGui(){

        //WGridPanel root = (WGridPanel) rootPanel;
        setRootPanel(root);
        root.setSize(330, 270);
        root.setInsets(Insets.ROOT_PANEL);

        WBox box= new WBox(Axis.VERTICAL);
       // WButton nw= new WButton();
        WSprite wSprite= new WSprite(Soul_kav22);
        WSprite wSprite1=new WSprite(Soul_kav22);
        root.add(wSprite,0,4,7,10);
        root.add(wSprite1,9,7,10,7);
        Wbuy buttonbuy= new Wbuy(Text.of("Buy"));

        WButtonUp ne = new WButtonUp();
        WButtonDown ke= new WButtonDown();
        root.add(ne,9,12);
        root.add(ke,9,13);
        root.add(buttonbuy,18,13);

        //root.add(nw,1,4);


        WDynamicLabel label = new WDynamicLabel(() ->"Souls Require: "+ Integer.toString(number_of));
        root.add(label,10,13);
        WButton button1= new WButton();
        WButton button2= new WButton(new ItemIcon(Items.APPLE));
        WButton button3= new WButton();
        WButton button4= new WButton();
        WButton button5= new WButton();
        WButton button6= new WButton();
        WButton button7= new WButton();
        WButton button8= new WButton();
        WButton button9= new WButton();
        WButton button10= new WButton();
        WButton button11= new WButton();
        WButton button12= new WButton();
        WButton button13= new WButton();
        WButton button14= new WButton();
        WButton button15= new WButton();
        WButton button16= new WButton();
        WButton button17= new WButton();
        WButton button18= new WButton();
        WButton button19= new WButton();
        WButton button20= new WButton();



        box.add(button1,20,20);
        box.add(button2,20,20);

        button1.setOnClick(() -> {
            item_name();


            // This code runs on the client when you click the button.
            //System.out.println("Button clicked!");
        });


        
        
        ne.setOnClick(() -> {

            count_increase();


            // This code runs on the client when you click the button.
            //System.out.println("Button clicked!");
        });

        ke.setOnClick(() -> {

            count_down();


            // This code runs on the client when you click the button.
            //System.out.println("Button clicked!");
        });



        buttonbuy.setOnClick(() -> {

            send_signal();
            PacketByteBuf buf= PacketByteBufs.create();
            buf= buf.writeString(send);

            ClientPlayNetworking.send(COMMAND,buf);
            clearcount();
            // This code runs on the client when you click the button.
            //System.out.println("Button clicked!");

        });

        root.add(new WLabel(Text.of("Scrolling test")).setVerticalAlignment(VerticalAlignment.CENTER), 0, 0, 5, 2);
        root.add(new WScrollPanel(box), 0, 4, 7, 10);
        root.validate(this);

    }

    protected  void drawBackground(MatrixStack mactrixStack,float delta, int mouseX, int mouseY)
    {

    }

    public static void count_increase()
    {
        if(number_of>99)
        {
            number_of=1;
        }
        number_of+=1;
    }

    public static void count_down()
    {
        if(number_of<0)
        {
            number_of=99;
        }
        number_of-=1;

    }

    public static void clearcount()
    {
        number_of=0;
    }

    public static void item_name()//string name
    {
        item="minecraft:diamond";
    }

    public static void send_signal()
    {
        String space= " ";
        String numbers= Integer.toString(number_of);
        send=item+space+numbers;
    }






}

