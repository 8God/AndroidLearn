package seu.cc;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

/**
 * Created by wuxiangyu on 2017/7/3.
 */

public class ProxyInfo {
    private String packageName;
    private String proxyClassName;
    private TypeElement typeElement;
    /**一个类，其中可以有多个Bindview对应的field*/
    public Map<Integer, VariableElement> injectVariables = new HashMap<>();

    public static final String PROXY = "ViewInject";

    public ProxyInfo(Elements elementUtils, TypeElement classElement) {
        this.typeElement = classElement;
        PackageElement packageElement = elementUtils.getPackageOf(classElement);
        String packageName = packageElement.getQualifiedName().toString();
        //classname
        String className = ClassValidator.getClassName(classElement, packageName);
        this.packageName = packageName;
        this.proxyClassName = className + "$$" + PROXY;
    }
    public String getJavCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(packageName).append(";\n\n");
        sb.append("public class ").append(proxyClassName).append(" {\n");
        sb.append(" public void inject (").append(typeElement.getQualifiedName()).append(" activity, Object source) {\n");
        sb.append("if (source instanceof android.app.Activity) {\n");
        for (int id : injectVariables.keySet()) {
            VariableElement element = injectVariables.get(id);
            String name = element.getSimpleName().toString();
            String type = element.asType().toString();
            sb.append("activity.").append(name).append(" = (" + type + ")(((android.app.Activity)source).findViewById(" + id + "));\n");
        }
        sb.append("} else {\n");
        for (int id : injectVariables.keySet()) {
            VariableElement element = injectVariables.get(id);
            String name = element.getSimpleName().toString();
            String type = element.asType().toString();
            sb.append("activity.").append(name).append(" = (" + type + ")(((android.view.View)source).findViewById(" + id + "));\n");
        }
        sb.append("}\n");
        sb.append("}\n");
        sb.append("}\n");
        return sb.toString();
    }

//    public String generateJavaCode() {
//        StringBuilder builder = new StringBuilder();
//        builder.append("// Generated code. Do not modify!\n");
//        builder.append("package ").append(packageName).append(";\n\n");
////        builder.append("import com.zhy.ioc.*;\n");
//        builder.append('\n');
//
//        builder.append("public class ").append(proxyClassName).append(" implements " + ProxyInfo.PROXY + "<" + typeElement.getQualifiedName() + ">");
//        builder.append(" {\n");
//
//        generateMethods(builder);
//        builder.append('\n');
//
//        builder.append("}\n");
//        return builder.toString();
//
//    }


//    private void generateMethods(StringBuilder builder) {
//
//        builder.append("@Override\n ");
//        builder.append("public void inject(" + typeElement.getQualifiedName() + " host, Object source ) {\n");
//
//
//        for (int id : injectVariables.keySet()) {
//            VariableElement element = injectVariables.get(id);
//            String name = element.getSimpleName().toString();
//            String type = element.asType().toString();
//            builder.append(" if(source instanceof android.app.Activity){\n");
//            builder.append("host." + name).append(" = ");
//            builder.append("(" + type + ")(((android.app.Activity)source).findViewById( " + id + "));\n");
//            builder.append("\n}else{\n");
//            builder.append("host." + name).append(" = ");
//            builder.append("(" + type + ")(((android.view.View)source).findViewById( " + id + "));\n");
//            builder.append("\n};");
//        }
//        builder.append("  }\n");
//    }

    public String getProxyClassFullName() {
        return packageName + "." + proxyClassName;
    }

    public TypeElement getTypeElement() {
        return typeElement;
    }
}
