import request from '@/utils/request'

// 查询空间成员列表
export function listMember(query) {
  return request({
    url: '/workspace/member/list',
    method: 'get',
    params: query
  })
}

// 查询空间成员详细
export function getMember(id) {
  return request({
    url: '/workspace/member/' + id,
    method: 'get'
  })
}

// 新增空间成员
export function addMember(data) {
  return request({
    url: '/workspace/member',
    method: 'post',
    data: data
  })
}

// 修改空间成员
export function updateMember(data) {
  return request({
    url: '/workspace/member',
    method: 'put',
    data: data
  })
}

// 删除空间成员
export function delMember(id) {
  return request({
    url: '/workspace/member/' + id,
    method: 'delete'
  })
}
