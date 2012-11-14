package net.axiom.modules;

import java.util.Random;
import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.minecraft.src.EntityClientPlayerMP;

public class AxiomModuleRetard extends AxiomModule
{
    public boolean derp = false;
    public boolean headless = false;
    public boolean backward = false;
    private float lastPitch = 0.0F;
    private float lastYaw = 0.0F;
    private float lastYawHead = 0.0F;

    public AxiomModuleRetard()
    {
        super("Retard", 11189196, "K", false);
    }

    public void clearChoices()
    {
        this.derp = false;
        this.headless = false;
        this.backward = false;
    }

    public void preMotionUpdates()
    {
        EntityClientPlayerMP var1 = AxiomWrapper.getPlayer();

        if (this.headless)
        {
            this.lastPitch = var1.rotationPitch;
            AxiomWrapper.getPlayer().rotationPitch = -180.0F;
        }

        if (this.backward)
        {
            this.lastYawHead = var1.rotationYawHead;
            var1.rotationYawHead = 360.0F - var1.rotationYaw;
        }

        if (this.derp)
        {
            this.lastPitch = var1.rotationPitch;
            this.lastYaw = var1.rotationYaw;
            this.lastYawHead = var1.rotationYawHead;
            var1.rotationPitch = (new Random()).nextFloat() * 360.0F;
            var1.rotationYaw = (new Random()).nextFloat() * 360.0F;
            var1.rotationYawHead = (new Random()).nextFloat() * 360.0F;
        }
    }

    public void postMotionUpdates()
    {
        EntityClientPlayerMP var1 = AxiomWrapper.getPlayer();

        if (this.headless)
        {
            var1.rotationPitch = this.lastPitch;
        }

        if (this.backward)
        {
            var1.rotationYawHead = this.lastYawHead;
        }

        if (this.derp)
        {
            var1.rotationPitch = this.lastPitch;
            var1.rotationYaw = this.lastYaw;
            var1.rotationYawHead = this.lastYawHead;
        }
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (var1.equalsIgnoreCase("retard"))
        {
            this.toggleState();
            String var4 = this.enabled ? "Retard mode enabled." : "Retard mode disabled.";
            AxiomHelper.chatMsg(var4);
            return true;
        }
        else
        {
            return false;
        }
    }
}
