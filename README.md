#Launch-适配组件化开发的路由框架  
包括三部分：  
-iocAnnotation  
-iocCompiler  
-iocApi  



注解使用：
```
@LaunchAnn("RuntimeActivity")
public class RuntimeActivity extends Activity {
    
}

```
打开 activity：
```
public void onClick(View view) {
    Launch.launch("RuntimeActivity", this);
}
```
