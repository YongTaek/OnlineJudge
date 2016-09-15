package kr.jadekim.oj.mainserver.util;

import kr.jadekim.oj.mainserver.service.BoardService;

/**
 * Created by cheonyujung on 2016. 9. 12..
 */
public class InitDB {

    BoardService boardService;

    public void initDB(){
        boardService.createBoard();
    }
}
