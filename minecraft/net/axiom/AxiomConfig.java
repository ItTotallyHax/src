package net.axiom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import net.axiom.modules.AxiomModule;
import net.axiom.util.ConfigUtil;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class AxiomConfig
{
    public ConfigUtil keybindConfig;
    public ConfigUtil hideConfig;

    public AxiomConfig()
    {
        File var1 = new File(Minecraft.getAppDir("minecraft") + "/axiom");

        if (!var1.exists())
        {
            AxiomHelper.consoleMsg("Config directory did not exist. Creating...");
            boolean var2 = var1.mkdir();
            String var3 = var2 ? "Config directory created." : "Could not create config directory.";
            AxiomHelper.consoleMsg(var3);
        }

        this.checkBindConfig();
        this.checkHideConfig();
        this.keybindConfig = new ConfigUtil(Minecraft.getAppDir("minecraft") + "/axiom/bind.properties");
        this.hideConfig = new ConfigUtil(Minecraft.getAppDir("minecraft") + "/axiom/hide.properties");
    }

    private void checkBindConfig()
    {
        File var1 = new File(Minecraft.getAppDir("minecraft") + "/axiom");
        File var2 = new File(var1 + "/bind.properties");

        if (!var2.exists())
        {
            AxiomHelper.consoleMsg("Bind configuartion did not exist. Creating...");

            try
            {
                boolean var3 = var2.createNewFile();
                String var4 = var3 ? "Bind configuration file created." : "Could not create bind config file.";
                Properties var5 = new Properties();
                Iterator var6 = AxiomSetup.modules.iterator();

                while (var6.hasNext())
                {
                    AxiomModule var7 = (AxiomModule)var6.next();
                    var5.put(var7.modName.toLowerCase(), Keyboard.getKeyName(var7.modBind));
                }

                FileOutputStream var9 = new FileOutputStream(var2);
                var5.store(var9, "Blackstar Keybind Configuration.\nRemember, changing this file directly may result in unpredictable outcomes with the client.\nIt is advised that you DO NOT change this file directly.\nIf you\'re getting keybind problems, delete this file and restart Minecraft.");
                var9.close();
            }
            catch (IOException var8)
            {
                var8.printStackTrace();
            }
        }
    }

    private void checkHideConfig()
    {
        File var1 = new File(Minecraft.getAppDir("minecraft") + "/axiom");
        File var2 = new File(var1 + "/hide.properties");

        if (!var2.exists())
        {
            AxiomHelper.consoleMsg("Module visibility configuartion did not exist. Creating...");

            try
            {
                boolean var3 = var2.createNewFile();
                String var4 = var3 ? "Module visibility configuration file created." : "Could not create bind config file.";
                Properties var5 = new Properties();
                Iterator var6 = AxiomSetup.modules.iterator();

                while (var6.hasNext())
                {
                    AxiomModule var7 = (AxiomModule)var6.next();
                    var5.put(var7.modName.toLowerCase(), String.valueOf(var7.hidden));
                }

                FileOutputStream var9 = new FileOutputStream(var2);
                var5.store(var9, (String)null);
                var9.close();
            }
            catch (IOException var8)
            {
                var8.printStackTrace();
            }
        }
    }

    public void loadBindConfig()
    {
        Iterator var1 = AxiomSetup.modules.iterator();

        while (var1.hasNext())
        {
            AxiomModule var2 = (AxiomModule)var1.next();
            String var3 = this.keybindConfig.getProp(var2.modName.toLowerCase(), Keyboard.getKeyName(var2.modBind));
            var2.modBind = Keyboard.getKeyIndex(var3);
            AxiomHelper.consoleMsg("Loaded \'" + var2.modName + "\', bind - " + var3 + ".");
        }
    }

    public void loadHideConfig()
    {
        AxiomModule var2;
        boolean var4;

        for (Iterator var1 = AxiomSetup.modules.iterator(); var1.hasNext(); var2.hidden = var4)
        {
            var2 = (AxiomModule)var1.next();
            String var3 = this.hideConfig.getProp(var2.modName.toLowerCase(), String.valueOf(var2.hidden));
            var4 = Boolean.parseBoolean(var3);
        }
    }

    public void setBind(String var1, String var2)
    {
        this.keybindConfig.setProp(var1, var2);
    }

    public void setHidden(String var1, boolean var2)
    {
        this.hideConfig.setProp(var1, String.valueOf(var2));
    }

    public void saveBinds()
    {
        File var1 = new File(Minecraft.getAppDir("minecraft") + "/axiom");
        File var2 = new File(var1 + "/bind.properties");

        try
        {
            Properties var3 = new Properties();
            Iterator var4 = AxiomSetup.modules.iterator();

            while (var4.hasNext())
            {
                AxiomModule var5 = (AxiomModule)var4.next();
                var3.put(var5.modName.toLowerCase(), Keyboard.getKeyName(var5.modBind));
            }

            FileOutputStream var7 = new FileOutputStream(var2);
            var3.store(var7, (String)null);
        }
        catch (Exception var6)
        {
            var6.printStackTrace();
        }
    }

    public void saveHidden()
    {
        File var1 = new File(Minecraft.getAppDir("minecraft") + "/axiom");
        File var2 = new File(var1 + "/hide.properties");

        try
        {
            Properties var3 = new Properties();
            Iterator var4 = AxiomSetup.modules.iterator();

            while (var4.hasNext())
            {
                AxiomModule var5 = (AxiomModule)var4.next();
                var3.put(var5.modName.toLowerCase(), Boolean.valueOf(var5.hidden));
            }

            FileOutputStream var7 = new FileOutputStream(var2);
            var3.store(var7, (String)null);
        }
        catch (Exception var6)
        {
            var6.printStackTrace();
        }
    }
}
