package net.axiom.modules;

import net.axiom.AxiomWrapper;
import net.minecraft.src.Packet19EntityAction;

public class AxiomModuleRevive extends AxiomModule
{
    public AxiomModuleRevive()
    {
        super("Revive", 16777215, true);
    }

    public void onPlayerDeath()
    {
        AxiomWrapper.mcObj.getSendQueue().addToSendQueue(new Packet19EntityAction(AxiomWrapper.getPlayer(), 3));
    }
}
