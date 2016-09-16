package kr.jadekim.oj.mainserver.service;


import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public Future<User> join(String login_id, String login_pw, String name, String email) {
        String password = bCryptPasswordEncoder.encode(login_pw);
        User user = new User(login_id, password, name, email);

        return new AsyncResult<User>(userRepository.save(user));
    }

    @Async
    public Future<User> login(String login_id, String login_pw) {
        User user = userRepository.findByloginId(login_id).get(0);
        if (user == null)
            return null;
        if (bCryptPasswordEncoder.matches(login_pw, user.getloginPw())) {
            return new AsyncResult<>(user);
        } else {
            return null;
        }
    }

    @Async
    public Future<User> findUser(String login_id){
        User user = userRepository.findByloginId(login_id).get(0);
        if(user == null){
            System.out.println(login_id+"!!!");
            return null;
        }else{
            return new AsyncResult<>(user);
        }
    }

    public Optional<User> getUserByLoginId(String login_id){
        Optional<User> user = userRepository.getByLoginId(login_id);
        return user;
    }

    @Async
    public void saveUser(User user){
        userRepository.save(user);
    }

    public User changeInfo(String email,String login_pw,User user){
        user.setloginPw(bCryptPasswordEncoder.encode(login_pw));
        user.setEmail(email);
        userRepository.save(user);
        return user;
    }

    @Async
    public Future<List<User>> findUserOrderBySuccessCount(Pageable pageable) {
        return new AsyncResult<>(userRepository.findUserOrderBySuccessCount(pageable));
    }

    @Async
    public Future<List<User>> findAll(Pageable pageable) {
        return new AsyncResult<>(userRepository.findAll(pageable).getContent());
    }
}