import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;

public class TestHomeWork {
    public static void main(String[] args) throws Exception {
        TestHomeWork test = new TestHomeWork();
        test.test();
    }

    public static void test() throws Exception {
        File file = new File("./src/dz");
        String[] fileList = file.list();
        ArrayList<String> fileName = new ArrayList<>();

        for (String f: fileList){
            System.out.println(f);
            String[] ext = f.split("\\.");
            if (ext[1].equalsIgnoreCase("class")){
                fileName.add(ext[0]);
                System.out.println(fileName);
            }
        }

        Iterator iterator = fileName.iterator();

        while(iterator.hasNext()){
            String name = String.valueOf(iterator.next());
            Class cl = URLClassLoader.newInstance(new URL[]{new File("./src/dz").toURL()}).loadClass(name);
            Constructor constr = cl.getConstructor();
            Object runTest = constr.newInstance();
            Method m = cl.getDeclaredMethod("start1",int.class);
            int res = (Integer) m.invoke(runTest, 12);
            System.out.println(res);

        }

    }
}
