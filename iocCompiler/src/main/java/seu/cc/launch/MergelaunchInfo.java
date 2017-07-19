package seu.cc.launch;

import java.util.ArrayList;

/**
 * Created by wuxiangyu on 2017/7/19.
 */

public class MergelaunchInfo {
    public static final String PACKAGE_NAME = "com.seu.util";
    public static final String CLASS_NAME = "LaunchTotalUtil";
    public ArrayList<String> launcUtilList = new ArrayList<>();

    public String getJavaCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(PACKAGE_NAME).append(";\n\n");

        sb.append("import java.util.HashMap;\n");
        sb.append("import com.example.router.MergeLaunch;\n");
        sb.append("import seu.cc.compiler.LaunchInjector;\n\n");

        sb.append("public class ").append(CLASS_NAME).append(" implements LaunchInjector{\n");

        sb.append("public String getPackageName(String key) {\n");
        sb.append("String fullActivityName = null;\n");
        for (String classFullName : launcUtilList) {
            sb.append("fullActivityName = " + classFullName + ".map.get(key);\n");
            sb.append("if (fullActivityName != null) {\n");
            sb.append("return fullActivityName;\n");
            sb.append("}\n");
        }
        sb.append("return null;\n");
        sb.append("}\n");
        sb.append("}\n");
        return sb.toString();
    }
}
