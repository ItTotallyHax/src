package net.axiom.modules;

import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Packet13PlayerLookMove;

public class AxiomModulePhase extends AxiomModule
{
    public long currentms = 0L;
    public long threshold = 1000L;
    public long lastClip = 0L;

    public AxiomModulePhase()
    {
        super("Phase", 7704386, "Z", false);
    }

    public boolean onRenderHandItem()
    {
        return false;
    }

    public boolean onOpaqueCheck(EntityPlayer var1)
    {
        return false;
    }

    public void onEnable()
    {
        AxiomWrapper.mcObj.thePlayer.noClip = true;
        AxiomWrapper.mcObj.thePlayer.capabilities.isFlying = true;
    }

    public void onPlayerUpdate()
    {
        AxiomWrapper.mcObj.thePlayer.noClip = true;
        AxiomWrapper.mcObj.thePlayer.capabilities.isFlying = true;
        this.currentms = System.nanoTime() / 1000000L;
        boolean var1 = this.currentms - this.lastClip > this.threshold;

        if (var1)
        {
            this.lastClip = System.nanoTime() / 1000000L;
            double var2 = AxiomWrapper.mcObj.thePlayer.boundingBox.minY - 0.001D;
            float var4 = AxiomWrapper.mcObj.thePlayer.rotationPitch;
            float var5 = AxiomWrapper.mcObj.thePlayer.rotationYaw;
            AxiomHelper.addPacket(new Packet13PlayerLookMove(AxiomWrapper.mcObj.thePlayer.posX, var2, AxiomWrapper.mcObj.thePlayer.posY, AxiomWrapper.mcObj.thePlayer.posZ, 1.0F, 1.0F, false));
        }
    }

    public void onDisable()
    {
        AxiomWrapper.mcObj.thePlayer.noClip = false;
        AxiomWrapper.mcObj.thePlayer.capabilities.isFlying = false;
    }
}
