package net.axiom.gui.components;

import net.axiom.util.GuiUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.Gui;
import org.lwjgl.opengl.GL11;

public class AvidGuiButton extends Gui
{
    protected int width;
    protected int height;
    public int xPosition;
    public int yPosition;
    public String displayString;
    public int id;
    public boolean enabled;
    public boolean drawButton;
    private boolean enabledisable;
    protected boolean prevState;

    public AvidGuiButton(int var1, int var2, int var3, boolean var4)
    {
        this(var1, var2, var3, 44, 12, var4, var4 ? "Enabled" : "Disabled");
    }

    public AvidGuiButton(int var1, int var2, int var3, int var4, int var5, boolean var6, String var7)
    {
        this(var1, var2, var3, var4, var5, var6);
        this.displayString = var7;
    }

    public AvidGuiButton(int var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        this.displayString = "";
        this.width = 50;
        this.height = 20;
        this.enabled = true;
        this.drawButton = true;
        this.id = var1;
        this.xPosition = var2;
        this.yPosition = var3;
        this.width = var4;
        this.height = var5;
        this.enabledisable = var6;
        this.prevState = var6;
    }

    public void updateScreen() {}

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
            boolean var6 = var2 >= var4 + this.xPosition && var2 <= var4 + this.xPosition + this.width && var3 >= var5 + this.yPosition && var3 <= var5 + this.yPosition + this.height;
            FontRenderer var7 = Minecraft.getMinecraft().fontRenderer;
            int var8 = this.enabledisable ? -16724737 : -10592674;
            int var9 = this.enabledisable ? -16755201 : -12566464;
            GuiUtil.drawAxiomButton(var4 + this.xPosition, var5 + this.yPosition, var4 + this.xPosition + this.width, var5 + this.yPosition + this.height, -10592674, var8, var9);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
            this.drawCenteredString(var7, this.enabledisable ? "Enabled" : "Disabled", (var4 + this.xPosition + this.width / 2) * 2, (var5 + this.yPosition + 4) * 2, var6 ? 16777120 : -1);
            GL11.glScaled(2.0D, 2.0D, 2.0D);
            this.mouseDragged(var1, var2, var3, var4, var5);
        }
    }

    protected void mouseDragged(Minecraft var1, int var2, int var3, int var4, int var5) {}

    public void mouseReleased(int var1, int var2) {}

    public void keyTyped(char var1, int var2) {}

    public boolean mousePressed(Minecraft var1, int var2, int var3, int var4, int var5)
    {
        return var2 >= var4 + this.xPosition && var2 <= var4 + this.xPosition + this.width && var3 >= var5 + this.yPosition && var3 <= var5 + this.yPosition + this.height;
    }
}
