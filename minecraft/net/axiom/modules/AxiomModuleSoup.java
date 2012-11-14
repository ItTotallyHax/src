package net.axiom.modules;

import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Packet15Place;

public class AxiomModuleSoup extends AxiomModule
{
    private long currentMS;
    private long currentMS2;
    private long lastSoup = -1L;
    private final long threshhold = 2000L;
    private long lastPvp = -1L;
    private final long pvpThresh = 20000L;
    public boolean eatSoup = true;
    public boolean slashPvP = false;
    private final int MUSHROOM_SOUP = 282;
    private final int DIAMOND_SWORD = 276;

    public AxiomModuleSoup()
    {
        super("AutoSoup", 14375635, "NONE", false);
    }

    public void onPlayerUpdate()
    {
        if (this.slashPvP)
        {
            this.currentMS = System.nanoTime() / 1000000L;

            if (this.currentMS - this.lastSoup >= 2000L || this.lastSoup == -1L)
            {
                this.lastSoup = System.nanoTime() / 1000000L;
            }
        }

        if (AxiomWrapper.mcObj.thePlayer.getHealth() <= 16)
        {
            this.eatSoup();
        }
    }

    public void eatSoup()
    {
        boolean var1 = false;

        for (int var2 = 44; var2 >= 9; --var2)
        {
            ItemStack var3 = AxiomWrapper.mcObj.thePlayer.inventorySlots.getSlot(var2).getStack();

            if (var3 != null)
            {
                if (var2 >= 36 && var2 <= 44)
                {
                    if (var3.itemID == 282)
                    {
                        AxiomWrapper.mcObj.thePlayer.inventory.currentItem = var2 - 36;
                        AxiomHelper.addPacket(new Packet15Place(-1, -1, -1, -1, AxiomWrapper.mcObj.thePlayer.inventory.getCurrentItem(), 0.0F, 0.0F, 0.0F));
                        var1 = true;
                        return;
                    }
                }
                else if (var3.itemID == 282)
                {
                    AxiomWrapper.mcObj.playerController.windowClick(0, var2, 0, false, AxiomWrapper.mcObj.thePlayer);
                    AxiomWrapper.mcObj.playerController.windowClick(0, 37, 0, false, AxiomWrapper.mcObj.thePlayer);
                    var1 = true;
                }
            }
        }

        if (!var1)
        {
            this.currentMS2 = System.nanoTime() / 1000000L;
        }

        if (this.currentMS2 - this.lastPvp >= 20000L)
        {
            AxiomWrapper.mcObj.thePlayer.sendChatMessage("/pvp");
            this.lastPvp = System.nanoTime() / 1000000L;
        }
    }
}
