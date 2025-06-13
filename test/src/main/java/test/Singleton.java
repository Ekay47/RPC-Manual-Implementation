package test;

// 1st - enum
//public enum Singleton {
//    INSTANCE;
//    private void doSth(){}
//}

// 2nd way - static inner class
//public class Singleton {
//    private static Singleton instance;
//    private Singleton(){}
//
//    public static Singleton getInstance(){
//        if(instance == null){
//            return SingletonInner.INSTANCE;
//        }
//    }
//
//    private static class SingletonInner{
//        private final static Singleton INSTANCE = new Singleton();
//    }
//}

// 3rd way
public class Singleton {
    private static volatile Singleton uniqueInstance;
    private Singleton(){}
    public static Singleton getInstance(){
        if(uniqueInstance==null){
            synchronized (Singleton.class){
                if(uniqueInstance==null){
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}

