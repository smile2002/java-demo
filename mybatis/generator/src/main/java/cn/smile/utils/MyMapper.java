package cn.smile.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by Smile on 2018/7/19.
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}

