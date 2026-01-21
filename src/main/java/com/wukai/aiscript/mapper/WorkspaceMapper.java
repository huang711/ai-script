package com.wukai.aiscript.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wukai.aiscript.entity.Workspace;
import org.apache.ibatis.annotations.Mapper;

/**
 * 空间表 Mapper 接口
 * 继承 BaseMapper 后，自动拥有了 insert, delete, update, selectById, selectList 等通用方法
 */
@Mapper
public interface WorkspaceMapper extends BaseMapper<Workspace> {
    // 目前暂时不需要写额外的方法，MyBatis Plus 已经够用了
}