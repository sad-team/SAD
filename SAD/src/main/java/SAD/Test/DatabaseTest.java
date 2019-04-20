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
}
