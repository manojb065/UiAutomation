package playground;

import org.testng.ITestContext;
import org.testng.annotations.Test;


public class login {

    static boolean flag = true;
    @Test(priority = 0)
    public void ltest1(ITestContext con){
        con.setAttribute("run",flag);
        flag=!flag;
        System.out.println("login 1");
    }

    @Test(priority = 1)
    public void ltest2(ITestContext con){
        System.out.println("login 2");
    }

    @Test(priority = 2)
    public void ltest3(ITestContext con){
        System.out.println("login 3");
    }

    @Test(priority = 3)
    public void ltest4(ITestContext con){
        System.out.println("run  --> "+con.getAttribute("run")+" flag --> "+flag);
        if((Boolean) con.getAttribute("run")) {
            System.out.println("login 4");
        }
    }
}
