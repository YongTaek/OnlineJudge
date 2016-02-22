package kr.jadekim.oj.mainserver.service;


import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Created by ohyongtaek on 2016. 1. 22..
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Async
    public Future<User> join(String login_id, String login_pw, String name, String email){
        String password = bCryptPasswordEncoder.encode(login_pw);
        User user = new User(login_id,password,name,email);

        return new AsyncResult<User>(userRepository.save(user));
    }

    @Async
    public Future<User> login(String login_id,String login_pw){
        User user =userRepository.findByloginId(login_id).get(0);
        if(user == null)
            return null;
        if(bCryptPasswordEncoder.matches(login_pw,user.getloginPw())){
            return new AsyncResult<>(user);
        }else{
            return null;
        }
    }

    @Async
    public Future<User> findUser(String login_id){
        User user = userRepository.findByloginId(login_id).get(0);
        if(user == null){
            return null;
        }else{
            return new AsyncResult<>(user);
        }
    }
}
