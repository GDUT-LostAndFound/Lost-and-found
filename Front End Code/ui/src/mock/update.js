import Mock from 'mockjs';
Mock.mock('/api/update', 'put', () => {
    return {
        "code": 0,
        "message": "更新成功",
    };
})
