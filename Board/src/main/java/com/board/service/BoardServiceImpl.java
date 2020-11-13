package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;

@Service
// Mapper와 유사, 해당 클래스가 비즈니스 로직을 담당하는 서비스 클래스임을 의미
public class BoardServiceImpl implements BoardService {

	@Autowired
	//BoardMapper 인터페이스 Bean을 주입
	private BoardMapper boardMapper;

	@Override
	public boolean registerBoard(BoardDTO params) {
		// insert 와 update 처리
		int queryResult = 0; 
		// insertBoard 또는 updateBoard 메서드의 실행결과 저장 변수
		// 쿼리가 정살적으로 실행되면, 쿼리를 실행한 횟수 1이 저장됨!

		if (params.getIdx() == null) {
			// params의 idx가 널이면,mysql의 auto_increment 속성에의해
			// PK(idx)가 자동 증가되어 게시글을 생성
			queryResult = boardMapper.insertBoard(params);
		} else {
			queryResult = boardMapper.updateBoard(params);
			// idx가 포함되어 있으면 게시글을 수정합니다.
		}

		return (queryResult == 1) ? true : false;
		// 쿼리의 실행결과 기준 true/false 반환
	}

	@Override
	public BoardDTO getBoardDetail(Long idx) {
		// 하나의 게시글을 조회하는 메서드의 결과값 반환
		return boardMapper.selectBoardDetail(idx);
	}

	@Override
	public boolean deleteBoard(Long idx) {
		int queryResult = 0;

		BoardDTO board = boardMapper.selectBoardDetail(idx);

		if (board != null && "N".equals(board.getDeleteYn())) {
			queryResult = boardMapper.deleteBoard(idx);
			// 삭제 여부(delete_yn)컬럼의 상태 값이 'Y'인 경우에는 삭제하지않고,
			// 'N'인 경우 삭제 처리
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<BoardDTO> getBoardList() {
		List<BoardDTO> boardList = Collections.emptyList();
		//NPE 방지를 위해 클래스의 Collection 클래스의 emptyList 메서드를 이용해서
		// 비어있는 리스트 선언
		// NPE란 NullPointerException의 줄임말, null 값을 가진 객체를 참조하려고 했을때
		// 일어나는 Exception 이다
		
		int boardTotalCount = boardMapper.selectBoardTotalCount();
		// 사용 중인 전체 게시글 수를 카운팅 한 결과를 저장

		if (boardTotalCount > 0) {
			boardList = boardMapper.selectBoardList();
		}
		// 사용 중인 전체 게시글이 1개 이상이면
		// boardList에 selectBoardList 메서드의 결과값 반환

		return boardList;
	}

}
