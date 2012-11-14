package net.axiom.ui;

import net.axiom.AxiomHelper;
import net.axiom.modules.AxiomModuleTracers;

public class AxiomWindowTracers extends AxiomWindow
{
    public AxiomModuleTracers Tracers;

    public AxiomWindowTracers()
    {
        super(0, 40, "Tracers");
        this.buttons.add(new AxiomOption(0, true, "Players", this));
        this.buttons.add(new AxiomOption(1, true, "Mobs", this));
        this.buttons.add(new AxiomOption(2, true, "Protect", this));
        this.buttons.add(new AxiomOption(3, true, "Waypoint", this));
    }

    public void buttonClicks(int var1)
    {
        super.buttonClicks(var1);
        this.Tracers = (AxiomModuleTracers)AxiomHelper.getModByName("Tracers");

        switch (var1)
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
    }
}
