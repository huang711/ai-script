package com.wukai.aiscript.service;

import com.wukai.aiscript.entity.Workspace;
import com.wukai.aiscript.entity.WorkspaceMember;
import com.wukai.aiscript.mapper.WorkspaceMapper;
import com.wukai.aiscript.mapper.WorkspaceMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class WorkspaceService {

    @Autowired
    private WorkspaceMapper workspaceMapper;

    @Autowired
    private WorkspaceMemberMapper memberMapper;

    /**
     * 创建空间
     * @param userId 创建人的ID (从 Token 中获取)
     * @param name 空间名称
     */
    @Transactional(rollbackFor = Exception.class) // 开启事务：任何一步报错，全部回滚
    public Workspace createWorkspace(Long userId, String name) {
        // 1. 插入空间表
        Workspace workspace = new Workspace();
        workspace.setName(name);
        workspace.setOwnerId(userId);
        workspace.setType("TEAM"); // 默认为团队版
        workspace.setCreateTime(LocalDateTime.now());
        workspaceMapper.insert(workspace);

        // 2. 插入成员表 (把自己设为 Owner - 角色1)
        WorkspaceMember member = new WorkspaceMember();
        member.setWorkspaceId(workspace.getId()); // 获取刚才生成的 ID
        member.setUserId(userId);
        member.setRole(1); // 1 代表拥有者
        member.setJoinTime(LocalDateTime.now());
        memberMapper.insert(member);

        return workspace;
    }
}