package net.axiom.modules;

import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.Material;

public class AxiomModuleGlide extends AxiomModule
{
    public double speed = 0.057999998331069946D;

    public AxiomModuleGlide()
    {
        super("Glide", 16763904, "NONE", true);
    }

    public void onPlayerUpdate()
    {
        EntityClientPlayerMP var1 = AxiomWrapper.getPlayer();

        if (var1.motionY < 0.0D && !var1.onGround && var1.isAirBorne && !var1.isInWater() && !var1.isInsideOfMaterial(Material.lava))
        {
            var1.motionY = -this.speed;
        }
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (var1.equalsIgnoreCase("gs"))
        {
            if (var2.length < 1)
            {
                return true;
            }
            else
            {
                try
                {
                    double var4 = Double.parseDouble(var2[0]);
                    this.speed = var4;
                    AxiomHelper.chatMsg("New glide speed - " + var2[0]);
                }
                catch (NumberFormatException var6)
                {
                    var6.printStackTrace();
                }

                return true;
            }
        }
        else
        {
            return false;
        }
    }
}
