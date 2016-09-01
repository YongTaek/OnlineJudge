package kr.jadekim.oj.mainserver.service;

import kr.jadekim.oj.mainserver.entity.Group;
import kr.jadekim.oj.mainserver.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Created by ohyongtaek on 2016. 8. 28..
 */
@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Async
    public Future<Group> findOne(int id) {
        return new AsyncResult<>(groupRepository.getOne(id));
    }

    @Async
    public void save(Group group) {
        groupRepository.save(group);
    }

    @Async
    public Future<Group> findByUserId(int user_id) {
        return new AsyncResult<>(groupRepository.findGroupByUserId(user_id));
    }
}
