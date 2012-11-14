package net.axiom.modules;

import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;

public class AxiomModuleBow extends AxiomModule
{
    public double range = 25.0D;

    public AxiomModuleBow()
    {
        super("Bow Aimbot", 39884, "NONE", false);
    }

    public void onPlayerUpdate()
    {
        this.loopAim();
    }

    public void loopAim()
    {
        AxiomModuleNames var1 = (AxiomModuleNames)AxiomHelper.getModByName("NameProtect");

        if (AxiomWrapper.mcObj.theWorld.loadedEntityList != null)
        {
            for (int var2 = 0; var2 < AxiomWrapper.mcObj.theWorld.loadedEntityList.size(); ++var2)
            {
                Entity var3 = (Entity)AxiomWrapper.mcObj.theWorld.loadedEntityList.get(var2);

                if (var3 instanceof EntityLiving && var3 != AxiomWrapper.mcObj.thePlayer && (double)AxiomWrapper.mcObj.thePlayer.getDistanceToEntity(var3) <= this.range)
                {
                    if (!AxiomWrapper.mcObj.thePlayer.canEntityBeSeen(var3))
                    {
                        return;
                    }

                    if (var3.isDead)
                    {
                        return;
                    }

                    if (var3 instanceof EntityPlayer)
                    {
                        EntityPlayer var4 = (EntityPlayer)var3;

                        if (var1.getAliasByName(var4.username) == null)
                        {
                            this.aimBow(var3);
                        }
                    }
                }
            }
        }
    }

    public void aimBow(Entity var1)
    {
        double var2 = 0.0D;
        double var4 = var1.posX - AxiomWrapper.mcObj.thePlayer.posX;
        double var6 = var1.posZ - AxiomWrapper.mcObj.thePlayer.posZ;
        double var8 = var1.posY - AxiomWrapper.mcObj.thePlayer.posY + 1.2D;

        if (var6 > 0.0D && var4 > 0.0D)
        {
            var2 = Math.toDegrees(-Math.atan(var4 / var6));
        }
        else if (var6 > 0.0D && var4 < 0.0D)
        {
            var2 = Math.toDegrees(-Math.atan(var4 / var6));
        }
        else if (var6 < 0.0D && var4 > 0.0D)
        {
            var2 = -90.0D + Math.toDegrees(Math.atan(var6 / var4));
        }
        else if (var6 < 0.0D && var4 < 0.0D)
        {
            var2 = 90.0D + Math.toDegrees(Math.atan(var6 / var4));
        }

        float var10 = (float)Math.sqrt(var6 * var6 + var4 * var4);
        float var11 = (float)(-Math.toDegrees(Math.atan(var8 / (double)var10)));
        AxiomWrapper.mcObj.thePlayer.rotationPitch = var11 - 3.0F;
        AxiomWrapper.mcObj.thePlayer.rotationYaw = (float)var2;
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (var1.equalsIgnoreCase("br"))
        {
            if (var2.length < 1)
            {
                return true;
            }
            else
            {
                try
                {
                    Double var4 = Double.valueOf(Double.parseDouble(var2[0]));
                    this.range = var4.doubleValue();
                    AxiomHelper.chatMsg("Bow Aimbot range is now " + var2[0] + ".");
                    return true;
                }
                catch (Exception var5)
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
