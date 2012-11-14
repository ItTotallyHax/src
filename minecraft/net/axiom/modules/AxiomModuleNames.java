package net.axiom.modules;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import net.axiom.AxiomHelper;
import net.axiom.AxiomSetup;
import net.axiom.AxiomWrapper;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.RenderManager;
import net.minecraft.src.StringUtils;

public class AxiomModuleNames extends AxiomModule
{
    public HashMap nameList = new HashMap();

    public AxiomModuleNames()
    {
        super("NameProtect", 16777215, true);
        this.enabled = true;
    }

    public String onChatLineRender(String var1)
    {
        return this.auditChat(var1);
    }

    public int onLabelDistanceIntegerSet(EntityPlayer var1)
    {
        return this.getMaxInt();
    }

    public float onLabelDistanceFloatSet(EntityPlayer var1)
    {
        return this.getMaxFloat();
    }

    public String onPlayerNameSet(String var1)
    {
        var1 = StringUtils.stripControlCodes(var1);
        return this.getAliasByName(var1) != null ? this.getAliasByName(var1) : var1;
    }

    public int onNameColor(EntityLiving var1)
    {
        return this.getNameColor((EntityPlayer)var1);
    }

    public float onLabelScale(EntityLiving var1)
    {
        return this.scaleName(var1, RenderManager.instance);
    }

    public float scaleName(EntityLiving var1, RenderManager var2)
    {
        float var3;

        if (var1.getDistanceToEntity(var2.livingPlayer) / 4.0F <= 3.0F)
        {
            var3 = 3.0F;
        }
        else if (var1.getDistanceToEntity(var2.livingPlayer) / 4.0F >= 20.0F)
        {
            var3 = 20.0F;
        }
        else
        {
            var3 = var1.getDistanceToEntity(var2.livingPlayer) / 4.0F;
        }

        return var3;
    }

    public String getAliasByName(String var1)
    {
        Iterator var2 = this.nameList.entrySet().iterator();
        Entry var3;

        do
        {
            if (!var2.hasNext())
            {
                return null;
            }

            var3 = (Entry)var2.next();
        }
        while (!var1.equalsIgnoreCase((String)var3.getKey()));

        return (String)var3.getValue();
    }

    public String getNameByAlias(String var1)
    {
        Iterator var2 = this.nameList.entrySet().iterator();
        Entry var3;

        do
        {
            if (!var2.hasNext())
            {
                return null;
            }

            var3 = (Entry)var2.next();
        }
        while (!var1.equalsIgnoreCase((String)var3.getValue()));

        return (String)var3.getKey();
    }

    public void addUser(String var1, String var2)
    {
        boolean var3 = this.getAliasByName(var1) == null;
        var3 = var3 && this.getNameByAlias(var2) == null;

        if (var3)
        {
            this.nameList.remove(var1);
        }

        this.nameList.put(var1, var2);
    }

    public boolean delUser(String var1)
    {
        boolean var2 = this.getNameByAlias(var1) != null;

        if (var2)
        {
            this.nameList.remove(this.getNameByAlias(var1));
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getLastCodeFromIndex(String var1, int var2)
    {
        for (int var3 = var2; var3 > -1; --var3)
        {
            if (var3 != 0 && var1.charAt(var3 - 1) == 167)
            {
                return String.valueOf(var1.charAt(var3));
            }
        }

        return null;
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (var1.equalsIgnoreCase("nameprotect"))
        {
            this.toggleState();
            AxiomHelper.chatMsg("NameProtect " + (this.enabled ? "enabled." : "disabled."));
            return true;
        }
        else
        {
            String var4;
            String var7;

            if (var1.equalsIgnoreCase("name"))
            {
                if (var3.contains("\"") && var3.endsWith("\"") && var2.length >= 1)
                {
                    var4 = var3.split("\"")[1];

                    if (var4 == null)
                    {
                        return true;
                    }
                    else
                    {
                        var7 = AxiomWrapper.mcObj.session.username;
                        AxiomSetup.handleCommand(".add " + var7 + " \"" + var4 + "\"");
                        return true;
                    }
                }
                else
                {
                    AxiomHelper.chatMsg("Invalid syntax.");
                    return true;
                }
            }
            else if (var1.equalsIgnoreCase("add"))
            {
                if (var3.contains("\"") && var3.endsWith("\""))
                {
                    var4 = var3.split("\"")[1];

                    if (var4 == null)
                    {
                        return true;
                    }
                    else
                    {
                        this.delUser(var4);
                        this.addUser(var2[0], var4);
                        var7 = "Added \'" + var4 + "\'.";
                        AxiomHelper.chatMsg(var7);
                        return true;
                    }
                }
                else
                {
                    AxiomHelper.chatMsg("Invalid syntax.");
                    return true;
                }
            }
            else if (var1.equalsIgnoreCase("del"))
            {
                if (var3.contains("\"") && var3.endsWith("\""))
                {
                    var4 = var3.split("\"")[1];
                    boolean var5 = this.delUser(var4);
                    String var6 = var5 ? "Deleted \'" + var4 + "\'." : "User doesn\'t exist.";
                    AxiomHelper.chatMsg(var6);
                    return true;
                }
                else
                {
                    AxiomHelper.chatMsg("Invalid syntax.");
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
    }

    public int getNameColor(EntityPlayer var1)
    {
        return this.getAliasByName(var1.username) != null ? 52479 : (var1.isSneaking() ? 16711680 : (AxiomWrapper.mcObj.thePlayer.getDistanceToEntity(var1) > 64.0F ? 52224 : -1));
    }

    public float getMaxFloat()
    {
        return 2.14748365E9F;
    }

    public int getMaxInt()
    {
        return Integer.MAX_VALUE;
    }

    public String auditChat(String var1)
    {
        String var2 = var1;
        String var5;
        String var6;
        String var7;
        String var8;

        for (Iterator var3 = this.nameList.entrySet().iterator(); var3.hasNext(); var2 = var2.replaceAll("(?i)" + var5, var7 + var6 + "\u00a7" + var8))
        {
            Entry var4 = (Entry)var3.next();
            var5 = (String)var4.getKey();
            var6 = (String)var4.getValue();
            var7 = "\u00a73";
            var8 = this.getLastCodeFromIndex(var1.toLowerCase(), var1.toLowerCase().indexOf(var5.toLowerCase()));
            var8 = var8 == null ? "f" : var8;
        }

        return var2;
    }
}
