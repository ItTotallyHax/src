package net.axiom.modules;

import net.axiom.AxiomSetup;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet3Chat;

public class AxiomModuleProt extends AxiomModule
{
    public AxiomModuleProt()
    {
        super("Protection", 16777215, true);
        this.enabled = true;
    }

    public boolean onSendQueueAdd(Packet var1)
    {
        if (var1 instanceof Packet3Chat)
        {
            Packet3Chat var2 = (Packet3Chat)var1;

            if (var2.message.startsWith(AxiomSetup.cmdChar))
            {
                return false;
            }
        }

        return true;
    }
}
