package seu.cc.test;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.VariableElement;

/**
 * Created by wuxiangyu on 2017/7/18.
 */

public class CakeInfo {
    Map<String, String> map = new HashMap<>();
    public void putPackageName(String key, String classFullName) {
        map.put(key, classFullName);
    }

    public String getPackageName(String key) {
        return map.get(key);
    }

    public static final String PACKAGE_NAME = "com.seu.util";
    public static final String CLASS_NAME = "LaunchUtil";
    public String getJavaCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(PACKAGE_NAME).append(";\n\n");
        sb.append("import java.util.HashMap;\n");
        sb.append("import seu.cc.compiler.LaunchInjector;\n");
        sb.append("public class ").append(CLASS_NAME).append(" implements LaunchInjector{\n");
        sb.append("static HashMap<String, String> map = new HashMap<String, String>();\n");
        sb.append("static{\n");
        for (String key: map.keySet()) {
            String classFullName = map.get(key);
            sb.append("map.put(\"" + key + "\", \"" + classFullName + "\");\n");
        }
        sb.append("}\n");
        sb.append("public String getPackageName(String key) {\n");
        sb.append("return map.get(key);\n");
        sb.append("}\n");
        sb.append("}\n");
        return sb.toString();
    }
}
