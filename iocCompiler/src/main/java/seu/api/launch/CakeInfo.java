package seu.api.launch;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuxiangyu on 2017/7/18.
 */

public class CakeInfo {
    private String moduleName;
    public CakeInfo(String moduleName) {
        this.moduleName = moduleName;
    }
    public String getModuleName() {
        return moduleName;
    }
    Map<String, String> map = new HashMap<>();
    public void putPackageName(String key, String classFullName) {
        map.put(key, classFullName);
    }

    public String getPackageName(String key) {
        return map.get(key);
    }

    public static final String PACKAGE_NAME = "seu.com.util";
    public static final String CLASS_NAME = "LaunchUtil";
    public String getJavaCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(PACKAGE_NAME).append("." + moduleName).append(";\n\n");

        sb.append("import java.util.HashMap;\n");
        sb.append("import seu.annotation.router.MergeLaunch;\n");
        sb.append("import seu.api.compiler.LaunchInjector;\n\n");

        sb.append("@MergeLaunch\n");
        sb.append("public class ").append(CLASS_NAME).append(" implements LaunchInjector{\n");
        sb.append("public static HashMap<String, String> map = new HashMap<String, String>();\n");
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
