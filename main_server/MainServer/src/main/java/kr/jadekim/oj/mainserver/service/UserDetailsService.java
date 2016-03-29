package kr.jadekim.oj.mainserver.service;


import kr.jadekim.oj.mainserver.entity.CurrentUser;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

/**
 * Created by ohyongtaek on 2016. 3. 24..
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

    private final UserService userService;

    @Autowired
    UserRepository userRepository;

    public UserDetailsService(){
        this.userService = null;
    }

    public UserDetailsService(UserService userService){
        this.userService = userService;
    }

    public CurrentUser login(String login_id,String login_pw) throws UsernameNotFoundException {
        User user;
        try {
            user = userService.login(login_id,login_pw).get();
            return new CurrentUser(user);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new CurrentUser(null);
    }

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getByLoginId(username).orElseThrow(()-> new UsernameNotFoundException(String.format("User with login_id=%s was not found",username)));
        return new CurrentUser(user);
    }
}
