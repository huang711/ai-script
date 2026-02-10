import request from '@/utils/request'

// 查询分集管理列表
export function listEpisodes(query) {
  return request({
    url: '/drama/episodes/list',
    method: 'get',
    params: query
  })
}

// 查询分集管理详细
export function getEpisodes(id) {
  return request({
    url: '/drama/episodes/' + id,
    method: 'get'
  })
}

// 新增分集管理
export function addEpisodes(data) {
  return request({
    url: '/drama/episodes',
    method: 'post',
    data: data
  })
}

// 修改分集管理
export function updateEpisodes(data) {
  return request({
    url: '/drama/episodes',
    method: 'put',
    data: data
  })
}

// 删除分集管理
export function delEpisodes(id) {
  return request({
    url: '/drama/episodes/' + id,
    method: 'delete'
  })
}
