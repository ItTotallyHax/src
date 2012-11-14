package net.axiom.ui;

import net.axiom.AxiomSetup;
import net.minecraft.src.Gui;

public class AxiomOption extends Gui
{
    public int buttonID;
    public boolean state;
    public AxiomWindow parentWindow;
    public String buttonText;

    public AxiomOption(int var1, boolean var2, String var3, AxiomWindow var4)
    {
        this.buttonID = var1;
        this.state = var2;
        this.buttonText = var3;
        this.parentWindow = var4;
    }

    public void drawButton()
    {
        CustomFontRenderer var1 = AxiomSetup.customFont;
        var1.drawStringS(this, this.buttonText, 4 + this.parentWindow.dragX, 18 + this.buttonID * 10 + this.parentWindow.dragY, 16777215);
        int var2 = this.state ? -16724737 : -10592674;
        int var3 = this.state ? -16755201 : -12566464;
    }

    public void toggle()
    {
        this.state = !this.state;
    }
}
