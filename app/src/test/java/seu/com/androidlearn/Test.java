package seu.com.androidlearn;

/**
 * Created by wuxiangyu on 2017/7/5.
 */

public class Test {
    public static void main(String[] args) {
        String className = "com.netease.pushclient.PushClientReceiverImpl";
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(String.format("class %s not found:%s" + className, new Object[]{e.toString()}));
        }
    }
}
