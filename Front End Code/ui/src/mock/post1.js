import Mock from 'mockjs';
Mock.mock('/api/post1', 'post', () => {
    return {
        "code": 0,
        "message": "发送成功！",
    }
})