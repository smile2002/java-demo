package cn.smile;

import cn.smile.domain.User;
import cn.smile.mapper.UserMapper;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.executor.result.ResultMapException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.scripting.ScriptingException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionException;

import java.io.IOException;
import java.io.Reader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            System.out.println("Hello World!");

            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();

            SqlSession sqlSession = sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setUserId("18999990001");
            user.setAge(40);
            user.setName("QYM");
            int result = userMapper.insert(user);
            if (result == 1) {
                System.out.println("insert successful!");
            }
            sqlSession.commit();
            sqlSession.close();
        } catch(IOException e) {
            //System.out.println(e.getMessage());
            e.getLocalizedMessage();
        } catch (PersistenceException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof MySQLIntegrityConstraintViolationException) {
                int errorCode = ((MySQLIntegrityConstraintViolationException) cause).getErrorCode();
                String sqlState = ((MySQLIntegrityConstraintViolationException) cause).getSQLState();
                System.out.printf("errorCode = %d, sqlState = %s\n", errorCode, sqlState);
            } else if (cause instanceof ResultMapException) {
                System.out.println("ResultMapException");
            } else if (cause instanceof ScriptingException) {
                System.out.println("ScriptingException");
            } else if (cause instanceof SqlSessionException) {
                System.out.println("SqlSessionException");
            } else if (cause instanceof TransactionException) {
                System.out.println("TransactionException");
            } else {
                System.out.println(e.getMessage());
            }
        }
    }
}
