package com.zjazn.uaa.controller;

import com.alibaba.fastjson.JSON;
import com.zjazn.uaa.common.R;
import com.zjazn.uaa.dao.UserDao;
import com.zjazn.uaa.dao.UserLineRoleDao;
import com.zjazn.uaa.model.User;
import com.zjazn.uaa.model.UserLineRole;
import com.zjazn.uaa.model.wo.wxUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@Slf4j
public class LoginController {
    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.secret}")
    private String secret;

    @Value("${server.port}")
    private String port;
    @Resource
    private RestTemplate restTemplate;


    @Resource
    private UserDao userDao;

    @Resource
    private UserLineRoleDao userLineRoleDao;

    @PostMapping("wxLogin")
//    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public R wxLogin(String code) {
        if(code == null) {
            return R.error().message("后端没有收到code或为null");
        }
        Object tokenObject = null ;
        log.info("************获取前端的授权码："+code);
        //1、获取openid
        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + appid +
                "&secret=" + secret +
                "&js_code=" + code +
                "&grant_type=authorization_code";
        log.info("*************url="+url);
        String wxu = restTemplate.getForObject(url, String.class);
        wxUser wxUser = JSON.parseObject(wxu, wxUser.class);
        log.info("************向微信指定API获取了用户信息："+wxUser.getOpenid());

        //2、根据openid查询数据库中是否存在，如果不存在进行注册
        User user = userDao.getUserByWxOpid(wxUser.getOpenid());
        log.info("***********根据Opid查询的结果是:"+user);
        Boolean isinit = false;
        if(user == null) {
            isinit = true;
            //执行注册
            Date date = new Date();
            User newUser = new User();
            newUser.setId(userDao.countUser()+1+"");
            newUser.setCreateTime(date);
            newUser.setUpdateTime(date);
            newUser.setWx_openid(wxUser.getOpenid());
            newUser.setPassword(new BCryptPasswordEncoder().encode("undefined"));
            newUser.setUsername(wxUser.getOpenid());
            newUser.setFullname("微信用户");
            newUser.setPersonality("一个小可爱！");
            int b = userDao.createUser(newUser);
            if(b <= 0) {
                return R.error().message("登录失败！");
            }
//            int i=1/0;
            User credUser = userDao.getUserByWxOpid(wxUser.getOpenid());
            //创建与角色的关联表记录
            UserLineRole userLineRole = new UserLineRole();
            userLineRole.setUser_id(credUser.getId());
            userLineRole.setRole_id("3");
            userLineRole.setCreate_time(new Date());
            userLineRole.setCreator("UAA服务器");
            int line = userLineRoleDao.createLine(userLineRole);
            if(line <= 0) {
                return R.error().message("登录失败！");
            }



        }
        User credUser = userDao.getUserByWxOpid(wxUser.getOpenid());
        log.info("************查出有Openid的用户userDao="+credUser);
        //请求token，返回
        tokenObject = restTemplate.postForObject("http://127.0.0.1:"+port+"/uaa/oauth/token?client_id=c1&client_secret=secret&grant_type=password&username="+credUser.getUsername()+"&password=undefined" ,null, Object.class);
        //3、根据openid获取用户账号密码获取token


        //4、返回token到客户端
        return R.ok().data("tokenData",tokenObject).data("userData",credUser).data("isinit",isinit);
    }
}
