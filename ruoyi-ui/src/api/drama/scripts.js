import request from '@/utils/request'

// 查询剧本管理列表
export function listScripts(query) {
  return request({
    url: '/drama/scripts/list',
    method: 'get',
    params: query
  })
}

// 查询剧本管理详细
export function getScripts(id) {
  return request({
    url: '/drama/scripts/' + id,
    method: 'get'
  })
}

// 新增剧本管理
export function addScripts(data) {
  return request({
    url: '/drama/scripts',
    method: 'post',
    data: data
  })
}

// 修改剧本管理
export function updateScripts(data) {
  return request({
    url: '/drama/scripts',
    method: 'put',
    data: data
  })
}

// 删除剧本管理
export function delScripts(id) {
  return request({
    url: '/drama/scripts/' + id,
    method: 'delete'
  })
}
