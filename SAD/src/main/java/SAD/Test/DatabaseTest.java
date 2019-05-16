package SAD.Test;

import SAD.Database.DataOperation;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseTest {
    private static DataOperation dataoperator;
    static{
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_config.xml");
        dataoperator=(DataOperation) context.getBean("dataoperator");
    }

    @Test
    public void selectUserRoleTest(){
        for(int i=0;i<100;i++) {
            System.out.println(dataoperator.selectUserRole(i)+"    ");
        }
    }
    @Test
    public void newPasswdTest(){
        System.out.println(dataoperator.newPasswd(1,"323","332"));
    }
    @Test
    public void searchResourceTest(){
        System.out.println(dataoperator.searchResource("人工","ALL"));
    }
    @Test
    public void getUserOrderTest(){
        System.out.println(dataoperator.getUserOrder(1));
    }
    @Test
    public void followUserTest(){
        System.out.println(dataoperator.followUser(6,7));
    }
    @Test
    public void unFollowUserTest(){
        System.out.println(dataoperator.unFollowUser(6,7));
    }
    @Test
    public void sendMessageTest(){
        System.out.println(dataoperator.sendMessage(6,7,"this is a test",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
    }
    @Test
    public void getMessageTest(){
        System.out.println(dataoperator.getMessage(7));
    }
}
