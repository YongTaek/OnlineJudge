package kr.jadekim.oj.mainserver.service;

import kr.jadekim.oj.mainserver.entity.Board;
import kr.jadekim.oj.mainserver.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cheonyujung on 2016. 9. 12..
 */
@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    public void createBoard() {
        if(boardRepository.count() == 0){
            boardRepository.save(new Board("notice"));
            boardRepository.save(new Board("question"));
        }
    }
}
