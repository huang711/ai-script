import request from '@/utils/request'

// 查询素材资产列表
export function listAssets(query) {
  return request({
    url: '/assets/assets/list',
    method: 'get',
    params: query
  })
}

// 查询素材资产详细
export function getAssets(id) {
  return request({
    url: '/assets/assets/' + id,
    method: 'get'
  })
}

// 新增素材资产
export function addAssets(data) {
  return request({
    url: '/assets/assets',
    method: 'post',
    data: data
  })
}

// 修改素材资产
export function updateAssets(data) {
  return request({
    url: '/assets/assets',
    method: 'put',
    data: data
  })
}

// 删除素材资产
export function delAssets(id) {
  return request({
    url: '/assets/assets/' + id,
    method: 'delete'
  })
}
