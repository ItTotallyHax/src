package net.axiom.modules;

import net.axiom.AxiomHelper;

public class AxiomModuleBright extends AxiomModule
{
    public float brightness = 6.0F;

    public AxiomModuleBright()
    {
        super("Bright", 16777215, "L", false);
    }

    public float onGammaSet(float var1)
    {
        AxiomModule var2 = AxiomHelper.getModByName("Wallhack");
        return var2 != null && var2.enabled ? var1 : this.brightness;
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (var1.equalsIgnoreCase("b"))
        {
            try
            {
                float var4 = Float.parseFloat(var2[0]);
                this.brightness = var4;
                AxiomHelper.chatMsg("New brightness - " + var2[0] + ".");
                return true;
            }
            catch (Exception var5)
            {
                AxiomHelper.chatMsg("Numbers only.");
            }
        }

        return false;
    }
}
