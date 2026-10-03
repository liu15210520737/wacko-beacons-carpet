# Wacko Beacons Carpet（26.2 服务端附属）

配合 [wacko-beacons](https://github.com/getcmdrolled/wacko-beacons) 客户端模组使用的 Carpet 附属。

## 背景

wacko-beacons 是一个概念验证：利用服务端不校验信标效果与金字塔层级是否匹配的漏洞，
在客户端给低层信标设置高级效果（如一层信标选力量 I、满级信标拿生命恢复 II）。

经源码比对确认：

- **1.21.11 及更早**：`BeaconMenu.updateEffects` 不做任何层级校验，服务端只通过
  `BeaconBlockEntity.filterEffect` 检查「是否为信标效果」，漏洞存在。
- **26.1 / 26.2**：Mojang 修复了该漏洞，新增 `BeaconBlockEntity.validateEffects(primary, secondary, levels)`
  服务端校验（次要效果需 4 层、效果层级不得超过塔层数、生命恢复不能作为主效果），
  且校验失败会**直接踢出玩家**。wacko-beacons 客户端模组在新版服务端上因此失效。

## 本附属的作用

添加 Carpet 规则 **`wackoBeacons`**（默认关闭）。开启后通过 Mixin 让
`BeaconBlockEntity.validateEffects` 恒为通过，恢复 1.21.11 及以前的服务端行为：

- 任意层级信标可选任意信标效果（非信标效果仍被原版 `filterEffect` 兜底过滤）；
- 不再踢出发送非法信标效果的玩家；
- 生命恢复 II 等仍需 4 层金字塔（等级提升逻辑 `applyEffects` 未改动，与旧版一致）。

## 使用

1. 服务端安装 Fabric Loader 0.19.3+、Java 25、Minecraft 26.2；
2. mods 文件夹放入 `fabric-carpet-26.2+v260616.jar` 与本模组 jar；
3. 玩家客户端放入 wacko-beacons 26.2 移植版；
4. 游戏内或控制台执行：

```
/wacko-beacons-carpet wackoBeacons true
```

## 构建

需要 JDK 25：

```bash
./gradlew build
```

## 许可

CC0-1.0（沿用原项目协议）
