package com.wukai.aiscript.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wukai.aiscript.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}