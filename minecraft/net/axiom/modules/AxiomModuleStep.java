package net.axiom.modules;

import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;

public class AxiomModuleStep extends AxiomModule
{
    public float height = 0.5F;

    public AxiomModuleStep()
    {
        super("Step", 16777215, true);
        this.enabled = true;
    }

    public void onPlayerUpdate()
    {
        AxiomWrapper.mcObj.thePlayer.stepHeight = this.height;
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (var1.equalsIgnoreCase("step"))
        {
            if (var2.length < 1)
            {
                return true;
            }
            else
            {
                try
                {
                    float var4 = Float.parseFloat(var2[0]);
                    this.height = var4;
                    AxiomHelper.chatMsg("Step height set to " + var2[0] + ".");
                    return true;
                }
                catch (NumberFormatException var5)
                {
                    AxiomHelper.chatMsg("Numbers only.");
                    return true;
                }
            }
        }
        else
        {
            return false;
        }
    }
}
