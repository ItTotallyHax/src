package net.axiom.modules;

import net.axiom.AxiomWrapper;

public class AxiomModuleSprint extends AxiomModule
{
    public AxiomModuleSprint()
    {
        super("Sprint", 15400704, "M", false);
    }

    public void onPlayerUpdate()
    {
        AxiomWrapper.mcObj.thePlayer.setSprinting(true);
    }
}
