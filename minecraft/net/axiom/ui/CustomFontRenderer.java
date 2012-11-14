package net.axiom.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import net.axiom.AxiomWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Gui;
import net.minecraft.src.StringUtils;
import org.lwjgl.opengl.GL11;

public class CustomFontRenderer
{
    private int texID;
    private int[] xPos;
    private int[] yPos;
    private int startChar;
    private int endChar;
    private final FontMetrics metrics;
    private Graphics graphics;

    public CustomFontRenderer(Minecraft var1, Font var2, int var3)
    {
        this(var1, var2, 32, 126);
    }

    public CustomFontRenderer(Minecraft var1, Font var2, int var3, int var4)
    {
        this.startChar = var3;
        this.endChar = var4;
        this.xPos = new int[var4 - var3];
        this.yPos = new int[var4 - var3];
        BufferedImage var5 = new BufferedImage(256, 256, 2);
        Graphics2D var6 = (Graphics2D)var5.getGraphics();
        var6.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        var6.setFont(var2);
        int var7 = var2.getSize();
        var6.setColor(new Color(255, 255, 255, 0));
        var6.fillRect(0, 0, 256, 256);
        var6.setColor(Color.white);
        this.metrics = var6.getFontMetrics(var2);
        int var8 = 2;
        int var9 = 2;

        for (int var10 = var3; var10 < var4; ++var10)
        {
            var6.drawString(String.valueOf((char)var10), var8, var9 + this.metrics.getAscent());
            this.xPos[var10 - var3] = var8;
            this.yPos[var10 - var3] = var9 - this.metrics.getMaxDescent() + 2;
            var8 += this.metrics.stringWidth("" + (char)var10) + 2;

            if (var8 >= 250 - this.metrics.getMaxAdvance())
            {
                var8 = 2;
                var9 += this.metrics.getMaxAscent() + this.metrics.getMaxDescent() + var7 / 2;
            }
        }

        try
        {
            this.texID = var1.renderEngine.allocateAndSetupTexture(var5);
        }
        catch (NullPointerException var11)
        {
            var11.printStackTrace();
        }

        this.graphics = var6;
    }

    public final void renderString(Gui var1, String var2, int var3, int var4, int var5)
    {
        if (var2 != null)
        {
            var3 *= 4;
            var4 = var4 * 2 - 7;
            GL11.glPushMatrix();
            GL11.glScaled(0.5D, 0.5D, 0.5D);
            this.drawStringWOC(var1, var2, var3 + 1, var4 + 1, var5);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.texID);
            float var6 = (float)(var5 >> 16 & 255) / 255.0F;
            float var7 = (float)(var5 >> 8 & 255) / 255.0F;
            float var8 = (float)(var5 & 255) / 255.0F;
            float var9 = (float)(var5 >> 24 & 255) / 255.0F;
            GL11.glColor4f(var6, var7, var8, var9);
            int var10 = var3;
            int var11 = var2.length();

            for (int var12 = 0; var12 < var11; ++var12)
            {
                char var13 = var2.charAt(var12);

                if (var13 == 167 && var12 + 1 < var2.length())
                {
                    char var14 = var2.charAt(var12 + 1);

                    if (var14 == 110)
                    {
                        var4 += this.metrics.getAscent() + 2;
                        var3 = var10;
                    }

                    int var15 = "0123456789abcdefklmnor".indexOf(var2.toLowerCase().charAt(var12 + 1));

                    if (var15 < 16)
                    {
                        try
                        {
                            int var16 = AxiomWrapper.mcObj.fontRenderer.colorCode[var15];
                            GL11.glColor3f((float)(var16 >> 16) / 255.0F, (float)(var16 >> 8 & 255) / 255.0F, (float)(var16 & 255) / 255.0F);
                        }
                        catch (Exception var17)
                        {
                            var17.printStackTrace();
                        }
                    }

                    ++var12;
                }
                else
                {
                    try
                    {
                        this.drawChar(var1, var13, var3, var4);
                        var3 = (int)((double)var3 + this.metrics.getStringBounds(Character.toString(var13), (Graphics)null).getWidth());
                    }
                    catch (ArrayIndexOutOfBoundsException var18)
                    {
                        ;
                    }
                }
            }

            GL11.glScaled(2.0D, 2.0D, 2.0D);
            GL11.glPopMatrix();
        }
    }

    public void drawStringS(Gui var1, String var2, int var3, int var4, int var5)
    {
        String var6 = StringUtils.stripControlCodes(var2);
        int var7 = var5 & 0;
        int var8 = (var5 & 0) >> 2;
        int var10000 = var8 + var7;
        GL11.glPushMatrix();
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        this.renderString(var1, var2, var3, var4, var5);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
    }

    public final void drawStringWOC(Gui var1, String var2, int var3, int var4, int var5)
    {
        if (var2 != null)
        {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.texID);
            float var6 = (float)(var5 >> 24 & 255) / 255.0F;
            GL11.glColor4f(0.0F, 0.0F, 0.0F, var6);
            int var7 = var3;
            int var8 = var2.length();

            for (int var9 = 0; var9 < var8; ++var9)
            {
                char var10 = var2.charAt(var9);

                if (var10 == 167 && var9 + 1 < var2.length())
                {
                    if (var2.charAt(var9 + 1) == 110)
                    {
                        var4 += this.metrics.getAscent() + 2;
                        var3 = var7;
                    }

                    ++var9;
                }
                else
                {
                    try
                    {
                        this.drawChar(var1, var10, var3, var4);
                        var3 = (int)((double)var3 + this.metrics.getStringBounds(Character.toString(var10), (Graphics)null).getWidth());
                    }
                    catch (ArrayIndexOutOfBoundsException var12)
                    {
                        ;
                    }
                }
            }
        }
    }

    public FontMetrics getMetrics()
    {
        return this.metrics;
    }

    public int getStringWidth(String var1)
    {
        return (int)this.getBounds(var1).getWidth() / 2;
    }

    public int getCharWidth(char var1)
    {
        return (int)this.getBounds(Character.toString(var1)).getWidth() / 2;
    }

    public int getStringHeight(String var1)
    {
        return (int)this.getBounds(var1).getHeight() / 2;
    }

    private final Rectangle getBounds(String var1)
    {
        if (var1 == null)
        {
            return new Rectangle(0, 0, 0, 0);
        }
        else
        {
            int var2 = 0;
            int var3 = 0;
            int var4 = 0;
            int var5 = var1.length();

            for (int var6 = 0; var6 < var5; ++var6)
            {
                char var7 = var1.charAt(var6);

                if (var7 == 92)
                {
                    char var8;

                    try
                    {
                        var8 = var1.charAt(var6 + 1);
                    }
                    catch (StringIndexOutOfBoundsException var10)
                    {
                        break;
                    }

                    if (var8 == 110)
                    {
                        var3 += this.metrics.getAscent() + 2;

                        if (var4 > var2)
                        {
                            var2 = var4;
                        }

                        var4 = 0;
                    }

                    ++var6;
                }
                else
                {
                    var4 += this.metrics.stringWidth(Character.toString(var7));
                }
            }

            if (var4 > var2)
            {
                var2 = var4;
            }

            var3 += this.metrics.getAscent();
            return new Rectangle(0, 0, var2, var3);
        }
    }

    private final void drawChar(Gui var1, char var2, int var3, int var4) throws ArrayIndexOutOfBoundsException
    {
        Rectangle2D var5 = this.metrics.getStringBounds(Character.toString(var2), this.graphics);
        var1.drawTexturedModalRect(var3, var4, this.xPos[(byte)var2 - this.startChar], this.yPos[(byte)var2 - this.startChar], (int)var5.getWidth(), (int)var5.getHeight() + this.metrics.getMaxDescent());
    }

    public List listFormattedStringToWidth(String var1, int var2)
    {
        return Arrays.asList(this.wrapFormattedStringToWidth(var1, var2).split("\n"));
    }

    String wrapFormattedStringToWidth(String var1, int var2)
    {
        int var3 = this.sizeStringToWidth(var1, var2);

        if (var1.length() <= var3)
        {
            return var1;
        }
        else
        {
            String var4 = var1.substring(0, var3);
            String var5 = getFormatFromString(var4) + var1.substring(var3 + (var1.charAt(var3) == 32 ? 1 : 0));
            return var4 + "\n" + this.wrapFormattedStringToWidth(var5, var2);
        }
    }

    private int sizeStringToWidth(String var1, int var2)
    {
        int var3 = var1.length();
        int var4 = 0;
        int var5 = 0;
        int var6 = -1;

        for (boolean var7 = false; var5 < var3; ++var5)
        {
            char var8 = var1.charAt(var5);

            switch (var8)
            {
                case 32:
                    var6 = var5;

                case 167:
                    if (var5 < var3 - 1)
                    {
                        ++var5;
                        char var9 = var1.charAt(var5);

                        if (var9 != 108 && var9 != 76)
                        {
                            if (var9 == 114 || var9 == 82)
                            {
                                var7 = false;
                            }
                        }
                        else
                        {
                            var7 = true;
                        }
                    }

                    break;

                default:
                    var4 += this.getCharWidth(var8);

                    if (var7)
                    {
                        ++var4;
                    }
            }

            if (var8 == 10)
            {
                ++var5;
                var6 = var5;
                break;
            }

            if (var4 > var2)
            {
                break;
            }
        }

        return var5 != var3 && var6 != -1 && var6 < var5 ? var6 : var5;
    }

    private static String getFormatFromString(String var0)
    {
        String var1 = "";
        int var2 = -1;
        int var3 = var0.length();

        while ((var2 = var0.indexOf(167, var2 + 1)) != -1)
        {
            if (var2 < var3 - 1)
            {
                char var4 = var0.charAt(var2 + 1);

                if (isFormatColor(var4))
                {
                    var1 = "\u00a7" + var4;
                }
                else if (isFormatSpecial(var4))
                {
                    var1 = var1 + "\u00a7" + var4;
                }
            }
        }

        return var1;
    }

    private static boolean isFormatColor(char var0)
    {
        return var0 >= 48 && var0 <= 57 || var0 >= 97 && var0 <= 102 || var0 >= 65 && var0 <= 70;
    }

    private static boolean isFormatSpecial(char var0)
    {
        return var0 >= 107 && var0 <= 111 || var0 >= 75 && var0 <= 79 || var0 == 114 || var0 == 82;
    }
}
