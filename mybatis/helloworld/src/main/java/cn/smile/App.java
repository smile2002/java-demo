package cn.smile;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import cn.smile.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SqlSessionFactory sqlSessionFactory;
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");

            /** SessionFactory创建过程中解析 mybatis-config.xml 配置，
                识别POJO类型，解析所有Mapper **/
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();

            SqlSession sqlSession = sqlSessionFactory.openSession();
            try {
                List<User> userList = sqlSession.selectList("selectAll");
                for (User user : userList) {
                    System.out.printf("%s %s %s\n", user.getUserId(), user.getAge(), user.getName());
                }
            } finally {
                sqlSession.close();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
