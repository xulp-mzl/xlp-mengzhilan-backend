#可重读配置类

### bean创建工具类
```
依赖于beanMapper.properties配置文件类映射
com.mengzhilan.util.BeanCreator.reload();
```

### 模型对应表单配置类
```
com.mengzhilan.form.FormConfig.reload();
```
#可清除缓存类
```
com.mengzhilan.util.ModelBaseConfigReaderUtils.clearCache();
```
###模型属性读取工具类
```
com.mengzhilan.util.ModelAttributeReaderUtils.reload();
```
###加载表单选项值来源工具类
```
com.mengzhilan.view.FormValueFromUtils.reload();
```