import Mock from 'mockjs';
Mock.mock('/api/founditems', 'get', () => {
  return {
    "data": [
      {
        "image": "https://tse2-mm.cn.bing.net/th/id/OIP-C.L4uv3jEJsduTWgZgFVFvvAHaHa?w=202&h=202&c=7&r=0&o=5&dpr=1.5&pid=1.7",
        "name": "张三",
        "phone": "1234567910",
        "description": "iPhone 12",
        "location": "教三"
      },
      {
        "image": "https://cbu01.alicdn.com/img/ibank/2019/001/272/12309272100_171072331.jpg",
        "name": "张三",
        "phone": "1234567910",
        "description": "黑色雨伞",
        "location": "教三"
      },
      {
        "image": "https://dsfs.oppo.com/archives/202003/202003100403395e674cabe09be.png",
        "name": "张三",
        "phone": "1234567910",
        "description": "电子手表",
        "location": "教三"
      },
      {
        "image": "https://tse3-mm.cn.bing.net/th/id/OIP-C.wwW1XyPAzSOr2zoK14avdgHaKI?rs=1&pid=ImgDetMain",
        "name": "张三",
        "phone": "1234567910",
        "description": "黑色耳机",
        "location": "教三"
      },
      {
        "image": "https://pic4.zhimg.com/v2-c5537b2cb5526ef4166d81be4142102a_r.jpg?source=1940ef5c",
        "name": "张三",
        "phone": "1234567910",
        "description": "高数书",
        "location": "教三"
      },
      {
        "image": "https://ts1.cn.mm.bing.net/th/id/R-C.858ea2d3fa0085e6761f420c7c38ac0e?rik=vFjtHXomOakddw&riu=http%3a%2f%2fpic.52112.com%2f180308%2f180308_101%2fLSYY3fEWmT_small.jpg&ehk=Wb6I5QaW7IIx9JmWPRxMM52bj673V%2f8wAU9I6%2fm9Da0%3d&risl=&pid=ImgRaw&r=0",
        "name": "张三",
        "phone": "1234567910",
        "description": "银色钥匙",
        "location": "教三"
      },
    ]
  }
})
