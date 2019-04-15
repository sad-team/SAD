package SAD.Interfaces;

import com.alibaba.fastjson.JSONObject;
import org.javamoney.moneta.Money;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import java.net.URL;
import java.sql.Time;
import java.sql.Date;

/**
 * 该接口定义后端所有对外的接口
 * @author HangLi & BingxinZhuang
 */

public interface Background {
    /**
     * 登陆
     *
     * @param username 用户名明文
     * @param passwd   密码之sha256
     * @return <p>是否成功登陆，返回值有</p>
     * <p>0 成功</p>
     * <p>1 已经有登陆信息</p>
     * <p>2 用户不存在</p>
     * <p>3 密码错误</p>
     */
    int loginIn(String username, String passwd);
    
    /**
     * 试图找回密码，重置为临时密码并发送到邮件
     *
     * @param username 用户名明文
     * @param email    注册邮箱
     * @return <p>是否找回成功，返回值</p>
     * <p>0 成功</p>
     * <p>1 已经有登陆信息</p>
     * <p>2 用户不存在</p>
     * <p>3 邮箱错误</p>
     */
    int findPasswd(String username, String email);
    
    /**
     * 修改密码
     *
     * @param oldpasswd  旧密码之sha256
     * @param newpasswd  新密码之sha256
     * @param newpasswd2 第二次新密码之sha256
     * @return <p>是否修改成功</p>
     * <p>0 成功</p>
     * <p>1 用户不存在</p>
     * <p>2 旧密码错误</p>
     * <p>3 新密码不合法</p>
     * <p>4 新密码两次输入不匹配</p>
     */
    int changePasswd(String oldpasswd, String newpasswd, String newpasswd2);
    
    /**
     * 普通用户注册
     *
     * @param username        用户名
     * @param identification  身份证信息
     * @param cellphonenumber 手机号
     * @param passwd          密码之sha256
     * @param passwd2         第二次密码之sha256
     * @param email           用户邮箱
     * @return <p>是否注册成功</p>
     * <p>0 成功</p>
     * <p>1 用户名已存在</p>
     * <p>2 用户名不合法</p>
     * <p>3 密码不合法</p>
     * <p>4 两次密码输入不匹配</p>
     * <p>5 用户邮箱不合法</p>
     */
    int userSignUp(String username, String identification, String cellphonenumber, String passwd, String passwd2, String email);
    
    /**
     * 科技专家注册
     *
     * @param username        用户名
     * @param identification  身份证信息
     * @param cellphonenumber 手机号
     * @param passwd          密码之sha256
     * @param passwd2         第二次密码之sha256
     * @param email           用户邮箱
     * @param englishname     英文姓名
     * @param academicid      所属学科id
     * @param technicalfield  所属领域
     * @return <p>是否注册成功</p>
     * <p>0 成功</p>
     * <p>1 用户名已存在</p>
     * <p>2 用户名不合法</p>
     * <p>3 密码不合法</p>
     * <p>4 两次密码输入不匹配</p>
     * <p>5 用户邮箱不合法</p>
     */
    int expertSignUp(String username, String identification, String cellphonenumber, String passwd, String passwd2, String email, String englishname, String academicid, String technicalfield);
    
    /**
     * 修改用户信息，具体参数待定（应该不会比这个多）
     *
     * @param username        用户名
     * @param identification  身份证信息
     * @param cellphonenumber 手机号
     * @param passwd          密码之sha256
     * @param passwd2         第二次密码之sha256
     * @param email           用户邮箱
     * @param englishname     英文姓名
     * @param academicid      所属学科id
     * @param technicalfield  所属领域
     * @return <p>是否注册成功</p>
     * <p>0 成功</p>
     * <p>1 用户名已存在</p>
     * <p>2 用户名不合法</p>
     * <p>3 密码不合法</p>
     * <p>4 两次密码输入不匹配</p>
     * <p>5 用户邮箱不合法</p>
     */
    int updateUserInfo(String username, String identification, String cellphonenumber, String passwd, String passwd2, String email, String englishname, String academicid, String technicalfield);
    
    /**
     * 普通用户升级为专家
     *
     * @param englishname    英文姓名
     * @param academicid     所属学科id
     * @param technicalfield 所属领域
     * @return <p>是否注册成功</p>
     * <p>0 成功</p>
     * <p>1 不知怎么就失败了</p>
     */
    int upgradeToExpert(String englishname, String academicid, String technicalfield);
    
    /**
     * 充值
     * @param source 充值源，参见SAD.Definations.Definations.ChargeSources
     * @param money 充值金额，要求使用money类
     * @param needed 充值源所需要的认证数据，目前未知
     * @return <p>充值是否成功</p>
     * <p>0 成功</p>
     * <p>数据源认证失败</p>
     */
    int reCharge(SAD.Definations.Definations.ChargeSources source, Money money, JSONObject needed);
    
    /**
     * 付费阅览或以0元浏览免费和已付费资源
     * @param resourceid 需要浏览的资源id
     * @return <p>全文浏览URL</p>
     * <p>非null表示成功</p>
     * <p>null表示失败</p>
     */
    URL preview(int resourceid);
    
    /**
     * 上传Paper
     * @param filepart 使用request.getPart获取，注意html中的设置
     * @param downloadprice 下载价格
     * @param transferprice 转让价格
     * @param title 标题
     * @param brief 摘要
     * @param from 出处
     * @param author 作者
     * @param ownerid 拥有者
     * @param date 日期
     * @param time 时间
     * @return <p>是否上传成功</p>
     * <p>0 上传成功</p>
     * <p>1 上传出错</p>
     * <p>2 查重未通过</p>
     */
    int uploadPaper(Part filepart, Money downloadprice, Money transferprice, String title, String brief, String from, String author, int ownerid, Date date, Time time);
    
    /**
     * 上传专利
     * @param filepart 文件
     * @param downloadprice 下载价格
     * @param transferprice 转让价格
     * @param title 标题
     * @param type 类型
     * @param applicationdate 申请日期
     * @param authorizeddate 认证日期
     * @return <p>是否上传成功</p>
     * <p>0 上传成功</p>
     * <p>1 上传出错</p>
     * <p>2 查重未通过</p>
     */
    int uploadPatent(Part filepart, Money downloadprice, Money transferprice, String title, String type, Date applicationdate, Date authorizeddate);
    
    /**
     * 设置管理员
     * @param userid 用户的账号
     * @return <p>设置账号userId为管理员账号</p>
     * <p>0为设置成功</p>
     * <p>1为设置失败</p>
     */
    int setAdministrator(int userid);
    
    /**
     * 解除管理员
     * @param userid 用户账号
     * @return <p>解除管理员</p>
     * <p>0为设置成功</p>
     *  <p>1为设置失败</p>
     */
    int deleteAdministrator(int userid);
    
    /**
     * 评论
     * @param commentto 被评论的资源Id
     * @param comments 评论内容
     * @return <p>评论资源</p>
     * <p>0表示成功</p>
     * <p>1表示失败</p>
     */
    int makeComments(int commentto, String comments);
    
    /**
     * 删除评论
     * @param commentid 评论的id
     * @return <p>删除评论</p>
     * <p>0表示成功</p>
     * <p>1表示失败</p>
     */
    int deleteComments(int commentid);
    
    /**
     *
     * @param resourceid 举报资源id
     * @param reason 举报理由陈述
     * @return <p>是否成功</p>
     * <p>0表示成功</p>
     * <p>1表示失败</p>
     */
    int reportResource(int resourceid, String reason);
    
    /**
     * 用户反馈
     * @param content 反馈内容
     * @return <p>是否成功</p>
     * <p>0表示成功</p>
     * <p>1表示失败</p>
     */
    int feedback(String content);
    
    /**
     * 发送消息
     * @param userid 消息接收者id
     * @param content 消息内容
     * @return <p>是否成功</p>
     * <p>0表示成功</p>
     * <p>1表示失败</p>
     */
    int sendMessage(int userid, String content);
    
    /**
     * 专家申请自己的门户系统
     * @return 门户主页URL
     */
    URL applyForHomepage();
}
