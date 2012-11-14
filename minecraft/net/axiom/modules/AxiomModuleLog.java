package net.axiom.modules;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import net.axiom.AxiomHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.src.StringUtils;

public class AxiomModuleLog extends AxiomModule
{
    public File currentLog = null;
    private OutputStreamWriter output = null;

    public AxiomModuleLog()
    {
        super("Chat Log", 16777215, true);
    }

    public void onEnable()
    {
        try
        {
            File var1 = new File(Minecraft.getAppDir("minecraft") + "/axiom");
            File var2 = new File(var1 + "/logs");

            if (!var2.exists())
            {
                var2.mkdir();
            }

            SimpleDateFormat var3 = new SimpleDateFormat("yyyyMMddHHmmss");
            var3.setTimeZone(TimeZone.getTimeZone("GMT"));
            this.currentLog = new File(var2 + "/" + var3.format(new Date()) + ".txt");
            System.out.println(this.currentLog.getAbsolutePath());
            this.currentLog.createNewFile();
            this.output = new OutputStreamWriter(new FileOutputStream(this.currentLog));
            AxiomHelper.chatMsg("Started chat logging.");
        }
        catch (Exception var4)
        {
            AxiomHelper.consoleMsg("Error starting chat logging.");
            AxiomHelper.chatMsg("Error starting chat logging.");
            var4.printStackTrace();
            this.onDisable();
        }
    }

    public void onDisable()
    {
        if (this.output != null)
        {
            try
            {
                this.output.close();
            }
            catch (IOException var2)
            {
                AxiomHelper.consoleMsg("Error stopping chat logging.");
            }
        }

        this.currentLog = null;
        this.output = null;
        AxiomHelper.chatMsg("Stopped chat logging.");
    }

    public String onChatLineRender(String var1)
    {
        try
        {
            SimpleDateFormat var2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            var2.setTimeZone(TimeZone.getTimeZone("GMT"));
            String var3 = StringUtils.stripControlCodes(var1);
            String var4 = var2.format(new Date());
            this.output.write("[" + var4 + "] " + var3 + "\r\n");
            this.output.flush();
        }
        catch (IOException var5)
        {
            AxiomHelper.consoleMsg("Chat logging error!");
            this.onDisable();
        }

        return var1;
    }
}
