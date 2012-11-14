package net.axiom.modules;

import net.axiom.AxiomWrapper;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet19EntityAction;
import net.minecraft.src.Packet34EntityTeleport;

public class AxiomModuleSneak extends AxiomModule
{
    public AxiomModuleSneak()
    {
        super("Sneak", 11202218, "O", false);
    }

    public void sendSneak()
    {
        NetClientHandler var1 = AxiomWrapper.mcObj.getSendQueue();
        var1.addToSendQueue(new Packet19EntityAction(AxiomWrapper.mcObj.thePlayer, 1));
    }

    public void onEntityTeleport(Packet34EntityTeleport var1)
    {
        Entity var2 = AxiomWrapper.mcObj.theWorld.getEntityByID(var1.entityId);

        if (var2 instanceof EntityPlayer)
        {
            this.sendSneak();
        }
    }

    public void onEnable()
    {
        this.sendSneak();
    }

    public void onDisable()
    {
        NetClientHandler var1 = AxiomWrapper.mcObj.getSendQueue();
        var1.addToSendQueue(new Packet19EntityAction(AxiomWrapper.mcObj.thePlayer, 2));
    }

    public boolean onSendQueueAdd(Packet var1)
    {
        if (var1 instanceof Packet19EntityAction)
        {
            Packet19EntityAction var2 = (Packet19EntityAction)var1;

            if (var2.state == 2)
            {
                return false;
            }
        }

        return true;
    }
}
