package net.axiom.gui.windows;

import java.util.List;
import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.axiom.gui.components.AvidGuiButton;
import net.axiom.gui.components.AvidGuiWindow;
import net.axiom.modules.AxiomModuleTracers;

public class AxiomWindowTracer extends AvidGuiWindow
{
    private AxiomModuleTracers Tracers;

    public AxiomWindowTracer(int var1, int var2)
    {
        super(var1, var2, "Tracers");
    }

    protected void actionPerformed(AvidGuiButton var1)
    {
        if (this.Tracers == null)
        {
            this.Tracers = (AxiomModuleTracers)AxiomHelper.getModByName("Tracers");
        }

        int var2 = var1.id;

        switch (var2)
        {
            case 0:
                this.Tracers.players = !this.Tracers.players;
                break;

            case 1:
                this.Tracers.mobs = !this.Tracers.mobs;
                break;

            case 2:
                this.Tracers.protect = !this.Tracers.protect;
                break;

            case 3:
                this.Tracers.points = !this.Tracers.points;
        }

        this.start();
    }

    public void start()
    {
        this.controlList.clear();
        this.controlList2.clear();

        if (this.Tracers == null)
        {
            this.Tracers = (AxiomModuleTracers)AxiomHelper.getModByName("Tracers");
        }

        byte var1 = 0;
        List var10000 = this.controlList;
        AvidGuiButton var10001 = new AvidGuiButton;
        int var2 = var1 + 6;
        var10001.<init>(0, 4, var2, this.Tracers.players);
        var10000.add(var10001);
        var10000 = this.controlList;
        var10001 = new AvidGuiButton;
        var2 += 14;
        var10001.<init>(1, 4, var2, this.Tracers.mobs);
        var10000.add(var10001);
        var10000 = this.controlList;
        var10001 = new AvidGuiButton;
        var2 += 14;
        var10001.<init>(2, 4, var2, this.Tracers.protect);
        var10000.add(var10001);
        var10000 = this.controlList;
        var10001 = new AvidGuiButton;
        var2 += 14;
        var10001.<init>(3, 4, var2, this.Tracers.points);
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
        AxiomWrapper.mcObj.fontRenderer.drawString("Players", 52, var2, this.getColor(this.Tracers.players));
        var2 += 14;
        AxiomWrapper.mcObj.fontRenderer.drawString("Mobs", 52, var2, this.getColor(this.Tracers.mobs));
        var2 += 14;
        AxiomWrapper.mcObj.fontRenderer.drawString("Protect", 52, var2, this.getColor(this.Tracers.protect));
        var2 += 14;
        AxiomWrapper.mcObj.fontRenderer.drawString("Waypoints", 52, var2, this.getColor(this.Tracers.points));
    }

    public int getWidth()
    {
        return 104;
    }

    public int getHeight()
    {
        return 72;
    }
}
