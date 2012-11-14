package net.axiom.gui.components;

import java.util.ArrayList;
import java.util.List;
import net.axiom.AxiomWrapper;
import net.axiom.util.GuiUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Gui;
import org.lwjgl.opengl.GL11;

public abstract class AvidGuiWindow extends Gui
{
    protected Minecraft mc;
    private int doubleClickTimer;
    public String title;
    public boolean enabled;
    public boolean focused;
    public boolean dragging;
    public boolean pinned;
    public int posX;
    public int posY;
    public int pointX;
    public int pointY;
    protected List controlList;
    protected List controlList2;
    protected AvidGuiButton selectedButton;
    protected boolean shouldDrawCtrlList2;

    public AvidGuiWindow(int var1, int var2, String var3)
    {
        this.mc = AxiomWrapper.mcObj;
        this.doubleClickTimer = 0;
        this.pinned = false;
        this.shouldDrawCtrlList2 = true;
        this.posX = var1;
        this.posY = var2;
        this.title = var3;
        this.controlList = new ArrayList();
        this.controlList2 = new ArrayList();
        this.start();
    }

    public void start() {}

    public void drawScreen(int var1, int var2, float var3)
    {
        this.draw();
        int var4;
        AvidGuiButton var5;

        if (this.enabled)
        {
            for (var4 = 0; var4 < this.controlList.size(); ++var4)
            {
                var5 = (AvidGuiButton)this.controlList.get(var4);
                var5.drawButton(this.mc, var1, var2, this.posX, this.posY + 12);
            }
        }

        if (this.enabled && this.shouldDrawCtrlList2)
        {
            for (var4 = 0; var4 < this.controlList2.size(); ++var4)
            {
                var5 = (AvidGuiButton)this.controlList2.get(var4);
                var5.drawButton(this.mc, var1, var2, this.posX, this.posY + 12);
            }
        }
    }

    public void keyTyped(char var1, int var2)
    {
        int var3;
        AvidGuiButton var4;

        for (var3 = 0; var3 < this.controlList.size(); ++var3)
        {
            var4 = (AvidGuiButton)this.controlList.get(var3);
            var4.keyTyped(var1, var2);
        }

        if (this.shouldDrawCtrlList2)
        {
            for (var3 = 0; var3 < this.controlList2.size(); ++var3)
            {
                var4 = (AvidGuiButton)this.controlList2.get(var3);
                var4.keyTyped(var1, var2);
            }
        }
    }

    public void draw()
    {
        GL11.glTranslatef((float)this.posX, (float)this.posY, 0.0F);
        this.drawHeader();

        if (this.enabled)
        {
            GL11.glTranslatef(0.0F, 14.35F, 0.0F);
            this.drawPanel();
            GL11.glTranslatef(0.0F, -14.35F, 0.0F);
        }

        GL11.glTranslatef((float)(-this.posX), (float)(-this.posY), 0.0F);
    }

    public void drawHeader()
    {
        GuiUtil.drawBorderedRect(0, 0, this.getWidth(), 13, 1, -12829636, -2142417587);
        AxiomWrapper.mcObj.fontRenderer.drawString(this.title, 3, 3, 16777215);
    }

    public void drawPanel()
    {
        GuiUtil.drawBorderedRect(0, 0, this.getWidth(), this.getHeight() - 11, 1, -12829636, -2142417587);
    }

    public boolean mouseClick(int var1, int var2, int var3)
    {
        if (this.checkBounds(var1, var2, this.getWidth() - 12, 1, this.getWidth() - 2, 10))
        {
            this.pinned = !this.pinned;
            this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
            return true;
        }
        else
        {
            if (this.checkBounds(var1, var2, 0, 0, this.getWidth(), 11))
            {
                this.dragging = true;
                this.pointX = var1;
                this.pointY = var2;

                if (this.doubleClickTimer > 0 && this.doubleClickTimer < 40)
                {
                    this.enabled = !this.enabled;
                }
                else
                {
                    this.doubleClickTimer = 8;
                }
            }

            if (this.enabled)
            {
                for (int var4 = 0; var4 < this.controlList.size(); ++var4)
                {
                    AvidGuiButton var5 = (AvidGuiButton)this.controlList.get(var4);

                    if (var5.mousePressed(this.mc, var1, var2, this.posX, this.posY + 12))
                    {
                        this.selectedButton = var5;
                        this.actionPerformed(var5);
                        break;
                    }
                }

                if (this.shouldDrawCtrlList2)
                {
                    boolean var7 = false;

                    for (int var8 = 0; var8 < this.controlList2.size(); ++var8)
                    {
                        AvidGuiButton var6 = (AvidGuiButton)this.controlList2.get(var8);

                        if (var6.mousePressed(this.mc, var1, var2, this.posX, this.posY + 12))
                        {
                            if (!var7)
                            {
                                this.selectedButton = var6;
                            }

                            this.actionPerformed(var6);
                            var7 = true;
                        }
                    }
                }
            }

            this.buttonClick(var1, var2, var3);
            return this.checkBounds(var1, var2, 0, 0, this.getWidth(), this.enabled ? this.getHeight() : 11);
        }
    }

    public void updateScreen()
    {
        if (this.doubleClickTimer > 0)
        {
            --this.doubleClickTimer;
        }

        int var1;
        AvidGuiButton var2;

        for (var1 = 0; var1 < this.controlList.size(); ++var1)
        {
            var2 = (AvidGuiButton)this.controlList.get(var1);
            var2.updateScreen();
        }

        if (this.shouldDrawCtrlList2)
        {
            for (var1 = 0; var1 < this.controlList2.size(); ++var1)
            {
                var2 = (AvidGuiButton)this.controlList2.get(var1);
                var2.updateScreen();
            }
        }
    }

    public void buttonClick(int var1, int var2, int var3) {}

    public void mouseMovedOrUp(int var1, int var2, int var3)
    {
        if (this.dragging)
        {
            if (var3 < 0)
            {
                this.posX += var1 - this.pointX;
                this.posY += var2 - this.pointY;
                this.pointX = var1;
                this.pointY = var2;
            }
            else if (var3 == 0)
            {
                this.dragging = false;
            }
        }

        if (this.selectedButton != null && var3 == 0)
        {
            this.selectedButton.mouseReleased(var1, var2);
            this.selectedButton = null;
        }
    }

    protected void actionPerformed(AvidGuiButton var1) {}

    public boolean checkBounds(int var1, int var2, int var3, int var4, int var5, int var6)
    {
        return var1 >= this.posX + var3 && var1 <= this.posX + var5 && var2 >= this.posY + var4 && var2 <= this.posY + var6;
    }

    public abstract int getWidth();

    public abstract int getHeight();
}
