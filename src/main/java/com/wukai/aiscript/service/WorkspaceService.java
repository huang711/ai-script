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
     */
    @Transactional(rollbackFor = Exception.class)
    public Workspace createWorkspace(Long userId, String name) {
        // 1. 插入空间表
        Workspace workspace = new Workspace();
        workspace.setName(name);
        workspace.setOwnerId(userId);
        workspace.setType("TEAM");
        workspace.setCreateTime(LocalDateTime.now());
        workspaceMapper.insert(workspace);

        // 2. 插入成员表
        WorkspaceMember member = new WorkspaceMember();
        member.setWorkspaceId(workspace.getId());
        member.setUserId(userId);

        //角色: 1=Editor, 9=Admin, 10=Owner)
        member.setRole(10);

        member.setJoinTime(LocalDateTime.now());
        memberMapper.insert(member);

        return workspace;
    }
}