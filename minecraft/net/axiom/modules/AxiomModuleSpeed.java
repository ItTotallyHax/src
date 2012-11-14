package net.axiom.modules;

import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.minecraft.src.EntityClientPlayerMP;

public class AxiomModuleSpeed extends AxiomModule
{
    public float landMF;
    public float jumpMF;
    public float modifier = 2.0F;

    public AxiomModuleSpeed()
    {
        super("Speedhack", 1826905, "G", false);
    }

    public void onEnable()
    {
        EntityClientPlayerMP var1 = AxiomWrapper.mcObj.thePlayer;
        this.landMF = var1.landMovementFactor;
        this.jumpMF = var1.jumpMovementFactor;
    }

    public void onPlayerUpdate()
    {
        EntityClientPlayerMP var1 = AxiomWrapper.mcObj.thePlayer;
        var1.landMovementFactor *= this.modifier;
        var1.jumpMovementFactor *= this.modifier;
    }

    public void onDisable()
    {
        EntityClientPlayerMP var1 = AxiomWrapper.mcObj.thePlayer;
        var1.landMovementFactor = this.landMF;
        var1.jumpMovementFactor = this.jumpMF;
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (var1.equalsIgnoreCase("sh"))
        {
            try
            {
                Float var4 = Float.valueOf(Float.parseFloat(var2[0]));
                this.modifier = var4.floatValue();
                AxiomHelper.chatMsg("Speedhack speed is now " + var2[0] + ".");
            }
            catch (Exception var5)
            {
                AxiomHelper.chatMsg("Numbers only.");
            }

            return true;
        }
        else
        {
            return false;
        }
    }
}
