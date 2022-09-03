# 项目
这是一个教程项目，请搭配教学食用

# 引用
引用了部分其他API，作为工具类使用，不作为其他使用，因为篇幅限制，直接复制进了源码里  
1. [EasyPlugin](https://github.com/CarmJos/EasyPlugin) by [@CarmJos](https://github.com/CarmJos)

# 思路
自定义物品，通过[PersistentDataContainer](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/persistence/PersistentDataContainer.html)使用自定义标签来识别物品类型  
生成物品时，写入标签；并在读取物品时，匹配标签，再做出相应判断