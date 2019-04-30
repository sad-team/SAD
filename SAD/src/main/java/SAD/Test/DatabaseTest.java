package SAD.Test;

import SAD.Database.DataOperation;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DatabaseTest {
    private static DataOperation dataoperator;
    static{
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_config.xml");
        dataoperator=(DataOperation) context.getBean("dataoperator");
    }
    @Test
    public void selectUserRoleTest(){
        for(int i=1;i<=10;i++) {
            System.out.println(dataoperator.selectUserRole(i));
        }
    }
    @Test
    public void newPasswdTest(){
        System.out.println(dataoperator.newPasswd(1,"333","323"));
        System.out.println(dataoperator.newPasswd(8,"333","323"));
        System.out.println(dataoperator.newPasswd(14,"23323","323"));
        System.out.println(dataoperator.newPasswd(11,"11111","323"));
    }
    @Test
    public void newUserTest(){
        System.out.println(dataoperator.newUser("ZYY","1111311r1yy11","69687209000","dsjajdhhfkjhaf","6s@sss"));
        System.out.println(dataoperator.newUser("BYY","3415r46yy623543","37714","dsjajdhhfkjhaf","s7s@sss"));
        System.out.println(dataoperator.newUser("XYY","11r66yy1","43423477144","dsjajdhhfkjhaf","s0s@sss"));
        System.out.println(dataoperator.newUser("DYY","sdryy","00000178200","dsjajdhhfkjhaf","sss@sss"));
    }

    @Test
    public void loginTest(){
        System.out.println(dataoperator.login("dd","3223"));
    }
}
