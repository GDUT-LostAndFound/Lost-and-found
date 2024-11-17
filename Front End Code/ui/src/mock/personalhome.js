import Mock from 'mockjs';
Mock.mock('/api/personalhome', 'get', () => {
    return {
        "avatar": "https://pic2.zhimg.com/v2-5fb13110e1de13d4c11e6e7f5b8026da_r.jpg",
        "username": "rain",
        "phone": "17728273837",
        "password": "123456"
    }
})
