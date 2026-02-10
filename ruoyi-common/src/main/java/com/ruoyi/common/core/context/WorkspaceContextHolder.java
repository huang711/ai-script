package com.ruoyi.common.core.context;

/**
 * 工作空间上下文
 * 
 * @author ruoyi
 */
public class WorkspaceContextHolder
{
    private static final ThreadLocal<Long> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setWorkspaceId(Long workspaceId)
    {
        CONTEXT_HOLDER.set(workspaceId);
    }

    public static Long getWorkspaceId()
    {
        return CONTEXT_HOLDER.get();
    }

    public static void clear()
    {
        CONTEXT_HOLDER.remove();
    }
}
