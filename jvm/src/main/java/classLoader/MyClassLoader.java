package classLoader;

import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {


    private String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    public byte[] loadByte(String name) throws IOException {

        if (name != null) {
            name = name.replaceAll("\\.", "/");
        }
        String path = classPath + name + ".class";
        System.out.println(path);
        FileInputStream fis = new FileInputStream(path);
        int length = fis.available();

        byte[] bytes = new byte[length];
        fis.read(bytes);
        fis.close();
        return bytes;
    }

    protected Class<?> findClass(String name) {
        Class<?> c = null;
        try {
            byte[] bytes = loadByte(name);
            c = defineClass(name, bytes, 0, bytes.length);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                if(name.startsWith("classLoader")){//如果类全限定名称为 自定义包名
                    c = findClass(name);
                }else{
                    c = this.getParent().loadClass(name);
                }
                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    c = findClass(name);

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }
}
