package net.axiom;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.PlayerControllerMP;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.WorldClient;

public class AxiomWrapper
{
    public static Minecraft mcObj;
    public static ScaledResolution scaledGui;

    public static EntityClientPlayerMP getPlayer()
    {
        return mcObj.thePlayer;
    }

    public static WorldClient getWorld()
    {
        return mcObj.theWorld;
    }

    public static PlayerControllerMP getController()
    {
        return mcObj.playerController;
    }
}
