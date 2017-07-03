package seu.cc.test;

import com.google.auto.service.AutoService;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;

/**
 * 一定要在java工程下，不然AbstractProcessor会无法使用
 * apt需要使用jdk下的包，而这个在androidsdk中是没有的。
 * Created by wuxiangyu on 2017/7/3.
 * https://lizhaoxuan.github.io/2016/07/17/apt-run_demo/
 */
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@AutoService(Processor.class)
public class CakeProcessor extends AbstractProcessor {
    /**
     * 表示该Processor处理哪些注解
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<String>();
        types.add(GetMsg.class.getCanonicalName());//区别：getName，遇到数组的时候回不一样，[[String
        return types;
    }
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //用来输出到控制台，在编译器下方的Messages窗口
        Messager messager = processingEnv.getMessager();
        //遍历所有的GetMsg注解，然后进行处理
        for (Element element : roundEnvironment.getElementsAnnotatedWith(GetMsg.class)) {
            String methodName = element.getSimpleName().toString();
            messager.printMessage(Diagnostic.Kind.NOTE, "Annotation class: " + methodName);
            //获取注解的值
            int id = element.getAnnotation(GetMsg.class).id();
            String name = element.getAnnotation(GetMsg.class).name();
            messager.printMessage(Diagnostic.Kind.NOTE, "Annotation value: id: " + id + " ;name: " + name);

            messager.printMessage(Diagnostic.Kind.NOTE, "-------------------------------------------");
        }
        //返回true表示该Process已经处理了，其他的Process不需要再处理了。
        return true;
    }
}
