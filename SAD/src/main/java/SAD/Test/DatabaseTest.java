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
    public void searchResourceTest(){
        System.out.println(dataoperator.searchResource("人工","ALL"));
    }
}
