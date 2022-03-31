export default [
  {
    url: `/mock/table/list`,
    method: 'post',
    response: ({ body }) => {
      const { page, pageSize } = body
      return {
        code: 200,
        data: {
          [`list`]: [{
            'id|+1': 0,
            'name': '@cname',
            "number|+1": 110,
            "num|+1": 1111,
            "mail|+1": '987654321@qq.com',
            "college|+1": 'xx大学',
            "city|1": ['北京', '上海', '深圳', '广州'],
            "address|+1": '北京xxx',
            "end|+1": '2022-07-01',
            "start|+1": '2018-09-01',
            "info|+1": '考上研究生',
            "date|+1": '2018-09-01',
            "year|1": [1, 2, 3]
          }],
          pager: {
            page: page,
            pageSize: pageSize,
            total: 198
          }
        },
        msg: ''
      };
    }
  },
  {
    url: `/mock/table/category`,
    method: 'get',
    response: ({ body }) => {
      const { page, pageSize } = body
      return {
        code: 200,
        data: {
          [`list|${pageSize}`]: [{
            'id|+1': 100 * page,
            'name': '@ctitle'
          }],
          pager: {
            page: page,
            pageSize: pageSize,
            total: 100
          }
        },
        msg: ''
      };
    }
  },
  {
    url: `/mock/table/tree`,
    method: 'post',
    response: ({ body }) => {
      return {
        code: 200,
        data: [{
          label: '软件学院',
          id: 1,
          'children|5': [{
            label: '@cname',
            'id|+1': 10
          }]
        }, {
          label: '计算机学院',
          id: 2,
          children: [{
            label: '前端',
            id: 3,
            'children|5': [{
              label: '@cname',
              'id|+1': 20
            }]
          }, {
            label: '后端',
            id: 4,
            'children|5': [{
              label: '@cname',
              'id|+1': 30
            }]
          }]
        }, {
          label: '美术学院',
          id: 5,
          children: [{
            label: '市场运营',
            id: 6,
            'children|5': [{
              label: '@cname',
              'id|+1': 40
            }]
          }, {
            label: '互联网营销',
            id: 7,
            'children|5': [{
              label: '@cname',
              'id|+1': 50
            }]
          }]
        }],
        msg: ''
      };
    }
  },
  {
    url: `/mock/table/add`,
    method: 'post',
    response: ({ body }) => {
      return {
        code: 200,
        data: {},
        msg: ''
      };
    }
  },
  {
    url: `/mock/table/update`,
    method: 'post',
    response: ({ body }) => {
      return {
        code: 200,
        data: {},
        msg: ''
      };
    }
  },
  {
    url: `/mock/table/del`,
    method: 'post',
    response: ({ body }) => {
      return {
        code: 200,
        data: {},
        msg: ''
      };
    }
  },
]