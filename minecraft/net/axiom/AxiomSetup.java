package net.axiom;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import net.axiom.gui.screens.AxiomGuiHub;
import net.axiom.modules.AxiomModule;
import net.axiom.modules.AxiomModuleAlts;
import net.axiom.modules.AxiomModuleAntiAFK;
import net.axiom.modules.AxiomModuleAutoAccept;
import net.axiom.modules.AxiomModuleBow;
import net.axiom.modules.AxiomModuleBright;
import net.axiom.modules.AxiomModuleChat;
import net.axiom.modules.AxiomModuleConsoleControl;
import net.axiom.modules.AxiomModuleCrumb;
import net.axiom.modules.AxiomModuleFastPlace;
import net.axiom.modules.AxiomModuleFilter;
import net.axiom.modules.AxiomModuleFish;
import net.axiom.modules.AxiomModuleFly;
import net.axiom.modules.AxiomModuleFreecam;
import net.axiom.modules.AxiomModuleGUI;
import net.axiom.modules.AxiomModuleGlide;
import net.axiom.modules.AxiomModuleInflict;
import net.axiom.modules.AxiomModuleInfo;
import net.axiom.modules.AxiomModuleJesus;
import net.axiom.modules.AxiomModuleKillAura;
import net.axiom.modules.AxiomModuleLog;
import net.axiom.modules.AxiomModuleMacro;
import net.axiom.modules.AxiomModuleMovement;
import net.axiom.modules.AxiomModuleNames;
import net.axiom.modules.AxiomModuleNegative;
import net.axiom.modules.AxiomModuleNoFall;
import net.axiom.modules.AxiomModuleNoRender;
import net.axiom.modules.AxiomModuleNuker;
import net.axiom.modules.AxiomModuleOp;
import net.axiom.modules.AxiomModulePhase;
import net.axiom.modules.AxiomModulePoint;
import net.axiom.modules.AxiomModuleProt;
import net.axiom.modules.AxiomModuleRetard;
import net.axiom.modules.AxiomModuleRevive;
import net.axiom.modules.AxiomModuleSearch;
import net.axiom.modules.AxiomModuleSneak;
import net.axiom.modules.AxiomModuleSoup;
import net.axiom.modules.AxiomModuleSpam;
import net.axiom.modules.AxiomModuleSpeed;
import net.axiom.modules.AxiomModuleSpeedmine;
import net.axiom.modules.AxiomModuleSprint;
import net.axiom.modules.AxiomModuleStep;
import net.axiom.modules.AxiomModuleTimer;
import net.axiom.modules.AxiomModuleTracers;
import net.axiom.modules.AxiomModuleUserDump;
import net.axiom.modules.AxiomModuleWallhack;
import net.axiom.thread.AxiomThreadLogin;
import net.axiom.ui.AxiomIGGUI;
import net.axiom.ui.CustomFontRenderer;
import net.minecraft.src.Block;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Potion;
import org.lwjgl.input.Keyboard;

public class AxiomSetup
{
    public static String version = "6.2.6";
    public static AxiomConfig config;
    public static ArrayList modules = new ArrayList();
    public static AxiomGuiHub gui;
    public static AxiomInput input = new AxiomInput();
    public static CustomFontRenderer customFont;
    public static String cmdChar = ".";

    public static void renderIGGUI()
    {
        initFont();

        if (!AxiomIGGUI.hidden && !AxiomWrapper.mcObj.gameSettings.showDebugInfo)
        {
            AxiomHelper.checkToggles();
            AxiomIGGUI.renderAxiomTag();
            AxiomIGGUI.renderModTags();
            AxiomIGGUI.renderCoordinates();
            AxiomIGGUI.renderOther();
        }
    }

    public static void initMods()
    {
        modules.add(new AxiomModuleBow());
        modules.add(new AxiomModuleBright());
        modules.add(new AxiomModuleCrumb());
        modules.add(new AxiomModuleFastPlace());
        modules.add(new AxiomModuleFilter());
        modules.add(new AxiomModuleFish());
        modules.add(new AxiomModuleFly());
        modules.add(new AxiomModuleFreecam());
        modules.add(new AxiomModuleGUI());
        modules.add(new AxiomModuleInflict());
        modules.add(new AxiomModuleJesus());
        modules.add(new AxiomModuleKillAura());
        modules.add(new AxiomModuleLog());
        modules.add(new AxiomModuleMovement());
        modules.add(new AxiomModuleNames());
        modules.add(new AxiomModuleNegative());
        modules.add(new AxiomModuleNoFall());
        modules.add(new AxiomModuleNuker());
        modules.add(new AxiomModulePhase());
        modules.add(new AxiomModulePoint());
        modules.add(new AxiomModuleRetard());
        modules.add(new AxiomModuleSearch());
        modules.add(new AxiomModuleSneak());
        modules.add(new AxiomModuleSoup());
        modules.add(new AxiomModuleSpam());
        modules.add(new AxiomModuleSpeed());
        modules.add(new AxiomModuleSpeedmine());
        modules.add(new AxiomModuleSprint());
        modules.add(new AxiomModuleStep());
        modules.add(new AxiomModuleTimer());
        modules.add(new AxiomModuleTracers());
        modules.add(new AxiomModuleWallhack());
        modules.add(new AxiomModuleProt());
        modules.add(new AxiomModuleMacro());
        modules.add(new AxiomModuleAntiAFK());
        modules.add(new AxiomModuleAlts());
        modules.add(new AxiomModuleGlide());
        modules.add(new AxiomModuleInfo());
        modules.add(new AxiomModuleUserDump());
        modules.add(new AxiomModuleRevive());
        modules.add(new AxiomModuleAutoAccept());
        modules.add(new AxiomModuleOp());
        modules.add(new AxiomModuleConsoleControl());
        modules.add(new AxiomModuleChat());
        modules.add(new AxiomModuleNoRender());
        config = new AxiomConfig();
        config.loadBindConfig();
        config.loadHideConfig();
        gui = new AxiomGuiHub();
        AxiomHelper.consoleMsg(modules.size() + " modules loaded.");
    }

    public static boolean modCommands(String var0, String[] var1, String var2)
    {
        boolean var3 = false;
        Iterator var4 = modules.iterator();

        while (var4.hasNext())
        {
            AxiomModule var5 = (AxiomModule)var4.next();
            boolean var6 = var5.onCommand(var0, var1, var2);

            if (var6)
            {
                var3 = var6;
            }
        }

        return var3;
    }

    public static boolean handleCommand(String var0)
    {
        if (!var0.startsWith(cmdChar))
        {
            return false;
        }
        else
        {
            String var1 = var0.replaceFirst(cmdChar, "").split(" ", 2)[0];
            String[] var2 = var0.replaceFirst(cmdChar + var1 + " ", "").split(" ");
            String var3 = var0.replaceFirst(cmdChar + var1 + " ", "");

            if (var1.equalsIgnoreCase("jump"))
            {
                EntityClientPlayerMP var33 = AxiomWrapper.mcObj.thePlayer;
                var33.motionY = 0.41999998688697815D;

                if (var33.isPotionActive(Potion.jump))
                {
                    var33.motionY += (double)((float)(var33.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
                }

                if (var33.isSprinting())
                {
                    float var27 = var33.rotationYaw * 0.017453292F;
                    var33.motionX -= (double)(MathHelper.sin(var27) * 0.2F);
                    var33.motionZ += (double)(MathHelper.cos(var27) * 0.2F);
                }

                var33.isAirBorne = true;
                return true;
            }
            else
            {
                String var4;

                if (var1.equalsIgnoreCase("say"))
                {
                    if (var3.contains("\"") && var3.endsWith("\""))
                    {
                        var4 = var3.split("\"")[1];
                        AxiomWrapper.mcObj.thePlayer.sendChatMessage(var4);
                        return true;
                    }
                    else
                    {
                        AxiomHelper.chatMsg("Invalid syntax.");
                        return true;
                    }
                }
                else if (var1.equalsIgnoreCase("gui"))
                {
                    AxiomModuleGUI var30 = (AxiomModuleGUI)AxiomHelper.getModByName("GUI");
                    var30.onEnable();
                    return true;
                }
                else if (var1.equalsIgnoreCase("reload"))
                {
                    modules.clear();
                    initMods();
                    AxiomHelper.chatMsg("Modules reloaded.");
                    return true;
                }
                else if (var1.equalsIgnoreCase("cc"))
                {
                    if (var2.length < 1)
                    {
                        return true;
                    }
                    else
                    {
                        AxiomHelper.chatMsg("New command identifier \'" + var2[0] + "\'.");
                        cmdChar = var2[0];
                        return true;
                    }
                }
                else
                {
                    AxiomModule var14;
                    int var28;

                    if (var1.equalsIgnoreCase("color"))
                    {
                        if (var3.contains("\"") && var3.endsWith("\""))
                        {
                            var4 = var3.split("\"")[1];

                            try
                            {
                                var14 = AxiomHelper.getModByName(var4);

                                if (var14 == null)
                                {
                                    AxiomHelper.chatMsg("Module does not exist.");
                                    return true;
                                }
                                else
                                {
                                    var28 = Integer.parseInt(var2[0], 16);
                                    var14.modColor = var28;
                                    AxiomHelper.chatMsg("\'" + var14.modName + "\' color updated.");
                                    return true;
                                }
                            }
                            catch (Exception var10)
                            {
                                AxiomHelper.chatMsg("Hex color codes only.");
                                return true;
                            }
                        }
                        else
                        {
                            AxiomHelper.chatMsg("Invalid syntax.");
                            return true;
                        }
                    }
                    else
                    {
                        String var19;

                        if (var1.equalsIgnoreCase("bid"))
                        {
                            if (var3.contains("\"") && var3.endsWith("\""))
                            {
                                var4 = var3.split("\"")[1];
                                Block var18 = null;
                                Block[] var26 = Block.blocksList;
                                int var25 = var26.length;

                                for (int var29 = 0; var29 < var25; ++var29)
                                {
                                    Block var32 = var26[var29];

                                    if (var32 != null && var32.translateBlockName().equalsIgnoreCase(var4))
                                    {
                                        var18 = var32;
                                    }
                                }

                                var19 = var18 == null ? "Not found." : var18.translateBlockName() + " - ID " + var18.blockID;
                                AxiomHelper.chatMsg(var19);
                                return true;
                            }
                            else
                            {
                                AxiomHelper.chatMsg("Invalid syntax.");
                                return true;
                            }
                        }
                        else if (var1.equalsIgnoreCase("bname"))
                        {
                            if (var2.length < 1)
                            {
                                return true;
                            }
                            else
                            {
                                try
                                {
                                    int var22 = Integer.parseInt(var2[0]);
                                    String var20 = AxiomHelper.blockIDToName(var22);

                                    if (var20 != null)
                                    {
                                        AxiomHelper.chatMsg(var2[0] + " - " + var20);
                                    }
                                    else
                                    {
                                        AxiomHelper.chatMsg("Not found.");
                                    }
                                }
                                catch (NumberFormatException var11)
                                {
                                    AxiomHelper.chatMsg("Block IDs only.");
                                }

                                return true;
                            }
                        }
                        else if (!var1.equalsIgnoreCase("mods"))
                        {
                            Iterator var15 = modules.iterator();
                            String var7;

                            do
                            {
                                if (!var15.hasNext())
                                {
                                    var15 = modules.iterator();

                                    do
                                    {
                                        if (!var15.hasNext())
                                        {
                                            if (var1.equalsIgnoreCase("bind"))
                                            {
                                                if (var3.contains("\"") && var3.endsWith("\""))
                                                {
                                                    var4 = var3.split("\"")[1];
                                                    var14 = AxiomHelper.getModByName(var4);

                                                    if (var14 == null)
                                                    {
                                                        AxiomHelper.chatMsg("Mod does not exist.");
                                                        return true;
                                                    }

                                                    var28 = Keyboard.getKeyIndex(var2[0].toUpperCase());
                                                    var7 = Keyboard.getKeyName(var28);
                                                    Iterator var31 = modules.iterator();
                                                    AxiomModule var9;

                                                    do
                                                    {
                                                        if (!var31.hasNext())
                                                        {
                                                            var14.modBind = var28;
                                                            AxiomHelper.chatMsg("Keybind for \'" + var14.modName + "\' set to " + var7 + " (" + var28 + ")");
                                                            config.saveBinds();
                                                            return true;
                                                        }

                                                        var9 = (AxiomModule)var31.next();
                                                    }
                                                    while (var9.modBind != var28 || var28 == 0);

                                                    AxiomHelper.chatMsg("Bind already in use by \'" + var9.modName + "\'.");
                                                    return true;
                                                }

                                                AxiomHelper.chatMsg("Invalid syntax.");
                                                return true;
                                            }

                                            double var16;

                                            if (var1.equalsIgnoreCase("yt"))
                                            {
                                                if (var2.length < 1)
                                                {
                                                    return true;
                                                }

                                                try
                                                {
                                                    var16 = Double.parseDouble(var2[0]);
                                                    EntityClientPlayerMP var23 = AxiomWrapper.mcObj.thePlayer;
                                                    var23.setPosition(var23.posX, var23.posY + var16, var23.posZ);
                                                }
                                                catch (Exception var12)
                                                {
                                                    AxiomHelper.chatMsg("Numbers only.");
                                                }

                                                return true;
                                            }

                                            if (var1.equalsIgnoreCase("hide"))
                                            {
                                                AxiomIGGUI.hidden = !AxiomIGGUI.hidden;
                                                return true;
                                            }

                                            if (var1.equalsIgnoreCase("login"))
                                            {
                                                if (var2.length < 2)
                                                {
                                                    return true;
                                                }

                                                Thread var21 = new Thread(new AxiomThreadLogin(var2[0], var2[1]), "Axiom Login Thread");
                                                var21.start();
                                                return true;
                                            }

                                            if (var1.equalsIgnoreCase("tp"))
                                            {
                                                if (var2.length < 3)
                                                {
                                                    return true;
                                                }

                                                try
                                                {
                                                    var16 = Double.parseDouble(var2[0]);
                                                    double var24 = Double.parseDouble(var2[1]);
                                                    double var8 = Double.parseDouble(var2[2]);
                                                    AxiomWrapper.mcObj.thePlayer.setPosition(var16, var24, var8);
                                                }
                                                catch (Exception var13)
                                                {
                                                    AxiomHelper.chatMsg("Numbers only.");
                                                }

                                                return true;
                                            }

                                            if (var1.equalsIgnoreCase("tpp"))
                                            {
                                                if (var2.length < 1)
                                                {
                                                    return true;
                                                }

                                                EntityPlayer var17 = AxiomHelper.getPlayerByName(var2[0]);

                                                if (var17 != null)
                                                {
                                                    AxiomWrapper.mcObj.thePlayer.setPosition(var17.posX, var17.posY + 2.5D, var17.posZ);
                                                }
                                                else
                                                {
                                                    AxiomHelper.chatMsg("Player not found.");
                                                }

                                                return true;
                                            }

                                            if (var1.equalsIgnoreCase("rr"))
                                            {
                                                AxiomWrapper.mcObj.renderGlobal.loadRenderers();
                                                return true;
                                            }

                                            if (modCommands(var1, var2, var3))
                                            {
                                                return true;
                                            }

                                            return false;
                                        }

                                        var14 = (AxiomModule)var15.next();
                                        var19 = var14.modName.replaceAll(" ", "");
                                    }
                                    while (!var1.equalsIgnoreCase("h") || var2.length <= 0 || !var2[0].equalsIgnoreCase(var19));

                                    var14.hidden = !var14.hidden;
                                    config.setHidden(var14.modName.toLowerCase(), var14.hidden);
                                    var7 = var14.enabled ? "Hidden." : "Shown.";
                                    AxiomHelper.chatMsg(var7);
                                    return true;
                                }

                                var14 = (AxiomModule)var15.next();
                                var19 = var14.modName.replaceAll(" ", "");
                            }
                            while (!var1.equalsIgnoreCase("t") || var2.length <= 0 || !var2[0].equalsIgnoreCase(var19));

                            var14.toggleState();
                            var7 = var14.enabled ? "enabled." : "disabled.";
                            AxiomHelper.chatMsg(var14.modName + " " + var7);
                            return true;
                        }
                        else
                        {
                            var4 = "";
                            AxiomModule var6;

                            for (Iterator var5 = modules.iterator(); var5.hasNext(); var4 = var4.concat("\'" + var6.modName + "\' "))
                            {
                                var6 = (AxiomModule)var5.next();
                            }

                            var4 = var4.replaceAll("\' \'", "\',\'");
                            AxiomHelper.chatMsg("Loaded Mods: " + var4);
                            return true;
                        }
                    }
                }
            }
        }
    }

    public static void initFont()
    {
        if (customFont == null)
        {
            customFont = new CustomFontRenderer(AxiomWrapper.mcObj, new Font("Verdana", 1, 17), 0, 255);
        }
    }
}
