package cn.smile.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    static Logger logger = Logger.getLogger(App.class);

    public static void main( String[] args )
    {
        InputStream in = App.class.getClassLoader().getResourceAsStream("log4j.properties");
        Properties props = new Properties();
        try {
            props.load(in);
            PropertyConfigurator.configure(props);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //或者直接提供文件绝对路径
        //PropertyConfigurator.configure("D:\\IDEAProjects\\log4jdemo\\log4j-v1-prop\\src\\main\\resources\\log4j.properties");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.debug("Hello World!");
    }
}
