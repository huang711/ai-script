import request from '@/utils/request'

// 查询文件存储列表
export function listFiles(query) {
  return request({
    url: '/assets/files/list',
    method: 'get',
    params: query
  })
}

// 查询文件存储详细
export function getFiles(id) {
  return request({
    url: '/assets/files/' + id,
    method: 'get'
  })
}

// 新增文件存储
export function addFiles(data) {
  return request({
    url: '/assets/files',
    method: 'post',
    data: data
  })
}

// 修改文件存储
export function updateFiles(data) {
  return request({
    url: '/assets/files',
    method: 'put',
    data: data
  })
}

// 删除文件存储
export function delFiles(id) {
  return request({
    url: '/assets/files/' + id,
    method: 'delete'
  })
}
