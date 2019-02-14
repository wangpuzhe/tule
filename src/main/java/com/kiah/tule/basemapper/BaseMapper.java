package com.kiah.tule.basemapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author kiah
 * @version Version 1.0
 * @date 2018/11/10
 */
public interface BaseMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
