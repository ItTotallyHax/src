package net.axiom.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil
{
    private Properties cfgProps = new Properties();
    private String filePath;

    public ConfigUtil(String var1)
    {
        this.loadConfig(var1);
    }

    public boolean loadConfig(String var1)
    {
        boolean var2 = false;

        try
        {
            FileInputStream var3 = new FileInputStream(var1);
            this.cfgProps.load(var3);
            var3.close();
            this.filePath = var1;
            var2 = true;
        }
        catch (IOException var4)
        {
            var4.printStackTrace();
        }
        catch (IllegalArgumentException var5)
        {
            var5.printStackTrace();
        }

        return var2;
    }

    public void setProp(String var1, String var2)
    {
        this.cfgProps.setProperty(var1, var2);

        try
        {
            FileOutputStream var3 = new FileOutputStream(new File(this.filePath));
            this.cfgProps.store(var3, (String)null);
            var3.close();
        }
        catch (FileNotFoundException var4)
        {
            var4.printStackTrace();
        }
        catch (IOException var5)
        {
            var5.printStackTrace();
        }
    }

    public String getProp(String var1, String var2)
    {
        String var3 = this.cfgProps.getProperty(var1);
        return var3 == null ? var2 : var3;
    }

    public boolean getBoolean(String var1, boolean var2)
    {
        String var3 = this.cfgProps.getProperty(var1);
        return var3 == null ? var2 : (var3.compareToIgnoreCase("true") == 0 ? true : (var3.compareToIgnoreCase("false") == 0 ? false : var2));
    }
}
