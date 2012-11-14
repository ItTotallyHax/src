package net.axiom.modules;

import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.Material;

public class AxiomModuleNoFall extends AxiomModule
{
    private static final int safeColor = 1092893;
    private static final int dangerColor = 15865880;
    private double fallDistance = 0.0D;
    private double lastY = 0.0D;
    private boolean previousOnGround = false;

    public AxiomModuleNoFall()
    {
        super("NoFall", 1092893, false);
    }

    public void prepareMod()
    {
        EntityClientPlayerMP var1 = AxiomWrapper.getPlayer();

        if (this.isSafe())
        {
            this.fallDistance = 0.0D;
            this.modColor = 1092893;
        }
        else if (this.fallDistance > 3.3D)
        {
            this.modColor = 15865880;
        }
    }

    public void onPlayerDeath()
    {
        this.modColor = 1092893;
    }

    public void onPlayerUpdate()
    {
        if (AxiomWrapper.getPlayer().posY < this.lastY && !this.isSafe())
        {
            this.fallDistance += this.lastY - AxiomWrapper.getPlayer().posY;
        }

        this.lastY = AxiomWrapper.getPlayer().posY;
    }

    public void preMotionUpdates()
    {
        this.previousOnGround = AxiomWrapper.getPlayer().onGround;
        AxiomWrapper.getPlayer().onGround = false;
    }

    public void postMotionUpdates()
    {
        AxiomWrapper.getPlayer().onGround = this.previousOnGround;
    }

    public void onEnable()
    {
        this.modColor = 1092893;
        this.lastY = AxiomWrapper.getPlayer().posY;
    }

    public void onDisable()
    {
        if (this.modColor != 1092893)
        {
            this.enabled = true;
            AxiomHelper.chatMsg("Please wait until it is safe (green) to toggle NoFall.");
        }
    }

    private boolean isSafe()
    {
        EntityClientPlayerMP var1 = AxiomWrapper.getPlayer();
        boolean var2 = var1.isInWater();
        var2 = var2 || var1.isInsideOfMaterial(Material.web);
        var2 = var2 || var1.isInsideOfMaterial(Material.lava);
        var2 = var2 || var1.isOnLadder();
        var2 = var2 || var1.capabilities.isCreativeMode;
        return var2;
    }
}
