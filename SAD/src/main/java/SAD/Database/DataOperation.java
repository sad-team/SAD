package SAD.Database;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.StringContent;
import java.security.MessageDigest;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataOperation {
    private JdbcTemplate template;
    public void setTemplate(JdbcTemplate template){
        this.template=template;
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
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int selectUserRole(int userid){
        if (ifUserExist(userid)){
            if(ifExpertExist(userid)){
                return 1;
            }else{
                if(ifAdminExist(userid)){
                    return 2;
                }else{
                    return 0;
                }
            }
        }else{
            return -1;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int userAuthority(int userid, String passwd){
        if(!ifUserExist(userid)){
            return -1;
        }else if(!selectUserAuthentication(userid,passwd)){
            return -2;
        }else{
            return 0;
        }
    }
    /**
     * 修改密码
     *
     * @param userid 用户id
     * @param oldpasswd 旧密码
     * @param newpasswd 新密码
     * @return <p>是否修改成功</p>
     * <p>0 成功</p>
     * <p>-1 密码错误</p>
     * <p>-2 用户不存在</p>
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int newPasswd(int userid, String oldpasswd, String newpasswd){
        if(!ifUserExist(userid)){
            return -2;
        }else if(!selectUserAuthentication(userid,oldpasswd)){
            return -1;
        }else{
            updateUserPasswd(userid,newpasswd);
            return 0;
        }
    }

    /**
     * 新建用户
     *
     * @param username 用户名
     * @param identification 用户身份验证信息
     * @param cellphone 电话
     * @param passwd 密码
     * @param email 邮箱
     * @return <p>是否成功</p>
     * <p>0 成功</p>
     * <p>-1 用户名重复</p>
     * <p>-2 身份信息重复</p>
     * <p>-3 电话重复</p>
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int newUser(String username, String identification, String cellphone, String passwd, String email){
        if(ifUserExist(username)){
            return -1;
        }else if(ifPhoneExist(cellphone)){
            return -3;
        }else if(ifIdentificationExist(identification)){
            return -2;
        }else{
            insertUser(username,identification,cellphone,passwd,email);
            return 0;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public List<Map<String,Object>> getUserOrder(int userid){
        if(!ifUserExist(userid)){
            return null;
        }else{
            return selectUserOrder(userid);
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int changeEmail(int userid, String email){
        if(!ifUserExist(userid)){
            return -1;
        }else if(!isEmail(email)){
            return -2;
        }else{
            updateEmail(userid,email);
            return 0;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int changeUserPhone(int userid, String newphone){
        if(!ifUserExist(userid)){
            return -1;
        }else{
            updatePhone(userid,newphone);
            return 0;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int getPoint(int userid){
        if(!ifUserExist(userid)){
            return -1;
        }else{
            return selectPoint(userid);
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public List<Map<String,Object>> searchResource(String searchword, String searchtype){
        List<Map<String,Object>> result=null;
        List temp;
        int state=-1;
        if(searchtype=="PAPER" || searchtype=="ALL"){
            state=0;
            temp=template.queryForList("select 'PAPER' as `type`, title as resourceName, author as authorName, url as resourceUrl from `resource` inner join `paper` on `paper`.id=`resource`.id where author like concat('%',?,'%') or title like concat('%',?,'%')",new Object[]{searchword,searchword});
            if(result==null){
                result=temp;
            }else{
                for(int i=0;i<temp.size();i++){
                    result.add((Map<String,Object>)temp.get(i));
                }
            }
        }
        if(searchtype=="PATENT" || searchtype=="ALL"){
            state=0;
            temp = template.queryForList("select 'PATENT' as `type`, title as resourceName, NULL as authorName, url as resourceUrl from `resource` inner join `patent` on `patent`.id=`resource`.id where title like concat('%',?,'%')",new Object[]{searchword});
            if(result==null){
                result=temp;
            }else{
                for(int i=0;i<temp.size();i++){
                    result.add((Map<String,Object>)temp.get(i));
                }
            }
        }
        if(searchtype=="PROJECT" || searchtype=="ALL"){
            state=0;
            temp = template.queryForList("select 'PROJECT' as `type`, title as resourceName, NULL as authorName, url as resourceUrl from `resource` inner join `project` on `project`.id=`resource`.id where title like concat('%',?,'%')",new Object[]{searchword});
            if(result==null){
                result=temp;
            }else{
                for(int i=0;i<temp.size();i++){
                    result.add((Map<String,Object>)temp.get(i));
                }
            }
        }
        if(state==1){
            return null;
        }else{
            return result;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int getUserId(String username){
        if(!ifUserExist(username)){
            return -1;
        }else{
            return selectUserId(username);
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public List<Map<String,Object>> getUserInfo(int userid){
        if(!ifUserExist(userid)){
            return null;
        }else{
            return selectUserInfo(userid);
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public List<Map<String,Object>> showUserResource(int userid){
        if(!ifUserExist(userid)){
            return null;
        }else{
            return selectUserResource(userid);
        }
    }
    public int followUser(int userid, int followid){
        if(!ifUserExist(userid)) return -1;
        else if (!ifUserExist(followid)) return -2;
        else if(ifFollow(userid,followid)) return -3;
        else{
            makeFollow(userid,followid);
            return 0;
        }
    }
    public List<Map<String,Object>> getFollowedUser(int userid){
        if(!ifUserExist(userid)) return null;
        else{
            return selectFollowedUser(userid);
        }
    }
    public int unFollowUser(int userid, int followid){
        if(!ifUserExist(userid)) return -1;
        else if (!ifUserExist(followid)) return -2;
        else if(!ifFollow(userid,followid)) return -3;
        else{
            unmakeFollow(userid,followid);
            return 0;
        }
    }
    public int sendMessage(int from, int to, String content,String time){
        if(!ifUserExist(from)) return -1;
        else if (!ifUserExist(to)) return -2;
        else{
            insertMessage(from,to,content,time);
            return 0;
        }
    }
    public List<Map<String,Object>> getMessage(int receiverid){
        if(!ifUserExist(receiverid)) return null;
        else{
            return selectMessage(receiverid);
        }
    }
    private List<Map<String,Object>> selectMessage(int receiverid){
        return template.queryForList("select * from `message` where `message`.`receiverId`=?",new Object[]{receiverid});
    }
    private void insertMessage(int fromuserid,int touserid,String content, String time){
        template.update("insert into `message`(`senderId`,`receiverId`,`content`,`sendTime`) values(?,?,?,?)", new Object[]{fromuserid,touserid,content,time});
    }
    private List<Map<String,Object>>selectFollowedUser(int userid){
        return template.queryForList("select `follow`.`followedId` from `follow` where `follow`.`userid`=?",new Object[]{userid});
    }
    private boolean ifFollow(int userid,int followid){
        return 1==template.queryForObject("select exists (select * from `follow` where `follow`.`userid`=? and `follow`.`followedId`=?)",new Object[]{userid,followid},Integer.class);
    }
    private void makeFollow(int userid, int followid){
        template.update("insert into `follow`(`userid`,`followedId`) values(?,?)",new Object[]{userid,followid});
    }
    private void unmakeFollow(int userid,int followid){
        template.update("delete from `follow` where userid=? and followedid=?", new Object[]{userid,followid});
    }
    private boolean ifUserExist(int userid){
        return 1==template.queryForObject("select exists(select * from `user` where `user`.`id`=?)",new Object[]{userid},Integer.class);
    }
    private boolean ifUserExist(String username){
        return 1==template.queryForObject("select exists(select * from `user` where `user`.`name`=?)",new Object[]{username},Integer.class);
    }
    private boolean ifExpertExist(int expertid){
        return 1==template.queryForObject("select exists(select * from `expert` where `expert`.`userId`=?)",new Object[]{expertid},Integer.class);
    }
    private boolean ifAdminExist(int adminid){
        return 1==template.queryForObject("select exists(select * from `administrator` where `administrator`.`userId`=?)",new Object[]{adminid}, Integer.class);
    }
    private boolean ifPhoneExist(String phone){
        return 1==template.queryForObject("select exists(select * from `user` where `user`.`cellphoneNumber`=?)",new Object[]{phone}, Integer.class);
    }
    private boolean ifIdentificationExist(String identification){
        return 1==template.queryForObject("select exists(select * from `user` where `user`.`identification`=?)",new Object[]{identification}, Integer.class);
    }
    private boolean selectUserAuthentication(int userid,String passwd){
        try {
            return 1 == template.queryForObject("select exists(select * from `user` where `user`.`id`=? and `user`.`password`=?)", new Object[]{userid, passwdSHA(passwd)}, Integer.class);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    private void updateUserPasswd(int userid, String passwd){
        try {
            template.update("update `user` set `user`.`password`=? where `user`.`id`=?", new Object[]{passwdSHA(passwd),userid});
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
    }
    private void insertUser(String name,String identification,String cellphone,String passwd,String email){
        template.update("insert into `user` (`name`,`identification`,`cellphoneNumber`,`password`,`points`,`email`) values(?,?,?,?,0,?)",new Object[]{name,identification,cellphone,passwdSHA(passwd),email});
    }
    private void updateEmail(int userid, String email){
        template.update("update `user` set `user`.`email`=? where `user`.`id`=?",new Object[]{email,userid});
    }
    private void updatePhone(int userid, String phone){
        template.update("update `user` set `user`.`cellphoneNumber`=? where `user`.`id`=?",new Object[]{phone,userid});
    }
    private String passwdSHA(String passwd) {
        try {
            byte[] result;
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(passwd.getBytes());
            result = digest.digest();
            String hs = "";
            String stmp = "";
            for (int n = 0; n < result.length; n++) {
                stmp = Integer.toHexString(result[n] & 0xFF);
                if (stmp.length() == 1)
                    hs = hs + "0" + stmp;
                else
                    hs = hs + stmp;
            }
            return hs.toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private boolean isEmail(String email){
        Pattern pattern = Pattern.compile("[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private int selectPoint(int userid){
        return template.queryForObject("select points from `user` where `user`.`id`=?",new Object[]{userid}, Integer.class);
    }
    private int selectUserId(String username){
        return template.queryForObject("select id from `user` where `user`.`name`=?",new Object[]{username}, Integer.class);
    }
    private List<Map<String,Object>> selectUserInfo(int userid){
        return template.queryForList("select `name`, `cellphoneNumber`, `email` from `user` where `user`.id=?",new Object[]{userid});
    }
    private List<Map<String,Object>> selectUserResource(int userid){
        return template.queryForList("select title as resourceName, url as resourceUrl from `resource` where `resource`.ownerId=?",new Object[]{userid});
    }
    private List<Map<String,Object>> selectUserOrder(int userid){
        return template.queryForList("select customerId as `to`, sellerId as `from`, state as state, title as resourceName, `time` as orderDate from `order` inner join resource on resource.id=`order`.resourceId where `sellerId`=? or `customerId`=?",new Object[]{userid,userid});
    }
}
