package net.axiom.modules;

import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;

public class AxiomModuleInfo extends AxiomModule
{
    public AxiomModuleInfo()
    {
        super("Info", 16777215, true);
        this.enabled = true;
    }

    private float getDamageToClient(EntityPlayer var1)
    {
        ItemStack var2 = var1.getCurrentEquippedItem();
        return var2 != null ? (float)var2.getDamageVsEntity(AxiomWrapper.getPlayer()) : 1.0F;
    }

    private float getDamageFromClient(EntityPlayer var1)
    {
        ItemStack var2 = AxiomWrapper.getPlayer().getCurrentEquippedItem();
        return var2 != null ? (float)var2.getDamageVsEntity(var1) : 1.0F;
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (!var1.equalsIgnoreCase("info"))
        {
            return false;
        }
        else if (var2.length < 1)
        {
            return true;
        }
        else
        {
            EntityPlayer var4 = AxiomHelper.getPlayerByName(var2[0]);

            if (var4 == null)
            {
                AxiomHelper.chatMsg("Player not found.");
                return true;
            }
            else
            {
                float var5 = this.getDamageToClient(var4);
                float var6 = this.getDamageFromClient(var4);
                String var7 = "Information for " + var4.username + ":";
                String var8 = "Armor Value: " + var4.getTotalArmorValue();
                String var9 = "Potential Damage (Receiving): " + var5;
                String var10 = "Potential Damage (Dealing): " + var6;
                String[] var11 = new String[] {var7, var8, var9, var10};
                String[] var12 = var11;
                int var13 = var11.length;

                for (int var14 = 0; var14 < var13; ++var14)
                {
                    String var15 = var12[var14];
                    AxiomHelper.chatMsg(var15);
                }

                return true;
            }
        }
    }
}
