## 聊天室小程序
1. 改造Client
2. 暴露调用接口new Client().connect();
3. 窗口ClientFrame显示完毕后调用new Client().connect();
4. 封装Client.send(String msg)函数
    - 保存Client中Channel对象，连接完成后进行初始化
    - 添加send(String msg)函数
    - 用初始化好的channel进行传输