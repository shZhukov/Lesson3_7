import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainClass {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessError, IllegalAccessException {
        start(TestClass.class);
    }

    // Метод для второго ДЗ Начало
    public static int start1(int a) {
        int b = 157;

        return a + b;
    }
    //-------------------

    public static void start(Class c) throws InvocationTargetException, IllegalAccessError, IllegalAccessException {
        Method[] m = c.getDeclaredMethods();
        List<Method> list = new ArrayList<>();
        for (Method i: m) {
            if(i.isAnnotationPresent(Test.class)){
                int p = i.getAnnotation(Test.class).priority();
                list.add(i);
            }
        }
        list.sort(new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                return o2.getAnnotation(Test.class).priority() - o1.getAnnotation(Test.class).priority();
            }
        });
        for (Method i: m) {
            if (i.isAnnotationPresent(BeforeSuite.class)){
                if(list.get(0).isAnnotationPresent(BeforeSuite.class))
                    throw new RuntimeException("BeforeSuite не впереди всех");
                    list.add(0,i);


            }
            if(i.isAnnotationPresent(AfterSuite.class)){
                if(list.get(list.size() -1 ).isAnnotationPresent((AfterSuite.class)))
                    throw new RuntimeException("AfterSuite не на последнем месте");
                    list.add(i);

            }
        }
        for (Method i: list) {
            i.invoke(null);
        }
    }
}
