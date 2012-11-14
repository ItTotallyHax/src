package net.axiom.modules;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;

public class AxiomModuleJesus extends AxiomModule
{
    public AxiomModuleJesus()
    {
        super("Jesus", 14477544, "J", false);
    }

    public AxisAlignedBB onFluidBoundingBoxCreation(Block var1, int var2, int var3, int var4)
    {
        double var5 = (double)var2;
        double var7 = (double)var3;
        double var9 = (double)var4;
        return AxisAlignedBB.getBoundingBox(var5 + var1.minX, var7 + var1.minY, var9 + var1.minZ, var5 + var1.maxX, var7 + var1.maxY - 0.2D, var9 + var1.maxZ);
    }
}
