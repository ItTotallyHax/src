package net.axiom.modules;

import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.minecraft.src.Block;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.ItemStack;

public class AxiomModuleSpeedmine extends AxiomModule
{
    public float modifier = 2.0F;

    public AxiomModuleSpeedmine()
    {
        super("Speedmine", 11062347, "P", false);
        this.modTag = "Speedy Gonzales";
        this.modifier = 1.4F;
    }

    public void prepareMod() {}

    public int onBlockHitDelaySet(int var1)
    {
        return 0;
    }

    public float onCurBlockDamageSet(Block var1, int var2, int var3, int var4, float var5)
    {
        float var6 = var1.getPlayerRelativeBlockHardness(AxiomWrapper.mcObj.thePlayer, AxiomWrapper.mcObj.thePlayer.worldObj, var2, var3, var4);
        return var6 * this.modifier;
    }

    public void onClickBlock(int var1, int var2, int var3, int var4)
    {
        this.selectTool(var1, var2, var3);
    }

    public void selectTool(int var1, int var2, int var3)
    {
        int var4 = AxiomWrapper.getWorld().getBlockId(var1, var2, var3);

        if (var4 != 0)
        {
            EntityClientPlayerMP var5 = AxiomWrapper.mcObj.thePlayer;
            float var6 = 0.1F;
            int var7 = AxiomWrapper.mcObj.thePlayer.inventory.currentItem;

            for (int var8 = 36; var8 < 45; ++var8)
            {
                ItemStack var9 = var5.inventorySlots.getSlot(var8).getStack();

                if (var9 != null)
                {
                    float var10 = var9.getStrVsBlock(Block.blocksList[var4]);

                    if (var10 > var6)
                    {
                        var6 = var10;
                        var7 = var8 - 36;
                    }
                }
            }

            AxiomWrapper.mcObj.thePlayer.inventory.currentItem = var7;
        }
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (var1.equalsIgnoreCase("ms"))
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
                    this.modifier = var4;
                    AxiomHelper.chatMsg("Mining speed is now " + var2[0] + ".");
                }
                catch (Exception var5)
                {
                    AxiomHelper.chatMsg("Numbers only.");
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
