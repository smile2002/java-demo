package cn.smile.rpcmode;

import com.unionpay.magpie.bootstrap.MagpieBootStrap;

/**
 * Hello world!
 *
 */
public class RpcModeServer
{
    public static void main( String[] args )
    {
        System.setProperty("magpie.config.file", "magpie-rpc-svr.xml");
        MagpieBootStrap.getBootStrap().start();
    }
}
