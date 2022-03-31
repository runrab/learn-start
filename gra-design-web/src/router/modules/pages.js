import Layout from '@/layout/index.vue'
import { createNameComponent } from '../createNode'
const route = [
  {
    path: '/pages',
    component: Layout,
    redirect: '/pages/crudTable',
    meta: { title: '页面', icon: 'el-icon-document-copy' },
    alwayShow: true,
    children: [
      {
        path: 'crudTable',
        component: createNameComponent(() => import('@/views/main/pages/crudTable/index.vue')),
        meta: { title: '用户管理', cache: false, roles: ['admin', 'editor'] }
      },
      {
        path: 'categoryTable',
        component: createNameComponent(() => import('@/views/main/pages/categoryTable/index.vue')),
        meta: { title: '班级管理', cache: true, roles: ['admin'] }
      },
      {
        path: 'treeTable',
        component: createNameComponent(() => import('@/views/main/pages/treeTable/index.vue')),
        meta: { title: '学院管理', cache: true }
      }
    ]
  }
]

export default route