package cn.footman.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author footman77
 * @create 2018-10-15 23:42
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.inMemoryAuthentication().withUser("zhangsan").password("123").roles("VIP1","VIP2")
                .and()
                .withUser("lisi").password("123").roles("VIP2","VIP3")
                .and()
                .withUser("wangwu").password("123").roles("VIP1","VIP3");


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        //定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        //开启自动配置登陆功能，如果没有权限，就会到登陆页面
        http.formLogin().usernameParameter("user").passwordParameter("pwd")
                .loginPage("/userlogin");
        //1、/login来到登陆页面
        //2、重定向到/login?error表示登陆失败
        //3、默认post形式的/login表示处理登陆
        //4、一旦定制loginPage;那么loginPage的post方式就是登陆处理逻辑


        //开启自动配置的注销功能
        http.logout().logoutSuccessUrl("/");//注销成功返回首页
        //1、访问/logout，清空session
        //2、注销成功返回/login?logout


        //开启记住我
        http.rememberMe().rememberMeParameter("remember");
        //登陆成功后，将cookie发给浏览器，以后登陆会带上这个cookie，登陆检查就可以免登陆
        //点击注销，就会删除cookie

    }
}
