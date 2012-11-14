package net.axiom.modules;

import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.Packet28EntityVelocity;

public class AxiomModuleMovement extends AxiomModule
{
    public boolean antivelocity = false;
    public boolean antiwater = false;

    public AxiomModuleMovement()
    {
        super("Movement", 16777215, true);
        this.enabled = true;
    }

    public void onPlayerUpdate()
    {
        EntityClientPlayerMP var1 = AxiomWrapper.mcObj.thePlayer;

        if (this.antiwater && var1.isInWater())
        {
            var1.noClip = true;
        }
    }

    public boolean onEntityVelocity(Packet28EntityVelocity var1)
    {
        return var1.entityId == AxiomWrapper.mcObj.thePlayer.entityId ? !this.antivelocity : true;
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        String var4;

        if (var1.equalsIgnoreCase("ave"))
        {
            this.antivelocity = !this.antivelocity;
            var4 = this.antivelocity ? "Now preventing velocity effects." : "Stopped preventing velocity effects.";
            AxiomHelper.chatMsg(var4);
            return true;
        }
        else if (var1.equalsIgnoreCase("awe"))
        {
            this.antiwater = !this.antiwater;
            var4 = this.antiwater ? "Now preventing water effects." : "Stopped preventing water effects.";
            AxiomHelper.chatMsg(var4);
            return true;
        }
        else
        {
            return false;
        }
    }
}
