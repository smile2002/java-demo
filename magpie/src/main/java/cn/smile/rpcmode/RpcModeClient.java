package cn.smile.rpcmode;

import com.unionpay.magpie.bootstrap.BootStrap;
import com.unionpay.magpie.bootstrap.MagpieBootStrap;
import com.unionpay.magpie.client.ServiceRegistry;

/**
 * Hello world!
 *
 */
public class RpcModeClient
{
    public static void main( String[] args )
    {
        System.setProperty("magpie.config.file", "magpie-bin-cli.xml");
        BootStrap bootStrap = MagpieBootStrap.getBootStrap();
        bootStrap.start();

        IHelloService helloService = ServiceRegistry.getService(IHelloService.class);
        String resultStr = helloService.sayHello("Hello Magpie!!");
        System.out.println("result string = " + resultStr);
        bootStrap.shutDown();
        System.exit(0);
    }
}
