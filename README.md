
# Seed

[![Build Status](https://travis-ci.org/CarlisleChan/SeedProject.svg?branch=master)](https://travis-ci.org/CarlisleChan/SeedProject)

## travis 环境变量配置

```
KEYSTORE_PASS=11111111

ALIAS_NAME=seed

ALIAS_PASS=11111111

FIR_TOKEN=6ebbbdf1f47e221394c61ab55b729e37
```

## 内容

- 依赖包统一管理并添加常用依赖包 [例子](https://github.com/CarlisleChan/SeedProject/blob/master/config.gradle)
- 基础框架 [SeedFramework](https://github.com/CarlisleChan/SeedFramework)
    - BaseActivity
    - BaseFragment
    - LazyFragment
    - Swipeback
    - FragmentAdapter
    - default theme.xml
- travis [参考](http://avnpc.com/pages/android-auto-deploy-workflow-on-travis-ci)
- 多渠道打包 [美团 walle](https://github.com/Meituan-Dianping/walle/)
- 混淆规则配置 [例子](https://github.com/CarlisleChan/SeedProject/tree/master/config)
- 通用
    - module
        - BottomTabActivity
    - provider
        - db (realm)
        - http (retrofit)
    - support (util)
        - rx
        - glide
        - channel

