package seu.com.androidlearn;

/**
 * Created by wuxiangyu on 2017/6/14.
 */

public class StaticTest {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getSingleton();
        System.out.println("Count1: "+ singleton.counter1);
        System.out.println("Count2: "+ singleton.counter2);
    }
}

class Singleton {
    private static Singleton singleton = new Singleton();
    public static int counter1;
    public static int counter2 = 0;
    private Singleton() {
        counter1++;
        counter2++;
    }
    public static Singleton getSingleton() {
        return singleton;
    }
}