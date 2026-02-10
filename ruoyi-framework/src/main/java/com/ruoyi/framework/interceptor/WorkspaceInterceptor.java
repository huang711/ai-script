package com.ruoyi.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.ruoyi.common.core.context.WorkspaceContextHolder;
import com.ruoyi.common.utils.StringUtils;

/**
 * 工作空间拦截器
 *
 * @author ruoyi
 */
@Component
public class WorkspaceInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String workspaceId = request.getHeader("X-Workspace-Id");
        if (StringUtils.isNotEmpty(workspaceId))
        {
            try
            {
                WorkspaceContextHolder.setWorkspaceId(Long.valueOf(workspaceId));
            }
            catch (NumberFormatException e)
            {
                // Ignore invalid ID
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
        WorkspaceContextHolder.clear();
    }
}
