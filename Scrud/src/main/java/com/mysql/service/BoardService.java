package com.mysql.service;

import java.util.List;

import com.mysql.domain.BoardVO;

public interface BoardService {

	public void create(BoardVO vo) throws Exception;
	public List<BoardVO> listAll() throws Exception;
	public BoardVO read(Integer b_no) throws Exception;
	public void delete(Integer b_no) throws Exception;
	public void update(BoardVO vo) throws Exception;
	
}
