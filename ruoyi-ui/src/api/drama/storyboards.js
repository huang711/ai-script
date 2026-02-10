import request from '@/utils/request'

// 查询分镜创作列表
export function listStoryboards(query) {
  return request({
    url: '/drama/storyboards/list',
    method: 'get',
    params: query
  })
}

// 查询分镜创作详细
export function getStoryboards(id) {
  return request({
    url: '/drama/storyboards/' + id,
    method: 'get'
  })
}

// 新增分镜创作
export function addStoryboards(data) {
  return request({
    url: '/drama/storyboards',
    method: 'post',
    data: data
  })
}

// 修改分镜创作
export function updateStoryboards(data) {
  return request({
    url: '/drama/storyboards',
    method: 'put',
    data: data
  })
}

// 删除分镜创作
export function delStoryboards(id) {
  return request({
    url: '/drama/storyboards/' + id,
    method: 'delete'
  })
}
