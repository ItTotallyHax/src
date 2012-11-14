package net.axiom.gui.components;

import net.minecraft.client.Minecraft;
import net.minecraft.src.FontRenderer;
import org.lwjgl.opengl.GL11;

public class AvidComponent extends AvidGuiButton
{
    protected int width;
    protected int height;
    public int xPosition;
    public int yPosition;
    public String displayString;
    public int id;
    public boolean enabled;
    public boolean drawButton;
    static boolean nigguh;

    public AvidComponent(int var1, int var2, int var3, String var4)
    {
        this(var1, var2, var3, 70, 26, var4);
    }

    public AvidComponent(int var1, int var2, int var3, int var4, int var5, String var6)
    {
        super(var1, var2, var3, var4, var5, nigguh);
        this.width = 70;
        this.height = 16;
        this.enabled = true;
        this.drawButton = true;
        this.id = var1;
        this.xPosition = var2;
        this.yPosition = var3;
        this.width = var4;
        this.height = var5;
        this.displayString = var6;
    }

    protected int getHoverState(boolean var1)
    {
        byte var2 = 1;

        if (!this.enabled)
        {
            var2 = 0;
        }
        else if (var1)
        {
            var2 = 2;
        }

        return var2;
    }

    public void drawButton(Minecraft var1, int var2, int var3, int var4, int var5)
    {
        if (this.drawButton)
        {
            FontRenderer var6 = Minecraft.getMinecraft().fontRenderer;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            boolean var7 = var2 >= var4 + this.xPosition && var3 >= var5 + this.yPosition && var2 < var4 + this.xPosition + this.width && var3 < var5 + this.yPosition + this.height;
            this.getHoverState(var7);
            this.mouseDragged(var1, var2, var3, var4, var5);
        }
    }

    protected void mouseDragged(Minecraft var1, int var2, int var3, int var4, int var5) {}

    public void mouseReleased(int var1, int var2) {}

    public boolean mousePressed(Minecraft var1, int var2, int var3, int var4, int var5)
    {
        return var2 >= var4 + this.xPosition && var2 <= var4 + this.xPosition + this.width && var3 >= var5 + this.yPosition && var3 <= var5 + this.yPosition + this.height;
    }
}
