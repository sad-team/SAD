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
        System.out.println(dataoperator.newUser("first","1111111111","0000000000","dsjajdhhfkjhaf","sss@sss"));
        System.out.println(dataoperator.newUser("dd","34154523543","314","dsjajdhhfkjhaf","sss@sss"));
        System.out.println(dataoperator.newUser("second","111","434234144","dsjajdhhfkjhaf","sss@sss"));
        System.out.println(dataoperator.newUser("third","sdadsadw","0000000000","dsjajdhhfkjhaf","sss@sss"));
    }
}
