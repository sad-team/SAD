package SAD.Test;

import SAD.Database.DAO4MyBatis;
import SAD.Database.DataOperation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DatabaseTest {
    public static DataOperation dataoperator;
    static{
        dataoperator=DataOperation.getOperator();
    }

    @Test
    public void selectUserRoleTest(){
        for(int i=0;i<400;i++) {
            System.out.println(dataoperator.selectUserRole(i)+"    ");
        }
    }
    @Test
    public void newPasswdTest(){
        System.out.println(dataoperator.newPasswd(1,"323","332"));
    }
    @Test
    public void searchResourceTest(){
        System.out.println(dataoperator.searchResource("人工智能","PAPER").size());
    }
    @Test
    public void searchResourceSolrTest(){
        System.out.println(dataoperator.searchResourceSolr("人工智能","ALL").size());
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
    @Test
    public void sellPaperTest(){
        System.out.println(dataoperator.sellPaper(1,2,10,(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())));
    }
    @Test
    public void resourceDetailTest(){
        System.out.println(dataoperator.resourceDetail(2));
        System.out.println(dataoperator.resourceDetail(1));
    }
    @Test
    public void getCommentTest(){
        System.out.println(dataoperator.getComment(1));
    }
    @Test
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public void testRead(){
        SqlSessionFactory sessionFactory=(SqlSessionFactory) new ClassPathXmlApplicationContext("spring_config.xml").getBean("sqlsessionFactory");
        SqlSession session1=sessionFactory.openSession();
        SqlSession session2=sessionFactory.openSession();
        DAO4MyBatis mapper1=session1.getMapper(DAO4MyBatis.class);
        DAO4MyBatis mapper2=session2.getMapper(DAO4MyBatis.class);
        List a=mapper1.selectTest();
        session1.close();
        List b=mapper2.selectTest();
        System.out.println(dataoperator.resourceDetail(2));
        System.out.println(dataoperator.resourceDetail(1));
        List c=mapper2.selectTest();
        System.out.println(a==b);
        session2.close();
    }
    @Test
    public void uploadResourceTest(){
        System.out.println(dataoperator.uploadResource(12,122,"aaaa","http",1));
    }
    @Test
    public void transferResourceTest(){
        System.out.println(dataoperator.transferResource(1,6,1091,122,"1111-11-11 11:11:11"));
    }
    @Test
    public void insertSolrTest(){
        HashMap<String,Object> map=new HashMap<String, Object>();
        map.put("selectResourceApprovingDepartment",1);
        map.put("selectResourceEndDate","2015-12-08");
        map.put("selectResourceTitle","人工try");
        map.put("selectResourceOwnerID",1);
        map.put("selectResourceURL","test.pdf");
        map.put("selectResourceDownloadPrice",1);
        map.put("selectResourcePurchaseQuantity",1);
        map.put("selectResourceFunds",1);
        map.put("selectResourceID",1);
        map.put("selectResourceStartDate","2015-12-08");
        map.put("selectResourceTransferPrice",1);
        dataoperator.insertSolr(map);
    }
    @Test
    public void initPaperTest(){
        System.out.println(dataoperator.initPaper(1083,"dddd","dsajs","sdad","2018"));
    }
}
