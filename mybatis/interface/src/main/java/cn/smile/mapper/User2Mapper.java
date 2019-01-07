package cn.smile.mapper;

import cn.smile.domain.User;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Smile on 2018/7/19.
 */
public interface User2Mapper {
    @Select({"select userId,age,name from user where userId=#{haha}"})
    User selectByUserId2(String userId);
}
