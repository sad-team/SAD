package SAD.Interfaces;

import com.alibaba.fastjson.JSONObject;
import org.javamoney.moneta.Money;

import java.net.URL;

/**
 * 该接口定义后端所有对外的接口
 * @author HangLi
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
    int normalSign(String username, String identification, String cellphonenumber, String passwd, String passwd2, String email);

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
    int expertSign(String username, String identification, String cellphonenumber, String passwd, String passwd2, String email, String englishname, String academicid, String technicalfield);

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
    int newUserInfo(String username, String identification, String cellphonenumber, String passwd, String passwd2, String email, String englishname, String academicid, String technicalfield);

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
    int becomeExpert(String englishname, String academicid, String technicalfield);

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
    URL payRead(int resourceid);
}