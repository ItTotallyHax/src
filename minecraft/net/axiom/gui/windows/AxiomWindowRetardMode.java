package net.axiom.gui.windows;

import java.util.List;
import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.axiom.gui.components.AvidGuiButton;
import net.axiom.gui.components.AvidGuiWindow;
import net.axiom.modules.AxiomModuleRetard;

public class AxiomWindowRetardMode extends AvidGuiWindow
{
    protected AxiomModuleRetard Retard;

    public AxiomWindowRetardMode(int var1, int var2)
    {
        super(var1, var2, "Retard Mode");
    }

    protected void actionPerformed(AvidGuiButton var1)
    {
        if (this.Retard == null)
        {
            this.Retard = (AxiomModuleRetard)AxiomHelper.getModByName("Retard");
        }

        int var2 = var1.id;

        switch (var2)
        {
            case 0:
                this.Retard.clearChoices();
                this.Retard.derp = !this.Retard.derp;
                break;

            case 1:
                this.Retard.clearChoices();
                this.Retard.headless = !this.Retard.headless;
                break;

            case 2:
                this.Retard.clearChoices();
                this.Retard.backward = !this.Retard.backward;
        }

        this.start();
    }

    public void start()
    {
        this.controlList.clear();
        this.controlList2.clear();

        if (this.Retard == null)
        {
            this.Retard = (AxiomModuleRetard)AxiomHelper.getModByName("Retard");
        }

        byte var1 = 0;
        List var10000 = this.controlList;
        AvidGuiButton var10001 = new AvidGuiButton;
        int var2 = var1 + 6;
        var10001.<init>(0, 4, var2, this.Retard.derp);
        var10000.add(var10001);
        var10000 = this.controlList;
        var10001 = new AvidGuiButton;
        var2 += 14;
        var10001.<init>(1, 4, var2, this.Retard.headless);
        var10000.add(var10001);
        var10000 = this.controlList;
        var10001 = new AvidGuiButton;
        var2 += 14;
        var10001.<init>(2, 4, var2, this.Retard.backward);
        var10000.add(var10001);
    }

    public int getColor(boolean var1)
    {
        int var2 = var1 ? -16724737 : -10592674;
        return var2;
    }

    public void drawPanel()
    {
        super.drawPanel();
        byte var1 = 0;
        int var2 = var1 + 6;
        AxiomWrapper.mcObj.fontRenderer.drawString("Derp", 52, var2, this.getColor(this.Retard.derp));
        var2 += 14;
        AxiomWrapper.mcObj.fontRenderer.drawString("Headless", 52, var2, this.getColor(this.Retard.headless));
        var2 += 14;
        AxiomWrapper.mcObj.fontRenderer.drawString("Backwards", 52, var2, this.getColor(this.Retard.backward));
    }

    public int getWidth()
    {
        return 112;
    }

    public int getHeight()
    {
        return 58;
    }
}
