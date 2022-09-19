public class classTests {

    @BeforeSuite
    public void Test1(){
        System.out.println("testBefore");
    }
    @Test(priority = 2)
    public void Test3(){
        System.out.println("TestPr2");
    }
    @Test(priority = 1)
    public void Test56(){
        System.out.println("TestPr1");
    }
    @Test(priority = 2)
    public void Test99(){
        System.out.println("TestPr2");
    }
    @AfterSuite
    public void Test2(){
        System.out.println("testAfter");
    }
    /*@AfterSuite
    public void Test10(){
        System.out.println("testBefore");
    }*/
    @Test(priority = 5)
    private void Test4(){
        System.out.println("TestPr5");
    }
    @Test(priority = 10)
    public void Test21(){
        System.out.println("TestPr10");
    }
    @Test(priority = 3)
    public void Test5(){
        System.out.println("TestPr3");
    }
    @Test(priority = 10)
    public void Test20(){
        System.out.println("TestPr10");
    }
}
