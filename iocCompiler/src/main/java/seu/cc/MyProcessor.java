package seu.cc;

import com.example.ann.BindView;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * Created by wuxiangyu on 2017/7/3.
 */
@AutoService(Processor.class)
public class MyProcessor extends AbstractProcessor {
    private Map<String, ProxyInfo> mProxyMap = new HashMap<String, ProxyInfo>();

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
     * 注册需要解析的注解，返回支持的注解类型
     *
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(BindView.class.getCanonicalName());
        return types;
    }

    /**
     * 返回支持的源码版本，功能和@SupportedSourceVersion(SourceVersion.RELEASE_7)一致
     *
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * Element：
     * VariableElement：代表一般成员变量
     * ExecutableElement：代表类中方法，包括构造函数
     * TypeElement：代表类
     * PackageElement：代表Package
     * <p>
     * process一般包括两个步骤：收集注解信息；生成代理类(文本把编译时生成的类叫代理类)
     * return: true表示该Process已经处理了，其他的process不需要再处理了
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        mMessager.printMessage(Diagnostic.Kind.NOTE, "MyProcessor-----------------------------------");
        mProxyMap.clear();
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindView.class);
        //收集信息
        for (Element element : elements) {

            //field type
            VariableElement variableElement = (VariableElement) element;
            mMessager.printMessage(Diagnostic.Kind.NOTE, "variableElement: " + variableElement.getSimpleName());
            //TypeElement
            TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();
            String qualifiedName = typeElement.getQualifiedName().toString();//该名称包含了包名+类名
            mMessager.printMessage(Diagnostic.Kind.NOTE, "typeElement: "+ typeElement.getSimpleName() + ";qualifiedName: "+ qualifiedName);
            ProxyInfo proxyInfo = mProxyMap.get(qualifiedName);
            if (proxyInfo == null) {
                proxyInfo = new ProxyInfo(mElementUtils, typeElement);
                mProxyMap.put(qualifiedName, proxyInfo);
            }
            BindView annotation = variableElement.getAnnotation(BindView.class);
            int id = annotation.value();
            proxyInfo.injectVariables.put(id, variableElement);
        }


        for (String key : mProxyMap.keySet()) {
            ProxyInfo proxyInfo = mProxyMap.get(key);
            try {
                mMessager.printMessage(Diagnostic.Kind.NOTE, "proxyFullName: "+ proxyInfo.getProxyClassFullName() + "; TypeElement: "+ proxyInfo.getTypeElement().getSimpleName());
                JavaFileObject sourceFile = mFileUtils.createSourceFile(proxyInfo.getProxyClassFullName(), proxyInfo.getTypeElement());
                Writer writer = sourceFile.openWriter();
                writer.write(proxyInfo.getJavCode());
                writer.flush();
                writer.close();
            } catch (IOException e) {

            }
        }
        return true;
    }

    private boolean checkAnnotationValid(Element annotatedElement, Class clazz) {
        if (annotatedElement.getKind() != ElementKind.FIELD) {
            error(annotatedElement, "%s must be declared on field.", clazz.getSimpleName());
            return false;
        }
        if (ClassValidator.isPrivate(annotatedElement)) {
            error(annotatedElement, "%s() must can not be private.", annotatedElement.getSimpleName());
            return false;
        }

        return true;
    }

    private void error(Element element, String message, Object... args) {
        if (args.length > 0) {
            message = String.format(message, args);
        }
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message, element);
    }
}
