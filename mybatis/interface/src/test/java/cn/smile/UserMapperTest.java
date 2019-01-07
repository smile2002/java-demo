package cn.smile;

import cn.smile.domain.User;
import cn.smile.mapper.User2Mapper;
import cn.smile.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Created by Smile on 2018/7/19.
 */
public class UserMapperTest extends BaseMapperTest {

    /**
     * 删除
     */
    @Test
    public void deleteById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            User user = new User();
            user.setUserId("18999990001");
            int result = userMapper.deleteById(user);
            if (result == 1) {
                System.out.println("delete successful!");
            } else {
                System.out.println("delete failed!");
            }
        } finally {
            sqlSession.commit();
            //sqlSession.rollback();
            sqlSession.close();
        }
    }

    /**
     * 插入
     */
    @Test
    public void insertUser() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setUserId("18999990001");
            user.setAge(40);
            user.setName("QYM");
            int result = userMapper.insert(user);
            if (result == 1) {
                System.out.println("insert successful!");
            }
        } finally {
            sqlSession.commit();
            //sqlSession.rollback();
            sqlSession.close();
        }
    }

    /**
     * 单个参数
     */
    @Test
    public void testSelectByName() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.selectByName("Lucy");
            for(User user : userList) {
                System.out.printf("%s - %s - %s\n", user.getUserId(), user.getAge(), user.getName());
            }
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 多参数，不用@Param
     */
    @Test
    public void testSelectByNameAndAge1() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.selectByNameAndAge1("Lucy", 3);
            for(User user : userList) {
                System.out.printf("%s - %s - %s\n", user.getUserId(), user.getAge(), user.getName());
            }
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 多参数，启用@Param注解
     */
    @Test
    public void testSelectByNameAndAge2() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.selectByNameAndAge2("Lucy", 3);
            for(User user : userList) {
                System.out.printf("%s - %s - %s\n", user.getUserId(), user.getAge(), user.getName());
            }
        } finally {
            sqlSession.close();
        }
    }

    /**
     * XML mapper方式查询
     */
    @Test
    public void testSelectById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectById("18500000024");
            if (user != null) {
                System.out.printf("%s %s\n", user.getUserId(), user.getName());
            } else {
                System.out.println("user 18500000024 does not exists!");
            }
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 注解方式查询
     */
    @Test
    public void testSelectByUserId() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectByUserId("18500000024");
            if (user != null) {
                System.out.printf("%s %s\n", user.getUserId(), user.getName());
            } else {
                System.out.println("user 18500000024 does not exists!");
            }
        } finally {
            sqlSession.close();
        }
    }


    /**
     * 仅接口+注解方式，不依赖XML Mapper
     */
    @Test
    public void testSelectByUserId2() {
        SqlSession sqlSession = getSqlSession();
        try {
            User2Mapper user2Mapper = sqlSession.getMapper(User2Mapper.class);
            User user = user2Mapper.selectByUserId2("18500000024");
            if (user != null) {
                System.out.printf("%s %s\n", user.getUserId(), user.getName());
            } else {
                System.out.println("user 18500000024 does not exists!");
            }
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 查询所有记录（XML mapper方式）
     */
    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            List<User> userList = sqlSession.selectList("selectAll");
            for(User user : userList) {
                System.out.printf("%s - %s - %s\n", user.getUserId(), user.getAge(), user.getName());
            }
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 动态SQL - <if>
     */
    @Test
    public void testSelectByNameOrAge() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User cond = new User();
            if (false) {
                cond.setName("Lucy");
            } else {
                cond.setAge(3);
            }

            List<User> userList = userMapper.selectByNameOrAge(cond);
            for(User user : userList) {
                System.out.printf("%s - %s - %s\n", user.getUserId(), user.getAge(), user.getName());
            }
        } finally {
            sqlSession.close();
        }
    }

}
