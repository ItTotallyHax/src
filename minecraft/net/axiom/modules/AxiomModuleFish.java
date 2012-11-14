package net.axiom.modules;

import java.util.Iterator;
import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.minecraft.src.EntityFishHook;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Packet15Place;

public class AxiomModuleFish extends AxiomModule
{
    public AxiomModuleFish()
    {
        super("AutoFish", 16763904, "NONE", false);
    }

    public void onPlayerUpdate()
    {
        Iterator var1 = AxiomWrapper.mcObj.theWorld.loadedEntityList.iterator();

        while (var1.hasNext())
        {
            Object var2 = var1.next();
            ItemStack var3 = AxiomWrapper.mcObj.thePlayer.inventory.getCurrentItem();

            if (var3 == null)
            {
                return;
            }

            if (var2 instanceof EntityFishHook && var3.itemID == 346)
            {
                EntityFishHook var4 = (EntityFishHook)var2;

                if (var4.motionX == 0.0D && var4.motionZ == 0.0D && var4.motionY != 0.0D)
                {
                    AxiomHelper.addPacket(new Packet15Place(-1, -1, -1, -1, AxiomWrapper.mcObj.thePlayer.inventory.getCurrentItem(), 0.0F, 0.0F, 0.0F));
                    break;
                }
            }
        }
    }
}
