import request from '@/utils/request'
import { listScripts } from '@/api/drama/scripts'
import { listEpisodes } from '@/api/drama/episodes'
import { listStoryboards, updateStoryboards } from '@/api/drama/storyboards'

export function getScriptByProjectId(projectId) {
  return listScripts({ projectId }).then(res => {
    if (res.rows && res.rows.length > 0) {
      return res.rows[0]
    }
    return null
  })
}

export function listEpisodesByScriptId(scriptId) {
  return listEpisodes({ scriptId })
}

export function listStoryboardsByEpisodeId(episodeId) {
  return listStoryboards({ episodeId })
}

export function updateStoryboard(data) {
  return updateStoryboards(data)
}

export function parseScript(scriptId) {
  return request({
    url: '/drama/ai/parse/' + scriptId,
    method: 'post'
  })
}

export function generateStoryboards(episodeId) {
  return request({
    url: '/drama/ai/generate/' + episodeId,
    method: 'post'
  })
}
