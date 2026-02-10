import request from '@/utils/request'

// 查询项目管理列表
export function listProjects(query) {
  return request({
    url: '/drama/projects/list',
    method: 'get',
    params: query
  })
}

// 查询项目管理详细
export function getProjects(id) {
  return request({
    url: '/drama/projects/' + id,
    method: 'get'
  })
}

// 新增项目管理
export function addProjects(data) {
  return request({
    url: '/drama/projects',
    method: 'post',
    data: data
  })
}

// 修改项目管理
export function updateProjects(data) {
  return request({
    url: '/drama/projects',
    method: 'put',
    data: data
  })
}

// 删除项目管理
export function delProjects(id) {
  return request({
    url: '/drama/projects/' + id,
    method: 'delete'
  })
}
