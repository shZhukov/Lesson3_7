public class TestClass {
    @BeforeSuite
    public static void beforeSuite() {
        System.out.println("beforeSuite");
    }
    @Test (priority = 3)
    public static void method1() {
        System.out.println("method1 приоритет 3");
    }
    @Test(priority = 7)
    public static void method2() {
        System.out.println("method2 приоритет 7");
    }
    @Test(priority = 6)
    public static void method3() {
        System.out.println("method3 приоритет 6");
    }
    @AfterSuite
    public static void afterSuit() {
        System.out.println("afterSuit");
    }
}
