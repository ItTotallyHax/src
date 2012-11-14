package net.axiom.gui.windows;

import java.util.List;
import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.axiom.gui.components.AvidGuiButton;
import net.axiom.gui.components.AvidGuiWindow;
import net.axiom.modules.AxiomModuleKillAura;
import net.axiom.util.ColorUtil;

public class AxiomWindowAura extends AvidGuiWindow
{
    protected AxiomModuleKillAura KillAura;

    public AxiomWindowAura(int var1, int var2)
    {
        super(var1, var2, "Kill Aura");
    }

    protected void actionPerformed(AvidGuiButton var1)
    {
        if (this.KillAura == null)
        {
            this.KillAura = (AxiomModuleKillAura)AxiomHelper.getModByName("Kill Aura");
        }

        int var2 = var1.id;

        switch (var2)
        {
            case 0:
                this.KillAura.player = !this.KillAura.player;
                break;

            case 1:
                this.KillAura.passive = !this.KillAura.passive;
                break;

            case 2:
                this.KillAura.neutral = !this.KillAura.neutral;
                break;

            case 3:
                this.KillAura.aggro = !this.KillAura.aggro;
                break;

            case 4:
                this.KillAura.vehicle = !this.KillAura.vehicle;
        }

        this.start();
    }

    public void start()
    {
        this.controlList.clear();
        this.controlList2.clear();

        if (this.KillAura == null)
        {
            this.KillAura = (AxiomModuleKillAura)AxiomHelper.getModByName("Kill Aura");
        }

        byte var1 = 0;
        List var10000 = this.controlList;
        AvidGuiButton var10001 = new AvidGuiButton;
        int var2 = var1 + 6;
        var10001.<init>(0, 4, var2, this.KillAura.player);
        var10000.add(var10001);
        var10000 = this.controlList;
        var10001 = new AvidGuiButton;
        var2 += 14;
        var10001.<init>(1, 4, var2, this.KillAura.passive);
        var10000.add(var10001);
        var10000 = this.controlList;
        var10001 = new AvidGuiButton;
        var2 += 14;
        var10001.<init>(2, 4, var2, this.KillAura.neutral);
        var10000.add(var10001);
        var10000 = this.controlList;
        var10001 = new AvidGuiButton;
        var2 += 14;
        var10001.<init>(3, 4, var2, this.KillAura.aggro);
        var10000.add(var10001);
        var10000 = this.controlList;
        var10001 = new AvidGuiButton;
        var2 += 14;
        var10001.<init>(4, 4, var2, this.KillAura.vehicle);
        var10000.add(var10001);
    }

    public void drawPanel()
    {
        super.drawPanel();
        byte var1 = 0;
        int var2 = var1 + 6;
        AxiomWrapper.mcObj.fontRenderer.drawString("Player", 52, var2, ColorUtil.getColor(this.KillAura.player));
        var2 += 14;
        AxiomWrapper.mcObj.fontRenderer.drawString("Passive", 52, var2, ColorUtil.getColor(this.KillAura.passive));
        var2 += 14;
        AxiomWrapper.mcObj.fontRenderer.drawString("Neutral", 52, var2, ColorUtil.getColor(this.KillAura.neutral));
        var2 += 14;
        AxiomWrapper.mcObj.fontRenderer.drawString("Aggro", 52, var2, ColorUtil.getColor(this.KillAura.aggro));
        var2 += 14;
        AxiomWrapper.mcObj.fontRenderer.drawString("Vehicle", 52, var2, ColorUtil.getColor(this.KillAura.vehicle));
    }

    public int getWidth()
    {
        return 100;
    }

    public int getHeight()
    {
        return 86;
    }
}
