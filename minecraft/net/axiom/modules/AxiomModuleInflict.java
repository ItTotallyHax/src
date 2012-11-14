package net.axiom.modules;

import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Packet28EntityVelocity;
import net.minecraft.src.Packet7UseEntity;

public class AxiomModuleInflict extends AxiomModule
{
    public long currentms = 0L;
    public long threshold = 1000L;
    public long lastHit = 0L;

    public AxiomModuleInflict()
    {
        super("Inflict", 15559708, "NONE", false);
    }

    public void prepareMod()
    {
        this.currentms = System.nanoTime() / 1000000L;
    }

    public boolean onEntityVelocity(Packet28EntityVelocity var1)
    {
        return var1.entityId != AxiomWrapper.mcObj.thePlayer.entityId;
    }

    public void onPlayerUpdate()
    {
        boolean var1 = this.currentms - this.lastHit >= this.threshold;

        if (var1)
        {
            AxiomHelper.addPacket(new Packet7UseEntity(AxiomWrapper.mcObj.thePlayer.entityId, AxiomWrapper.mcObj.thePlayer.entityId, 1));
            AxiomWrapper.mcObj.thePlayer.swingItem();
            this.lastHit = System.nanoTime() / 1000000L;
        }
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (var1.equalsIgnoreCase("is"))
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
                    float var5 = 1000.0F / var4;
                    var5 = (float)MathHelper.ceiling_float_int(var5);
                    var5 += 2.0F;
                    long var6 = (long)var5;
                    this.threshold = var6;
                    AxiomHelper.chatMsg("New infliction speed - " + var2[0] + " attacks per second.");
                }
                catch (Exception var8)
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
