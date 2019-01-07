package cn.smile.binmode;

import com.unionpay.magpie.bootstrap.BootStrap;
import com.unionpay.magpie.bootstrap.MagpieBootStrap;

/**
 * Hello world!
 *
 */
public class BinModeServer
{
    public static void main( String[] args )
    {
        try {
            System.out.println("Hello World!");
            BootStrap bootStrap = MagpieBootStrap.getBootStrap(new ServicePayloadListener());
            System.setProperty("magpie.config.file", "magpie-bin-svr.xml");
            bootStrap.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
