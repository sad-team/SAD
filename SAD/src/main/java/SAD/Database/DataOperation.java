package SAD.Database;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.Context;
import javax.swing.text.StringContent;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataOperation{
    private SAD.Database.DAO4MyBatis daoMapper;
    private static DataOperation dataOperator;
    public void setDaoMapper(SAD.Database.DAO4MyBatis daomapper){
        this.daoMapper=daomapper;
    }
    public static DataOperation getOperator(){
        if(dataOperator==null){
            ApplicationContext context=new ClassPathXmlApplicationContext("spring_config.xml");
            dataOperator=(DataOperation) context.getBean("dataOperator");
        }
        return dataOperator;
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
        if (daoMapper.ifUserExistID(userid)){
            if(daoMapper.ifExpertExist(userid)){
                return 1;
            }else{
                if(daoMapper.ifAdminExist(userid)){
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
    public List<Map<String,Object>> getComment(int resourceid){
        if(!daoMapper.ifResourceExsit(resourceid)) return null;
        return daoMapper.getComment(resourceid);
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int userAuthority(int userid, String passwd){
        if(!daoMapper.ifUserExistID(userid)){
            return -1;
        }else if(!daoMapper.selectUserAuthentication(userid,passwdSHA(passwd))){
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
        if(!daoMapper.ifUserExistID(userid)){
            return -2;
        }else if(!daoMapper.selectUserAuthentication(userid,passwdSHA(oldpasswd))){
            return -1;
        }else{
            daoMapper.updateUserPasswd(userid,passwdSHA(newpasswd));
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
        if(daoMapper.ifUserExistName(username)){
            return -1;
        }else if(daoMapper.ifPhoneExist(cellphone)){
            return -3;
        }else if(daoMapper.ifIdentificationExist(identification)){
            return -2;
        }else{
            daoMapper.insertUser(username,identification,cellphone,passwdSHA(passwd),email);
            return 0;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public List<Map<String,Object>> getUserOrder(int userid){
        if(!daoMapper.ifUserExistID(userid)){
            return null;
        }else{
            return daoMapper.selectUserOrder(userid);
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int changeEmail(int userid, String email){
        if(!daoMapper.ifUserExistID(userid)){
            return -1;
        }else if(!isEmail(email)){
            return -2;
        }else{
            daoMapper.updateEmail(userid,email);
            return 0;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int changeUserPhone(int userid, String newphone){
        if(!daoMapper.ifUserExistID(userid)){
            return -1;
        }else{
            daoMapper.updatePhone(userid,newphone);
            return 0;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int getPoint(int userid){
        if(!daoMapper.ifUserExistID(userid)){
            return -1;
        }else{
            return daoMapper.selectPoint(userid);
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public List<Map<String,Object>> searchResource(String searchword, String searchtype){
        List<Map<String,Object>> result=new ArrayList();
        List<Map<String,Object>> temp;
        int state=-1;
        if(searchtype=="PAPER" || searchtype=="ALL"){
            state=0;
            temp=daoMapper.selectPaper(searchword);
            if(temp!=null){
                for(int i=0;i<temp.size();i++){
                    result.add((Map<String,Object>)temp.get(i));
                }
            }
        }
        if(searchtype=="PATENT" || searchtype=="ALL"){
            state=0;
            temp=daoMapper.selectPatent(searchword);
            if(temp!=null){
                for(int i=0;i<temp.size();i++){
                    result.add((Map<String,Object>)temp.get(i));
                }
            }
        }
        if(searchtype=="PROJECT" || searchtype=="ALL"){
            state=0;
            temp=daoMapper.selectProject(searchword);
            if(temp!=null){
                for(int i=0;i<temp.size();i++){
                    result.add((Map<String,Object>)temp.get(i));
                }
            }
        }
        if(state==-1){
            return null;
        }else{
            return result;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int getUserId(String username){
        if(!daoMapper.ifUserExistName(username)){
            return -1;
        }else{
            return daoMapper.selectUserId(username);
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public List<Map<String,Object>> getUserInfo(int userid){
        if(!daoMapper.ifUserExistID(userid)){
            return null;
        }else{
            return daoMapper.selectUserInfo(userid);
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public List<Map<String,Object>> showUserResource(int userid){
        if(!daoMapper.ifUserExistID(userid)){
            return null;
        }else{
            return daoMapper.selectUserResource(userid);
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int followUser(int userid, int followid){
        if(!daoMapper.ifUserExistID(userid)) return -1;
        else if (!daoMapper.ifUserExistID(followid)) return -2;
        else if(daoMapper.ifFollow(userid,followid)) return -3;
        else{
            daoMapper.makeFollow(userid,followid);
            return 0;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public List<Map<String,Object>> getFollowedUser(int userid){
        if(!daoMapper.ifUserExistID(userid)) return null;
        else{
            return daoMapper.selectFollowedUser(userid);
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int unFollowUser(int userid, int followid){
        if(!daoMapper.ifUserExistID(userid)) return -1;
        else if (!daoMapper.ifUserExistID(followid)) return -2;
        else if(!daoMapper.ifFollow(userid,followid)) return -3;
        else{
            daoMapper.unmakeFollow(userid,followid);
            return 0;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int sendMessage(int from, int to, String content,String time){
        if(!daoMapper.ifUserExistID(from)) return -1;
        else if (!daoMapper.ifUserExistID(to)) return -2;
        else{
            daoMapper.insertMessage(from,to,content,time);
            return 0;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public List<Map<String,Object>> getMessage(int receiverid){
        if(!daoMapper.ifUserExistID(receiverid)) return null;
        else{
            return daoMapper.selectMessage(receiverid);
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int followState(int userid,int expertid){
        if(!daoMapper.ifUserExistID(userid)){
            return -1;
        }else if(!daoMapper.ifUserExistID(expertid)){
            return -2;
        }else if(daoMapper.ifFollow(userid,expertid)){
            return 1;
        }else {
            return 0;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int sellPaper(int userid,int resourceid,int points, String boughttime){
        if(!daoMapper.ifUserExistID(userid)) return -1;
        else if(!daoMapper.ifResourceExsit(resourceid)) return -2;
        else if(points<0) return -3;
        else if(daoMapper.selectMoney(userid)<points) return -4;
        else{
            daoMapper.updateMoney(userid,-points);
            daoMapper.updateMoney(daoMapper.selectOwnerId(resourceid),points);
            daoMapper.writeOrder(userid,resourceid,boughttime,1);
            //daoMapper.updateOwner(resourceid,userid);
            return 0;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int chargeMoney(int userid,int points){
        if(!daoMapper.ifUserExistID(userid)) return -1;
        else if(points<0) return -2;
        else{
            daoMapper.updateMoney(userid,points);
            return 0;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public List<Map<String,Object>> resourceDetail(int resourceid){
        if(!daoMapper.ifResourceExsit(resourceid)) return null;
        else{
            return daoMapper.selectResourceDetails(resourceid);
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int makeComment(int resourceid,String content,String commenttime){
        if(!daoMapper.ifResourceExsit(resourceid)) return -1;
        else {
            daoMapper.insertComment(resourceid,content,commenttime);
            return 0;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int ifHasBought(int userid, int resourceid){
        if(!daoMapper.ifUserExistID(userid)) return -1;
        else if(!daoMapper.ifResourceExsit(resourceid)) return -2;
        else if(daoMapper.ifHasBought(resourceid,userid))return 1;
        else if(!daoMapper.ifHasBought(resourceid,userid)) return 0;
        assert(false);
        return -3;
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
}
