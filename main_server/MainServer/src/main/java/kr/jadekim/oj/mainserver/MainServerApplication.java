package kr.jadekim.oj.mainserver;

import kr.jadekim.oj.mainserver.entity.Team;
import kr.jadekim.oj.mainserver.repository.BoardRepository;
import kr.jadekim.oj.mainserver.service.BoardService;
import kr.jadekim.oj.mainserver.util.InitDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@SpringBootApplication
public class MainServerApplication {



    public static void main(String[] args) {

        SpringApplication.run(MainServerApplication.class, args);
        new MainServerApplication().initDB();
    }

    public void initDB(){
    }
}