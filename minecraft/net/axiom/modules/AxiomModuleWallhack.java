package net.axiom.modules;

import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.minecraft.src.Block;
import net.minecraft.src.Packet14BlockDig;

public class AxiomModuleWallhack extends AxiomModule
{
    private boolean sVal;
    private boolean orebf = false;
    public int opacity = 110;
    public boolean diamond = false;
    public boolean gold = false;
    public boolean iron = false;
    public boolean coal = false;
    public boolean redstone = false;
    public boolean emerald = false;
    public boolean circuits = false;
    public boolean manmade = false;
    public boolean dungeons = false;
    public boolean strongholds = false;
    public boolean hazards = false;

    public AxiomModuleWallhack()
    {
        super("Wallhack", 11184810, "X", false);
    }

    public void onRenderBlock(Block var1, int var2, int var3, int var4)
    {
        if (this.isWallhackBlock(var1.blockID) && this.orebf)
        {
            AxiomHelper.addPacket(new Packet14BlockDig(1, var2, var3, var4, 1));
        }
    }

    public int onColorOpacitySet()
    {
        return this.opacity;
    }

    public boolean onRenderAllFacesSet(Block var1, int var2, int var3, int var4)
    {
        return this.isBlockEnabled(var1.blockID);
    }

    public int onBlockRenderPassSet(int var1, Block var2)
    {
        return this.isBlockEnabled(var2.blockID) ? 0 : 1;
    }

    public float onGammaSet(float var1)
    {
        return 200.0F;
    }

    public float onBlockBrightnessFloatSet(float var1)
    {
        return 1000.0F;
    }

    public int onBlockBrightnessIntegerSet(int var1)
    {
        return 1000;
    }

    private boolean isWallhackBlock(int var1)
    {
        switch (var1)
        {
            case 14:
            case 15:
            case 16:
            case 20:
            case 23:
            case 27:
            case 28:
            case 29:
            case 33:
            case 34:
            case 41:
            case 42:
            case 46:
            case 48:
            case 50:
            case 51:
            case 52:
            case 54:
            case 55:
            case 56:
            case 58:
            case 61:
            case 62:
            case 63:
            case 64:
            case 68:
            case 71:
            case 73:
            case 75:
            case 76:
            case 98:
            case 102:
            case 119:
            case 120:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
                return true;

            case 17:
            case 18:
            case 19:
            case 21:
            case 22:
            case 24:
            case 25:
            case 26:
            case 30:
            case 31:
            case 32:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 43:
            case 44:
            case 45:
            case 47:
            case 49:
            case 53:
            case 57:
            case 59:
            case 60:
            case 65:
            case 66:
            case 67:
            case 69:
            case 70:
            case 72:
            case 74:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 99:
            case 100:
            case 101:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            default:
                return false;
        }
    }

    public boolean isBlockEnabled(int var1)
    {
        switch (var1)
        {
            case 10:
            case 11:
                return true;

            case 12:
            case 13:
            case 17:
            case 18:
            case 19:
            case 21:
            case 22:
            case 24:
            case 25:
            case 26:
            case 30:
            case 31:
            case 32:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 43:
            case 44:
            case 45:
            case 47:
            case 49:
            case 53:
            case 59:
            case 60:
            case 65:
            case 66:
            case 67:
            case 69:
            case 70:
            case 72:
            case 74:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 99:
            case 100:
            case 101:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            default:
                return false;

            case 14:
            case 41:
                return this.gold;

            case 15:
            case 42:
                return this.iron;

            case 16:
                return this.coal;

            case 20:
            case 50:
            case 54:
            case 58:
            case 61:
            case 62:
            case 63:
            case 64:
            case 68:
            case 71:
            case 102:
            case 130:
                return this.manmade;

            case 23:
            case 27:
            case 28:
            case 29:
            case 33:
            case 34:
            case 55:
            case 75:
            case 76:
            case 131:
            case 132:
                return this.circuits;

            case 46:
            case 51:
                return this.hazards;

            case 48:
            case 52:
                return this.dungeons;

            case 56:
            case 57:
                return this.diamond;

            case 73:
                return this.redstone;

            case 98:
            case 119:
            case 120:
                return this.strongholds;

            case 129:
            case 133:
                return this.emerald;
        }
    }

    public void onEnable()
    {
        this.sVal = AxiomWrapper.mcObj.gameSettings.ambientOcclusion;
        AxiomWrapper.mcObj.gameSettings.ambientOcclusion = false;
        AxiomHelper.renderBlocks();
    }

    public void onDisable()
    {
        AxiomWrapper.mcObj.gameSettings.ambientOcclusion = this.sVal;
        AxiomHelper.renderBlocks();
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (var1.equalsIgnoreCase("wo"))
        {
            if (var2.length < 1)
            {
                return true;
            }
            else
            {
                try
                {
                    int var6 = Integer.parseInt(var2[0]);
                    this.opacity = var6;
                    AxiomHelper.chatMsg("Wallhack opacity - " + var6);

                    if (this.enabled)
                    {
                        AxiomWrapper.mcObj.renderGlobal.loadRenderers();
                    }
                }
                catch (Exception var5)
                {
                    var5.printStackTrace();
                }

                return true;
            }
        }
        else if (var1.equalsIgnoreCase("orebf"))
        {
            this.orebf = !this.orebf;
            String var4 = this.orebf ? "Orebfuscator bypass enabled." : "Orebfuscator bypass disabled.";
            AxiomHelper.chatMsg(var4);

            if (this.enabled)
            {
                AxiomWrapper.mcObj.renderGlobal.loadRenderers();
            }

            return true;
        }
        else
        {
            return false;
        }
    }
}
