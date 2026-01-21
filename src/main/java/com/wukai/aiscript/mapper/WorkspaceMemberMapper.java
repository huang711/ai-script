package com.wukai.aiscript.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wukai.aiscript.entity.WorkspaceMember;
import org.apache.ibatis.annotations.Mapper;

/**
 * 空间成员表 Mapper 接口
 */
@Mapper
public interface WorkspaceMemberMapper extends BaseMapper<WorkspaceMember> {
    // 同样，标准的 CRUD 操作由 BaseMapper 提供
}