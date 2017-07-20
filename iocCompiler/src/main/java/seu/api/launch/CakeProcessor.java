package seu.api.launch;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

import seu.annotation.router.LaunchAnn;
import seu.annotation.router.MergeLaunch;


/**
 * 一定要在java工程下，不然AbstractProcessor会无法使用
 * apt需要使用jdk下的包，而这个在androidsdk中是没有的。
 * Created by wuxiangyu on 2017/7/3.
 * https://lizhaoxuan.github.io/2016/07/17/apt-run_demo/
 */
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@AutoService(Processor.class)
public class CakeProcessor extends AbstractProcessor {
    private Filer mFileUtils;//文件相关的辅助类，生成javasourcecode
    private Elements mElementUtils;//和元素相关的辅助类，获取元素信息
    private Messager mMessager;//和日志相关的辅助类
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFileUtils = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
    }
    /**
     * 表示该Processor处理哪些注解
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<String>();
        types.add(LaunchAnn.class.getCanonicalName());//区别：getName，遇到数组的时候回不一样，[[String
        return types;
    }
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //用来输出到控制台，在编译器下方的Messages窗口
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "CakeProcessor-----------------------------------");
        //遍历所有的GetMsg注解，然后进行处理
        CakeInfo info = null;
        for (Element element : roundEnvironment.getElementsAnnotatedWith(LaunchAnn.class)) {

            TypeElement typeElement = (TypeElement) element;
            String qualifiedName = typeElement.getQualifiedName().toString();
            String key = element.getAnnotation(LaunchAnn.class).value();

            //获取注解的值
//            int id = element.getAnnotation(LaunchAnn.class).id();
//            String name = element.getAnnotation(LaunchAnn.class).name();
            Map<String, String> options = processingEnv.getOptions();
            if (options != null) {
                String moduleName = options.get("moduleName");
                messager.printMessage(Diagnostic.Kind.NOTE, "Annotation value: moduleName: " + moduleName + "-------------------------------------------" );
                info = new CakeInfo(moduleName);
                info.putPackageName(key, qualifiedName);
            }

        }
        if (info == null) {
            return false;
        }
        try {
            messager.printMessage(Diagnostic.Kind.NOTE, "className: " + info.FILE_FULL_NAME + "-------------------------------------------" );
            JavaFileObject sourceFile = mFileUtils.createSourceFile(info.FILE_FULL_NAME);
            Writer writer = sourceFile.openWriter();
            writer.write(info.getJavaCode());
            writer.flush();
            writer.close();
        } catch (IOException e) {

        }

        messager.printMessage(Diagnostic.Kind.NOTE, "generate LaunchAnn already-----------------------------------");
        //返回true表示该Process已经处理了，其他的Process不需要再处理了。
        MergelaunchInfo mergelaunchInfo = new MergelaunchInfo();
        for (Element element : roundEnvironment.getElementsAnnotatedWith(MergeLaunch.class)) {
            TypeElement typeElement = (TypeElement) element;
            String qualifiedName = typeElement.getQualifiedName().toString();
            messager.printMessage(Diagnostic.Kind.NOTE, "generate MergeLaunch already-----------------------------------+:" + qualifiedName);
        }
        return true;
    }
}
