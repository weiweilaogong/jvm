package classLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderExample {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {


        MyClassLoader myClassLoader = new MyClassLoader("/Users/huazhen/Documents/coding/xiaoshou/jvm/target/classes/");

        Class<?> aClass = myClassLoader.findClass("classLoader.User");
        Object o = aClass.newInstance();
        Method printName = aClass.getDeclaredMethod("printName", null);

        Object invoke = printName.invoke(o, null);
        System.out.println(aClass.getClassLoader());
    }
}
