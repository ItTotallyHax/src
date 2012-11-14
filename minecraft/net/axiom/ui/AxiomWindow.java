package net.axiom.ui;

import java.util.ArrayList;
import java.util.Iterator;
import net.axiom.AxiomSetup;
import net.axiom.util.GuiUtil;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.Gui;

public class AxiomWindow extends Gui
{
    public FontRenderer fontrenderer;
    public ArrayList buttons = new ArrayList();
    public boolean maxed = false;
    public boolean drag = false;
    public int dragX;
    public int dragY;
    public int dragPointX;
    public int dragPointY;
    public String windowTitle;

    public AxiomWindow(int var1, int var2, String var3)
    {
        this.dragX = var1;
        this.dragY = var2;
        this.windowTitle = var3;
    }

    public AxiomOption getButtonByID(int var1)
    {
        Iterator var2 = this.buttons.iterator();
        AxiomOption var3;

        do
        {
            if (!var2.hasNext())
            {
                return null;
            }

            var3 = (AxiomOption)var2.next();
        }
        while (var3.buttonID != var1);

        return var3;
    }

    public void clickButton(int var1)
    {
        Iterator var2 = this.buttons.iterator();

        while (var2.hasNext())
        {
            AxiomOption var3 = (AxiomOption)var2.next();

            if (var3.buttonID == var1)
            {
                var3.toggle();
            }
        }
    }

    public boolean mouseDragged(int var1, int var2)
    {
        if (this.drag)
        {
            this.dragX += var1 - this.dragPointX;
            this.dragY += var2 - this.dragPointY;
            this.dragPointX = var1;
            this.dragPointY = var2;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean clicked(int var1, int var2)
    {
        if (73 + this.dragX < var1 && 78 + this.dragX > var1 && 4 + this.dragY < var2 && 12 + this.dragY > var2)
        {
            this.maxed = !this.maxed;
            return true;
        }
        else if (2 + this.dragX < var1 && 72 + this.dragX > var1 && 2 + this.dragY < var2 && 14 + this.dragY > var2)
        {
            this.drag = true;
            this.dragPointX = var1;
            this.dragPointY = var2;
            return true;
        }
        else if (!this.maxed)
        {
            return false;
        }
        else if (var1 > this.dragX + 53 && var1 < this.dragX + 78)
        {
            int var3 = 17;
            int var4 = 26;

            for (int var5 = 0; var5 < this.buttons.size(); ++var5)
            {
                if (var2 > this.dragY + var3 && var2 < this.dragY + var4)
                {
                    this.buttonClicks(var5);
                }

                var3 += 10;
                var4 += 10;
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public void buttonClicks(int var1)
    {
        this.clickButton(var1);
    }

    public void drawWindow()
    {
        CustomFontRenderer var1 = AxiomSetup.customFont;
        GuiUtil.drawBorderedRect(2 + this.dragX, 2 + this.dragY, 80 + this.dragX, 14 + this.dragY, 1, -12829636, -2142417587);
        var1.drawStringS(this, this.maxed ? "-" : "+", 72 + this.dragX, 5 + this.dragY, 16777215);
        var1.drawStringS(this, this.windowTitle, 4 + this.dragX, 5 + this.dragY, 16777215);

        if (this.maxed)
        {
            GuiUtil.drawBorderedRect(2 + this.dragX, 15 + this.dragY, 80 + this.dragX, this.buttons.size() * 10 + 18 + this.dragY, 1, -12829636, -2142417587);
            Iterator var2 = this.buttons.iterator();

            while (var2.hasNext())
            {
                AxiomOption var3 = (AxiomOption)var2.next();
                var3.drawButton();
            }
        }
    }
}
