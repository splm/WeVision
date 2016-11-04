###WeVision是什么?
> 一个动画库基于[NineOldAndroids](https://github.com/JakeWharton/NineOldAndroids)的动画

------------------------

###demo预览
![](https://github.com/splm/WeVision/blob/master/device-2016-11-04-152307.png)
[文件下载](https://github.com/splm/WeVision/blob/master/wevision-demo.apk)

###如何使用
`Step 1:`
```gradle
    compile project (':wevisionengine')
    compile 'com.nineoldandroids:library:2.4.0'
```

`Step 2:`

```java
    WeVisionEngine.use(Vision.FadeInLeft).playOn(target);
    //可以简单理解为，WeVision引擎使用FadeInLeft效果，作用在Target控件上。
```

-------------------------

`注意`
> 关于playOn()方法的参数target为view类型，如果target为普通的view，那么为单一效果，如果为viewgroup那么其内部的子控件包括子viewgroup都会依次执行该效果；

###存在的问题
- 没有集成动画过程的监听器，目前还无法准确跟踪动画的执行流程；


###参考
- [NineOldAndroids](https://github.com/JakeWharton/NineOldAndroids)
- [Android View Animations](https://github.com/daimajia/AndroidViewAnimations)

###联系方式
> Email:splm_lis@163.com
