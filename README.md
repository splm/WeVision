###What is WeVision?
> An animation libary which bases on [NineOldAndroids](https://github.com/JakeWharton/NineOldAndroids).

------------------------

###Preview
![](https://github.com/splm/WeVision/blob/master/device-2016-11-04-152307.png)
- [Donwload DemoApk](https://github.com/splm/WeVision/blob/master/wevision-demo.apk)

###How 2 use:
`Step 1:`
```gradle
    compile project (':wevisionengine')
    compile 'com.nineoldandroids:library:2.4.0'
```

`Step 2:`

```java
    //You can interpreted simply as WeVision engine use FadeInLeft effection on target view.
     WeVisionEngine.WeVisionStatus status=WeVisionEngine.use(Vision.FadeInLeft).addListener(new AbsBaseAnimation.WeVisionAnimatorListener() {
                @Override
                public void onStart(Animator animator) {
                    Log.e(TAG,"The animations of whole views are starting!");
                }

                @Override
                public void onEnd() {
                    Log.e(TAG,"All of animations have completed!");
                }

            }).playOn(target_container);
```

- `I want to check state`

```java
WeVisionStatus status=WeVisionEngine.use(Vision.FadeInLeft).playOn(targetView);
boolean isRunning=status.isRunning(); //isRunning
boolean isEnd=status.isEnd();//isEnd
```

- `I want to stop!`

```java
status.stop();
```

-------------------------

###Notice`
> About what type of parameters apply for "playOn" method,if parameter "target" is a common view,you could see a simple vision;
> but if you set a viewgroup as a parameter,all of the children in this viewgroup are going to work,and then excuted in order!

###Reference
- [NineOldAndroids](https://github.com/JakeWharton/NineOldAndroids)
- [Android View Animations](https://github.com/daimajia/AndroidViewAnimations)

###Contact me
> Email: splm_lis@163.com
