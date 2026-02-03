import request from '@/utils/request'

// 查询创作资产列表
export function listAssets(query) {
  return request({
    url: '/system/assets/list',
    method: 'get',
    params: query
  })
}

// 查询创作资产详细
export function getAssets(id) {
  return request({
    url: '/system/assets/' + id,
    method: 'get'
  })
}

// 新增创作资产
export function addAssets(data) {
  return request({
    url: '/system/assets',
    method: 'post',
    data: data
  })
}

// 修改创作资产
export function updateAssets(data) {
  return request({
    url: '/system/assets',
    method: 'put',
    data: data
  })
}

// 删除创作资产
export function delAssets(id) {
  return request({
    url: '/system/assets/' + id,
    method: 'delete'
  })
}
