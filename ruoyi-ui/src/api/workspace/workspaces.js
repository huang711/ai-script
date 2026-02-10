import request from '@/utils/request'

// 查询工作空间列表
export function listWorkspaces(query) {
  return request({
    url: '/workspace/workspaces/list',
    method: 'get',
    params: query
  })
}

// 查询工作空间详细
export function getWorkspaces(id) {
  return request({
    url: '/workspace/workspaces/' + id,
    method: 'get'
  })
}

// 新增工作空间
export function addWorkspaces(data) {
  return request({
    url: '/workspace/workspaces',
    method: 'post',
    data: data
  })
}

// 修改工作空间
export function updateWorkspaces(data) {
  return request({
    url: '/workspace/workspaces',
    method: 'put',
    data: data
  })
}

// 删除工作空间
export function delWorkspaces(id) {
  return request({
    url: '/workspace/workspaces/' + id,
    method: 'delete'
  })
}
