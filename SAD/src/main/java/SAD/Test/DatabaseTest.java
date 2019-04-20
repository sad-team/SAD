package SAD.Test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DatabaseTest {
    @Test
    public void selectUserRoleTest(){
        for(int i=1;i<=10;i++) {
            System.out.println(SAD.Database.DataOperation.selectUserRole(i));
        }
    }
    @Test
    public void newPasswdTest(){
        System.out.println(SAD.Database.DataOperation.newPasswd(1,"11111","323"));
        System.out.println(SAD.Database.DataOperation.newPasswd(8,"231","323"));
        System.out.println(SAD.Database.DataOperation.newPasswd(14,"23323","323"));
        System.out.println(SAD.Database.DataOperation.newPasswd(11,"11111","323"));
    }
}
