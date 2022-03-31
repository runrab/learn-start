import request from '@/utils/system/request'
const baseURI="http://localhost:8085/api"
// 获取数据api
export function getData(data) {
  return request({
    url: '/user/listAll',
    method: 'post',
    baseURL: baseURI,
    data
  },
  // console.log(data),
  )
}
// 获取分类数据
export function getCategory(data) {
  return request({
    url: '/user/grade',
    method: 'post',
    baseURL: baseURI,
    data
  })
}
// 获取树组织数据
export function getTree(data) {
  return request({
    url: '/user/academy',
    method: 'post',
    baseURL: baseURI,
    data
  })
}
// 新增
export function add(data) {
  return request({
    url: '/user/add',
    method: 'post',
    baseURL: baseURI,
    data
  },
  console.log(data)
  )
}
// 编辑
export function update(data) {
  return request({
    url: '/user/update',
    method: 'post',
    baseURL: baseURI,
    data
  })
}
// 删除
export function del(data) {
  return request({
    url: '/user/del',
    method: 'post',
    baseURL: baseURI,
    data
  })
}