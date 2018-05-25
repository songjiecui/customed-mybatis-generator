package com.ceiec.baseplatform.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * desc:
 *
 * @author: caokunliang
 * creat_date: 2018/1/31
 * creat_time: 16:51
 **/
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
