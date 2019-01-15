package cn.smile.binmode;

import com.unionpay.magpie.bootstrap.BootStrap;
import com.unionpay.magpie.bootstrap.MagpieBootStrap;
import com.unionpay.magpie.client.ServiceRegistry;

/**
 * Hello world!
 *
 */
public class BinModeClient
{
    public static void main( String[] args )
    {
        try {
            System.setProperty("magpie.config.file", "magpie-bin-cli.xml");
            BootStrap bootStrap = MagpieBootStrap.getBootStrap();
            bootStrap.start();
            Thread.sleep(1000);
            byte[] resultBytes = ServiceRegistry.getService("myService1").call("hello magpie".getBytes());
            System.out.println("result = " + new String(resultBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
