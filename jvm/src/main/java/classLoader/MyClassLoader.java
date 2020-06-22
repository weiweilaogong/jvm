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
        FileInputStream fis = new FileInputStream(classPath + name + ".class");
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
}
