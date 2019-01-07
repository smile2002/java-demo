package cn.smile.j1.lang;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Smile on 2018/11/19.
 */
public class ProxyDemo {
    public interface Phone {
        void call();
        void shutdown();
        void boot();
    }

    public static class HuaWeiPhone implements Phone {

        @Override
        public void call() {
            System.out.println("HW Phone call!");
        }

        @Override
        public void shutdown() {
            System.out.println("HW Phone shutdown!");
        }

        @Override
        public void boot() {
            System.out.println("HW Phone boot!");
        }
    }


    /**
     * 创建时必须提供 InvocationHandler 实例，并重写 invoke() 方法
     */
    public static class MyInvocationHandler implements InvocationHandler {
        private Phone target;
        public MyInvocationHandler(Phone phone) {
            this.target = phone;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Proxy start ...");
            Object ret = method.invoke(target, args);
            System.out.println("Proxy finish ...\r\n");
            System.out.println("================");
            return ret;
        }
    }



    public static void main(String[] args) {

        HuaWeiPhone hwPhone = new HuaWeiPhone();
        Phone myPhone = (Phone) Proxy.newProxyInstance(
                HuaWeiPhone.class.getClassLoader(),
                HuaWeiPhone.class.getInterfaces(),
                new MyInvocationHandler(hwPhone));

        myPhone.boot();
        myPhone.call();
        myPhone.shutdown();
    }
}

