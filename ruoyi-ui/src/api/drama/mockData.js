export const mockScript = {
  id: 1,
  title: '赛博侦探之觉醒',
  content: `[场景：新东京市，雨夜，霓虹灯闪烁的后巷]

主角（李默）躺在湿漉漉的地面上，雨水打在他的脸上。
不远处，一只机械流浪狗正在啃食着什么。
巨大的全息广告牌投射出刺眼的粉色光芒，上面写着“神经元科技 - 链接你的未来”。

李默（独白）：我不知道我是谁，也不知道我为什么在这里。我只记得那个女人的眼神，还有这块...该死的芯片。

[动作] 李默摸向自己的上衣口袋，手指触碰到一块冰冷的金属物体。

李默挣扎着站起来，周围的垃圾桶散发出腐烂的味道。
远处传来了警笛声，由远及近。
他必须离开这里，马上。`,
  parsedStructure: JSON.stringify({
    scenes: [
      { location: '后巷', time: '雨夜', characters: ['李默', '机械流浪狗'] }
    ]
  })
}

export const mockEpisodes = [
  {
    id: 101,
    episodeNum: 1,
    title: '雨夜苏醒',
    summary: '李默在雨夜的后巷中醒来，失去了记忆。他发现自己口袋里有一块神秘的芯片，同时察觉到警察正在逼近。在躲避警察的过程中，他意外激活了芯片，看到了一个神秘女人的影像。'
  },
  {
    id: 102,
    episodeNum: 2,
    title: '致命追击',
    summary: '李默逃入地下复杂的管网系统。神经元科技的私家安保部队“暗影卫队”也加入了追捕。李默利用对地形的直觉（或者是肌肉记忆？）摆脱了第一波追击，但受了伤。'
  },
  {
    id: 103,
    episodeNum: 3,
    title: '黑客据点',
    summary: '受伤的李默晕倒在一个废弃的网吧前。被地下黑客组织“无面者”的成员小K救起。小K发现李默手中的芯片是最高机密，但也因此给据点带来了危险。'
  }
]

export const mockStoryboards = [
  {
    id: 1001,
    episodeId: 101,
    shotNum: 1,
    description: '特写：地面积水倒映着闪烁的霓虹灯招牌（粉色与蓝色交织）。一只满是泥泞的靴子踏入水中，激起涟漪。',
    dialogue: '（无）',
    cameraMovement: '固定',
    duration: '3s'
  },
  {
    id: 1002,
    episodeId: 101,
    shotNum: 2,
    description: '中景：李默躺在湿漉漉的地面上，雨水不断打在他的脸上。他的衣服破烂不堪。',
    dialogue: '李默（独白）：我不知道我是谁...',
    cameraMovement: '俯拍->拉',
    duration: '5s'
  },
  {
    id: 1003,
    episodeId: 101,
    shotNum: 3,
    description: '主观镜头（模糊）：李默睁开眼睛，看到的景象重影模糊。远处巨大的全息广告牌显得格外刺眼。',
    dialogue: '李默（独白）：...也不知道我为什么在这里。',
    cameraMovement: '摇',
    duration: '4s'
  }
]
