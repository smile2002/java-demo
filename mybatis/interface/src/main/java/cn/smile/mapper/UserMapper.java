package cn.smile.mapper;

import cn.smile.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Smile on 2018/7/19.
 */
public interface UserMapper {

    @Select({"select userId,age,name from user where userId=#{haha}"})
    User selectByUserId(String userId);

    User selectById(String id);
    List<User> selectAll();

    /** 如果参数列只有一个参数，mybatis中将自动匹配 */
    List<User> selectByName(String anyName);

    /** 如果参数列为多参数，mapper中的命名参数如 #{name} 将报错
        可以使用 #{0} #{1} 或 #{param1} #{param2}  */
    List<User> selectByNameAndAge1(String name, int age);

    /** 使用@Param注解后，mapper中可以使用命名参数 #{name} */
    List<User> selectByNameAndAge2(@Param("name") String name, @Param("age") int age);

    /** 动态SQL */
    List<User> selectByNameOrAge(User user);

    int insert(User user);
    int deleteById(User user);

}
