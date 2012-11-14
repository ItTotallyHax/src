package net.axiom.ui;

import net.axiom.AxiomHelper;
import net.axiom.modules.AxiomModuleKillAura;

public class AxiomWindowKillAura extends AxiomWindow
{
    public AxiomModuleKillAura KillAura;

    public AxiomWindowKillAura()
    {
        super(0, 26, "Kill Aura");
        this.buttons.add(new AxiomOption(0, true, "Players", this));
        this.buttons.add(new AxiomOption(1, true, "Passive", this));
        this.buttons.add(new AxiomOption(2, true, "Neutral", this));
        this.buttons.add(new AxiomOption(3, true, "Aggro", this));
        this.buttons.add(new AxiomOption(4, true, "Vehicles", this));
    }

    public void buttonClicks(int var1)
    {
        super.buttonClicks(var1);
        this.KillAura = (AxiomModuleKillAura)AxiomHelper.getModByName("Kill Aura");

        switch (var1)
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
                this.KillAura.aggro = !this.KillAura.neutral;
                break;

            case 4:
                this.KillAura.vehicle = !this.KillAura.vehicle;
        }
    }
}
