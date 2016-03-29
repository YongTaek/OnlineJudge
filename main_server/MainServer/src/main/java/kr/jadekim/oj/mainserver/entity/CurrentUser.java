package kr.jadekim.oj.mainserver.entity;

import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Created by ohyongtaek on 2016. 3. 24..
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    User user;

    public CurrentUser(User user) {
        super(user.getLoginId(),user.getLoginPw(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Role getRole(){
        return user.getRole();
    }
}
