package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.BoardDTO;

@Mapper
// 기존 스프링 DAO 클래스에 @Repository를 선언해서 데이터베이스와 통신하는 클래스임을 나타냄
// 하지만 마이바티스는 인터페이스에 @Mapper만 지정해주면 XML Mapper에서 메서드의 이름과
// 일치하는 sql문을  찾아 실행 ==> Mapper영역은 데이터베이스와의 통신 sql쿼리를 호출하는 역할
public interface BoardMapper {
	public int insertBoard(BoardDTO parame); // params에 게시글의 정보가 담김
	public BoardDTO selectBoardDetail(Long idx);
	public int updataBoard(BoardDTO params);
	public int deleteBoard(Long idx);
	public List<BoardDTO> selectBoardList();
	public int selectBoardTotalCount();
}
