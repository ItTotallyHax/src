package net.axiom;

import java.util.Iterator;
import net.axiom.modules.AxiomModule;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Packet;

public class AxiomHelper
{
    public static void chatMsg(String var0)
    {
        if (AxiomWrapper.getPlayer() != null)
        {
            AxiomWrapper.getPlayer().addChatMessage("\u00a73[Axiom]:\u00a7f " + var0);
        }
    }

    public static void consoleMsg(Object var0)
    {
        System.out.println("[Axiom]: " + var0);
    }

    public static void checkToggles()
    {
        AxiomInput var0 = AxiomSetup.input;
        Iterator var1 = AxiomSetup.modules.iterator();

        while (var1.hasNext())
        {
            AxiomModule var2 = (AxiomModule)var1.next();

            if (var0.checkKey(var2.modBind))
            {
                var2.toggleState();
            }
        }
    }

    public static void disableMods()
    {
        Iterator var0 = AxiomSetup.modules.iterator();

        while (var0.hasNext())
        {
            AxiomModule var1 = (AxiomModule)var0.next();
            var1.onDisable();
        }
    }

    public static AxiomModule getModByName(String var0)
    {
        Iterator var1 = AxiomSetup.modules.iterator();
        AxiomModule var2;

        do
        {
            if (!var1.hasNext())
            {
                return null;
            }

            var2 = (AxiomModule)var1.next();
        }
        while (!var2.modName.equalsIgnoreCase(var0));

        return var2;
    }

    public static EntityPlayer getPlayerByName(String var0)
    {
        for (int var1 = 0; var1 < AxiomWrapper.mcObj.theWorld.loadedEntityList.size(); ++var1)
        {
            Entity var2 = (Entity)AxiomWrapper.mcObj.theWorld.loadedEntityList.get(var1);

            if (var2 instanceof EntityPlayer)
            {
                EntityPlayer var3 = (EntityPlayer)var2;

                if (var3.username.equalsIgnoreCase(var0))
                {
                    return var3;
                }
            }
        }

        return null;
    }

    public static void addPacket(Packet var0)
    {
        AxiomWrapper.mcObj.getSendQueue().addToSendQueue(var0);
    }

    public static int blockNameToID(String var0)
    {
        var0 = var0.toLowerCase();
        Block[] var1 = Block.blocksList;
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3)
        {
            Block var4 = var1[var3];

            if (var4 != null)
            {
                String var5 = var4.getBlockName();

                if (var5 != null)
                {
                    var5 = var5.toLowerCase().replaceAll("tile.", "");

                    if (var5.contains("ore"))
                    {
                        var5 = var5.toLowerCase().replaceAll("ore", "");
                        var5 = var5 + "ore";
                    }

                    if (var5.contains("block"))
                    {
                        var5 = var5.toLowerCase().replaceAll("block", "");
                        var5 = var5 + "block";
                    }

                    if (var5.equalsIgnoreCase(var0))
                    {
                        return var4.blockID;
                    }
                }
            }
        }

        return -1;
    }

    public static String blockIDToName(int var0)
    {
        Block[] var1 = Block.blocksList;
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3)
        {
            Block var4 = var1[var3];

            if (var4 != null)
            {
                String var5 = var4.getBlockName();

                if (var5 != null)
                {
                    var5 = var5.toLowerCase().replaceAll("tile.", "");

                    if (var5.contains("ore"))
                    {
                        var5 = var5.toLowerCase().replaceAll("ore", "");
                        var5 = var5 + "ore";
                    }

                    if (var5.contains("block"))
                    {
                        var5 = var5.toLowerCase().replaceAll("block", "");
                        var5 = var5 + "block";
                    }

                    if (var4.blockID == var0)
                    {
                        return var5;
                    }
                }
            }
        }

        return null;
    }

    public static void renderBlocks()
    {
        int var0 = (int)AxiomWrapper.getPlayer().posX;
        int var1 = (int)AxiomWrapper.getPlayer().posY;
        int var2 = (int)AxiomWrapper.getPlayer().posZ;
        AxiomWrapper.mcObj.renderGlobal.markBlockRangeNeedsUpdate(var0 - 200, var1 - 200, var2 - 200, var0 + 200, var1 + 200, var2 + 200);
    }

    public static double entityDistance(Entity var0, Entity var1)
    {
        double var2 = var1.posX - var0.posX;
        double var4 = var1.posY - var0.posY;
        double var6 = var1.posZ - var0.posZ;
        double var8 = Math.sqrt(var2 * var2 + var4 * var4 + var6 * var6);
        return var8;
    }
}
