package net.axiom.gui.screens;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.axiom.AxiomHook;
import net.axiom.AxiomSetup;
import net.axiom.util.GuiUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.src.ChatClickData;
import net.minecraft.src.ChatLine;
import net.minecraft.src.GuiChat;
import net.minecraft.src.GuiNewChat;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.StringTranslate;
import net.minecraft.src.StringUtils;
import org.lwjgl.opengl.GL11;

public class AxiomGuiChat extends GuiNewChat
{
    private final Minecraft mc;
    private final List field_73770_b = new ArrayList();
    private final List ChatLines = new ArrayList();
    private int field_73768_d = 0;
    private boolean field_73769_e = false;

    public AxiomGuiChat(Minecraft var1)
    {
        super(var1);
        this.mc = var1;

        for (int var2 = 0; var2 < 8; ++var2)
        {
            this.ChatLines.add(new ChatLine(0, " ", -1));
        }
    }

    public void func_73762_a(int var1)
    {
        if (this.mc.gameSettings.chatVisibility != 2)
        {
            byte var2 = 10;
            boolean var3 = false;
            int var4 = 0;
            int var5 = this.ChatLines.size();
            float var6 = this.mc.gameSettings.chatOpacity * 0.9F + 0.1F;

            if (var5 > 0)
            {
                if (this.func_73760_d())
                {
                    var2 = 20;
                    var3 = true;
                }

                int var10 = 0;
                int var7;
                int var8;
                int var9;
                ChatLine var11;
                double var12;
                int var15;
                String var16;

                for (var7 = 0; var7 + this.field_73768_d < this.ChatLines.size() && var7 < var2; ++var7)
                {
                    var11 = (ChatLine)this.ChatLines.get(var7 + this.field_73768_d);

                    if (var11 != null)
                    {
                        var8 = var1 - var11.getUpdatedCounter();

                        if (var8 < 200 || var3)
                        {
                            var12 = (double)var8 / 200.0D;
                            var12 = 1.0D - var12;
                            var12 *= 10.0D;

                            if (var12 < 0.0D)
                            {
                                var12 = 0.0D;
                            }

                            if (var12 > 1.0D)
                            {
                                var12 = 1.0D;
                            }

                            var12 *= var12;
                            var9 = (int)(255.0D * var12);

                            if (var3)
                            {
                                var9 = 255;
                            }

                            var9 = (int)((float)var9 * var6);
                            ++var4;

                            if (var9 > 3)
                            {
                                boolean var14 = true;
                                var15 = -var7 * 9;
                                var10 += 9;
                                GL11.glEnable(GL11.GL_BLEND);
                                var16 = var11.getChatLineString();

                                if (!this.mc.gameSettings.chatColours)
                                {
                                    var16 = StringUtils.stripControlCodes(var16);
                                }
                            }
                        }
                    }
                }

                if (var10 > 0)
                {
                    if (var3)
                    {
                        GuiUtil.drawBorderedRect(3, -var7 * 9 - 9, 327, -var7 * 9 + 4, 1, -15658735, -2146365167);
                        AxiomSetup.customFont.drawStringS(this, "\u00a73[Axiom]:\u00a7f Chat", 3, -var7 * 9 - 6, -1);
                        GuiUtil.drawBorderedRect(3, -var7 * 9 + 5, 327, -var7 * 9 + 12 + var10, 1, -15658735, -2146365167);
                    }
                    else
                    {
                        GuiUtil.drawBorderedRect(3, -var7 * 9 + 108 - 10 + 2, 327, -var7 * 9 + 108 - 12 + -var10, 1, -15658735, -2146365167);
                    }
                }

                for (var7 = 0; var7 + this.field_73768_d < this.ChatLines.size() && var7 < var2; ++var7)
                {
                    var11 = (ChatLine)this.ChatLines.get(var7 + this.field_73768_d);

                    if (var11 != null)
                    {
                        var8 = var1 - var11.getUpdatedCounter();

                        if (var8 < 200 || var3)
                        {
                            var12 = (double)var8 / 200.0D;
                            var12 = 1.0D - var12;
                            var12 *= 10.0D;

                            if (var12 < 0.0D)
                            {
                                var12 = 0.0D;
                            }

                            if (var12 > 1.0D)
                            {
                                var12 = 1.0D;
                            }

                            var12 *= var12;
                            var9 = (int)(255.0D * var12);

                            if (var3)
                            {
                                var9 = 255;
                            }

                            var9 = (int)((float)var9 * var6);
                            ++var4;

                            if (var9 > 3)
                            {
                                byte var18 = 3;
                                var15 = -var7 * 9;
                                var10 += 9;
                                GL11.glEnable(GL11.GL_BLEND);
                                var16 = var11.getChatLineString();

                                if (!this.mc.gameSettings.chatColours)
                                {
                                    var16 = StringUtils.stripControlCodes(var16);
                                }

                                var16 = AxiomHook.chatLineRenderHook(var16);
                                AxiomSetup.customFont.drawStringS(this, var16, var18, var15, 16777215 + (var9 << 24));
                            }
                        }
                    }
                }

                if (var3)
                {
                    var7 = this.mc.fontRenderer.FONT_HEIGHT;
                    GL11.glTranslatef(0.0F, (float)var7, 0.0F);
                    int var17 = var5 * var7 + var5;
                    var8 = var4 * var7 + var4;
                    int var20 = this.field_73768_d * var8 / var5;
                    int var13 = var8 * var8 / var17;

                    if (var17 != var8)
                    {
                        var9 = var20 > 0 ? 170 : 96;
                        int var19 = this.field_73769_e ? 13382451 : 3355562;
                        drawRect(0, -var20, 2, -var20 - var13, var19 + (var9 << 24));
                        drawRect(2, -var20, 1, -var20 - var13, 13421772 + (var9 << 24));
                    }
                }
            }
        }
    }

    public void func_73761_a()
    {
        this.ChatLines.clear();
        this.field_73770_b.clear();
    }

    /**
     * takes a String and prints it to chat
     */
    public void printChatMessage(String var1)
    {
        this.printChatMessageWithOptionalDeletion(var1, 0);
    }

    /**
     * prints the String to Chat. If the ID is not 0, deletes an existing Chat Line of that ID from the GUI
     */
    public void printChatMessageWithOptionalDeletion(String var1, int var2)
    {
        boolean var3 = this.func_73760_d();
        boolean var4 = true;

        if (var2 != 0)
        {
            this.deleteChatLine(var2);
        }

        Iterator var5 = AxiomSetup.customFont.listFormattedStringToWidth(var1, 300).iterator();

        while (var5.hasNext())
        {
            String var6 = (String)var5.next();

            if (var3 && this.field_73768_d > 0)
            {
                this.field_73769_e = true;
                this.func_73758_b(1);
            }

            if (!var4)
            {
                var6 = " " + var6;
            }

            var4 = false;
            this.ChatLines.add(0, new ChatLine(this.mc.ingameGUI.getUpdateCounter(), var6, var2));
        }

        while (this.ChatLines.size() > 100)
        {
            this.ChatLines.remove(this.ChatLines.size() - 1);
        }
    }

    public List func_73756_b()
    {
        return this.field_73770_b;
    }

    public void func_73767_b(String var1)
    {
        if (this.field_73770_b.isEmpty() || !((String)this.field_73770_b.get(this.field_73770_b.size() - 1)).equals(var1))
        {
            this.field_73770_b.add(var1);
        }
    }

    public void func_73764_c()
    {
        this.field_73768_d = 0;
        this.field_73769_e = false;
    }

    public void func_73758_b(int var1)
    {
        this.field_73768_d += var1;
        int var2 = this.ChatLines.size();

        if (this.field_73768_d > var2 - 20)
        {
            this.field_73768_d = var2 - 20;
        }

        if (this.field_73768_d <= 0)
        {
            this.field_73768_d = 0;
            this.field_73769_e = false;
        }
    }

    public ChatClickData func_73766_a(int var1, int var2)
    {
        if (!this.func_73760_d())
        {
            return null;
        }
        else
        {
            ScaledResolution var3 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
            int var4 = var3.getScaleFactor();
            int var5 = var1 / var4 - 3;
            int var6 = var2 / var4 - 40;

            if (var5 >= 0 && var6 >= 0)
            {
                int var7 = Math.min(20, this.ChatLines.size());

                if (var5 <= 320 && var6 < this.mc.fontRenderer.FONT_HEIGHT * var7 + var7)
                {
                    int var8 = var6 / (this.mc.fontRenderer.FONT_HEIGHT + 1) + this.field_73768_d;
                    return new ChatClickData(this.mc.fontRenderer, (ChatLine)this.ChatLines.get(var8), var5, var6 - (var8 - this.field_73768_d) * this.mc.fontRenderer.FONT_HEIGHT + var8);
                }
                else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
    }

    public void func_73757_a(String var1, Object ... var2)
    {
        this.printChatMessage(StringTranslate.getInstance().translateKeyFormat(var1, var2));
    }

    public boolean func_73760_d()
    {
        return this.mc.currentScreen instanceof GuiChat;
    }

    /**
     * finds and deletes a Chat line by ID
     */
    public void deleteChatLine(int var1)
    {
        Iterator var2 = this.ChatLines.iterator();

        while (var2.hasNext())
        {
            ChatLine var3 = (ChatLine)var2.next();

            if (var3.getChatLineID() == var1)
            {
                var2.remove();
                return;
            }
        }
    }
}
