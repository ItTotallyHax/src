package net.axiom.modules;

import net.axiom.AxiomWrapper;
import net.minecraft.src.AxisAlignedBB;

public class AxiomModuleGod extends AxiomModule
{
    private double offset = 0.0625D;
    private AxisAlignedBB expanded = null;

    public AxiomModuleGod()
    {
        super("God", 52479, "NONE", false);
    }

    public void onEnable()
    {
        this.expanded = AxiomWrapper.mcObj.thePlayer.boundingBox.copy().expand(this.offset, this.offset, this.offset);
    }

    public void onPlayerUpdate()
    {
        AxiomWrapper.mcObj.thePlayer.boundingBox.minX = this.expanded.minX;
        AxiomWrapper.mcObj.thePlayer.boundingBox.minY = this.expanded.minY;
        AxiomWrapper.mcObj.thePlayer.boundingBox.minZ = this.expanded.minZ;
        AxiomWrapper.mcObj.thePlayer.boundingBox.maxX = this.expanded.maxX;
        AxiomWrapper.mcObj.thePlayer.boundingBox.maxY = this.expanded.maxY;
        AxiomWrapper.mcObj.thePlayer.boundingBox.maxZ = this.expanded.maxZ;
    }

    public void onDisable()
    {
        this.expanded = null;
    }
}
