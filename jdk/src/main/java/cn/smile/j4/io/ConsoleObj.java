package cn.smile.j4.io;

import java.io.Console;

/**
 * Created by Smile on 2018/10/25.
 */
public class ConsoleObj {
    public static void main(String arg[]) {

        Console console = System.console();
        if (console != null) {
            console.printf("Hello Console!!");
            console.flush();
        }
    }
}
