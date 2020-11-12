package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;

@Service
// 해당 클래스가 비즈니스 로직을 담당하는 서비스 클래스 라는걸 의미
public class BoardServicelmpl implements BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	// @Autowired를 이용해서 BoardMapper 인터페이스 Bean을 주입

	@Override
	public boolean registerBoard(BoardDTO params) {
		int queryResult = 0;
		
		if(params.getIdx() ==null) {
				queryResult = boardMapper.insertBoard(params);
		}else {
				queryResult = boardMapper.updataBoard(params);
		}
		return (queryResult ==1)? true : false;
	}

	@Override
	public BoardDTO getBoardDetail(Long idx) {
		return boardMapper.selectBoardDetail(idx);
	}

	@Override
	public boolean deleteBoard(Long idx) {
		int queryResult = 0;
		BoardDTO board = boardMapper.selectBoardDetail(idx);
		if(board != null && "N".equals(board.getDeleteYn())) {
			queryResult = boardMapper.deleteBoard(idx);
		}
		return (queryResult == 1)? true : false;
	}

	@Override
	public List<BoardDTO> getBoardList() {
		List<BoardDTO> boardList = Collections.emptyList();
		int boardTotalCount = boardMapper.selectBoardTotalCount();
		if(boardTotalCount > 0) {
			boardList = boardMapper.selectBoardList();
		}
		return boardList;
	}

}
