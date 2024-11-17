import Mock from 'mockjs';
Mock.mock('/api/home', 'get', () => {
    return {
        "avatar": "https://pic2.zhimg.com/v2-5fb13110e1de13d4c11e6e7f5b8026da_r.jpg"
    }
})
