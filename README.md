# copilot-demo

## 项目简介

本项目是一个基于 Spring Boot 的消息微服务，主要用于管理和分发通知与公告，适用于如 uPortal-home 这样的门户系统。消息通过 JSON 文件配置，支持按用户分组和时间进行过滤，确保不同用户在合适的时间收到合适的消息。

### 主要功能
- 支持通知和公告两种消息类型。
- 消息可按用户分组（如“Portal Administrators”）和时间（goLiveDate/expireDate）进行过滤。
- 提供多种 RESTful API 接口，包括获取所有消息、按用户过滤消息、按 ID 查询消息等。
- 消息数据来源于配置的 JSON 文件（如 `src/main/resources/messages.json`）。
- 支持消息按钮、附加数据等丰富的消息内容结构。

### 主要接口
- `/`：健康检查，返回应用状态。
- `/messages`：返回当前用户可见的消息列表。
- `/message/{id}`：按 ID 获取当前用户可见的消息，自动过滤过期、未生效或无权限的消息。
- `/admin/allMessages`：返回所有消息，不做过滤，适合管理和排查。
- `/admin/message/{id}`：按 ID 获取所有消息，不做权限和时间过滤。

### 核心实现文件
- 控制器：`src/main/java/edu/wisc/my/messages/controller/MessagesController.java`
- 服务层：`src/main/java/edu/wisc/my/messages/service/MessagesService.java`
- 消息模型：`src/main/java/edu/wisc/my/messages/model/Message.java`
- 消息数据读取：`src/main/java/edu/wisc/my/messages/data/MessagesFromTextFile.java`

### 配置与启动
- 消息数据源通过 `src/main/resources/application.properties` 配置。
- 使用 Maven 构建和运行，支持单元测试和集成测试。

### 适用场景
- 校园门户、企业内部系统等需要分组、定时推送消息的场景。
- 支持灵活扩展和自定义消息内容。

详细接口和消息格式说明可参考 `docs/json.md` 和 `docs/README.md`。

### 其他
其他问题还有什么