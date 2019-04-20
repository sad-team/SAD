package SAD.Database;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public final class DataOperation {
    private static JdbcTemplate template;
    static{
        ApplicationContext context;
        context=new ClassPathXmlApplicationContext("spring_config.xml");
        template=(JdbcTemplate) context.getBean("jdbctemplate");
    }

    /**
     *获取用户角色
     *
     * @param userid 用户id
     * @return <p>角色类型</p>
     * <p>-1 不存在</p>
     * <p>0  普通用户</p>
     * <p>1 专家</p>
     * <p>2 管理员</p>
     */
    public static int selectUserRole(int userid){
        return template.queryForInt("select selectUserRole(?)",new Object[]{userid});
    }
    public static int newPasswd(int userid, String oldpasswd, String newpasswd){
        return template.queryForInt("select newPasswd(?,?,?)",new Object[]{userid,oldpasswd,newpasswd});
    }
}
