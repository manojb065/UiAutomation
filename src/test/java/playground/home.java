package playground;

import org.testng.annotations.Test;

public class home {
    @Test(priority = 0,groups = "home")
    public void test1(){
        System.out.println("home 1");
    }

    @Test(priority = 1,groups = "home")
    public void test2(){
        System.out.println("home 2");
    }

    @Test(priority = 2,groups = "home")
    public void test3(){
        System.out.println("home 3");
    }

    @Test(priority = 3,groups = "home")
    public void test4(){
        System.out.println("home 4");
    }
}
