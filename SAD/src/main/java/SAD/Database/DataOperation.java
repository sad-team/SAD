package SAD.Database;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.jvm.hotspot.utilities.soql.SOQLException;

import javax.naming.Context;
import javax.swing.text.StringContent;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataOperation{
    private SAD.Database.DAO4MyBatis daoMapper;
    private static DataOperation dataOperator;
    private static ApplicationContext context;
    private DataOperation(){}
    public void setDaoMapper(SAD.Database.DAO4MyBatis daomapper){
        this.daoMapper=daomapper;
    }
    public static DataOperation getOperator(){
        if(context==null){
            context=new ClassPathXmlApplicationContext("spring_config.xml");
        }
        if(dataOperator==null){
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
        try {
            if (ifUserExistID(userid)) {
                if (daoMapper.ifExpertExist(userid)) {
                    return 1;
                } else {
                    if (daoMapper.ifAdminExist(userid)) {
                        return 2;
                    } else {
                        return 0;
                    }
                }
            } else {
                return -1;
            }
        }catch(IOException e){
            return -2;
        }catch(SolrServerException e){
            return -3;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public List<Map<String,Object>> getComment(int resourceid){
        if(!daoMapper.ifResourceExsit(resourceid)) return null;
        return daoMapper.getComment(resourceid);
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int userAuthority(int userid, String passwd){
        try {
            if (!ifUserExistID(userid)) {
                return -1;
            } else if (!daoMapper.selectUserAuthentication(userid, passwdSHA(passwd))) {
                return -2;
            } else {
                return 0;
            }
        }catch(IOException e){
            return -3;
        }catch (SolrServerException e){
            return -4;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int uploadResource(int downloadprice, int transferprice, String title, String url, int ownerid){
        try {
            if (downloadprice < 0) return -1;
            if (transferprice < 0) return -2;
            if (!ifUserExistID(ownerid)) return -3;
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("id", 1);
            daoMapper.insertResource(map, downloadprice, transferprice, title, url, ownerid);
            return map.get("id");
        }catch(IOException e){
            return -4;
        }catch(SolrServerException e){
            return -5;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int transferResource(int oldowner, int newowner, int resourceid, int point,String time){
        try {
            if (!ifUserExistID(oldowner)) return -1;
            if (!ifUserExistID(newowner)) return -2;
            if (!daoMapper.ifResourceExsit(resourceid)) return -3;
            if (point < 0) return -4;
            if (daoMapper.selectMoney(newowner) < point) return -5;
            if ((Integer) (daoMapper.selectResourceDetails(resourceid).get(0).get("ownerId")) != oldowner) return -6;
            if ((Integer) (daoMapper.selectResourceDetails(resourceid).get(0).get("transferPrice")) != point) return -7;
            daoMapper.writeOrder(newowner, resourceid, time, 1);
            daoMapper.updateOwner(resourceid, newowner);
            daoMapper.updateMoney(oldowner, point);
            daoMapper.updateMoney(newowner, -point);
            return 0;
        }catch (IOException e){
            return -8;
        }catch(SolrServerException e){
            return -9;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int initPaper(int id,String brief,String from, String author, String issuedtime){
        if(!daoMapper.ifResourceExsit(id)) return -1;
        daoMapper.insertPaper(id,brief,from,author,issuedtime);
        return 0;
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
        try {
            if (!ifUserExistID(userid)) {
                return -2;
            } else if (!daoMapper.selectUserAuthentication(userid, passwdSHA(oldpasswd))) {
                return -1;
            } else {
                daoMapper.updateUserPasswd(userid, passwdSHA(newpasswd));
                return 0;
            }
        }catch(IOException e){
            return -3;
        }catch (SolrServerException e){
            return -4;
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
        try {
            if (!ifUserExistID(userid)) {
                return null;
            } else {
                return daoMapper.selectUserOrder(userid);
            }
        }catch(IOException e){
            return null;
        }catch (SolrServerException e){
            return null;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int changeEmail(int userid, String email){
        try {
            if (!ifUserExistID(userid)) {
                return -1;
            } else if (!isEmail(email)) {
                return -2;
            } else {
                daoMapper.updateEmail(userid, email);
                return 0;
            }
        }catch(IOException e){
            return -3;
        }catch(SolrServerException e){
            return -4;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int changeUserPhone(int userid, String newphone){
        try {
            if (!ifUserExistID(userid)) {
                return -1;
            } else {
                daoMapper.updatePhone(userid, newphone);
                return 0;
            }
        }catch(IOException e){
            return -2;
        }catch(SolrServerException e){
            return -3;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int getPoint(int userid){
        try {
            if (!ifUserExistID(userid)) {
                return -1;
            } else {
                return daoMapper.selectPoint(userid);
            }
        }catch(IOException e){
            return -2;
        }catch(SolrServerException e){
            return -3;
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
        try {
            if (!ifUserExistID(userid)) {
                return null;
            } else {
                return daoMapper.selectUserInfo(userid);
            }
        }catch(IOException e){
            return null;
        }catch(SolrServerException e){
            return null;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public List<Map<String,Object>> showUserResource(int userid){
        try {
            if (!ifUserExistID(userid)) {
                return null;
            } else {
                return daoMapper.selectUserResource(userid);
            }
        }catch(IOException e){
            return null;
        }catch(SolrServerException e){
            return null;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int followUser(int userid, int followid){
        try {
            if (!ifUserExistID(userid)) return -1;
            else if (!ifUserExistID(followid)) return -2;
            else if (daoMapper.ifFollow(userid, followid)) return -3;
            else {
                daoMapper.makeFollow(userid, followid);
                return 0;
            }
        }catch(IOException e){
            return -4;
        }catch (SolrServerException e){
            return -5;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public List<Map<String,Object>> getFollowedUser(int userid){
        try {
            if (!ifUserExistID(userid)) return null;
            else {
                return daoMapper.selectFollowedUser(userid);
            }
        }catch(IOException e){
            return null;
        }catch (SolrServerException e){
            return null;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int unFollowUser(int userid, int followid){
        try {
            if (!ifUserExistID(userid)) return -1;
            else if (!ifUserExistID(followid)) return -2;
            else if (!daoMapper.ifFollow(userid, followid)) return -3;
            else {
                daoMapper.unmakeFollow(userid, followid);
                return 0;
            }
        }catch(IOException e){
            return -4;
        }catch(SolrServerException e){
            return -5;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int sendMessage(int from, int to, String content,String time){
        if(!ifUserExistID(from)) return -1;
        else if (!ifUserExistID(to)) return -2;
        else{
            daoMapper.insertMessage(from,to,content,time);
            return 0;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public List<Map<String,Object>> getMessage(int receiverid){
        if(!ifUserExistID(receiverid)) return null;
        else{
            return daoMapper.selectMessage(receiverid);
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int followState(int userid,int expertid){
        if(!ifUserExistID(userid)){
            return -1;
        }else if(!ifUserExistID(expertid)){
            return -2;
        }else if(daoMapper.ifFollow(userid,expertid)){
            return 1;
        }else {
            return 0;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int sellPaper(int userid,int resourceid,int points, String boughttime){
        if(!ifUserExistID(userid)) return -1;
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
        if(!ifUserExistID(userid)) return -1;
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
        if(!ifUserExistID(userid)) return -1;
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
    private boolean ifUserExistID(int userid)throws IOException,SolrServerException{
        HttpSolrClient solrClient=context.getBean(HttpSolrClient.class);
        SolrQuery sq=new SolrQuery();
        String querystring="selectUserID:"+((Integer)userid).toString();
        sq.set("q",querystring);
        try {
            QueryResponse qr = solrClient.query(sq);
            long nums = qr.getResults().getNumFound();
            if (nums != 0 && nums != 1) throw new RuntimeException();
            return nums == 1;
        }catch(IOException e){
            e.printStackTrace();
            throw e;
        }catch(SolrServerException e){
            e.printStackTrace();
            throw e;
        }finally{
            solrClient.close();
        }
    }
}
