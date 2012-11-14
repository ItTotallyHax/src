package net.axiom.modules;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityItem;

public class AxiomModuleNoRender extends AxiomModule
{
    public AxiomModuleNoRender()
    {
        super("NoRender", 0, true);
    }

    public boolean onEntityRendered(Entity var1)
    {
        return var1 instanceof EntityItem ? false : super.onEntityRendered(var1);
    }
}
