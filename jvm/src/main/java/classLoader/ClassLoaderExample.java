package classLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderExample {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {


        MyClassLoader myClassLoader = new MyClassLoader("/Users/huazhen/Documents/coding/test1/");
        Class<?> aClass = myClassLoader.findClass("classLoader.User1");
        Object o = aClass.newInstance();
        Method printName = aClass.getDeclaredMethod("printName", null);
        printName.invoke(o, null);
        System.out.println(aClass.getClassLoader());


        System.out.println("------------------------------------------------------------------------------------------");


        MyClassLoader myClassLoader1 = new MyClassLoader("/Users/huazhen/Documents/coding/test/");
        Class<?> aClass1 = myClassLoader1.findClass("classLoader.User");
        Object o1 = aClass1.newInstance();
        Method printName1 = aClass1.getDeclaredMethod("printName", null);
        printName1.invoke(o1, null);
        System.out.println(aClass1.getClassLoader());

    }
}
