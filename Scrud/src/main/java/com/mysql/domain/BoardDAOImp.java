package com.mysql.domain;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mysql.dao.BoardDAO;

@Repository
public class BoardDAOImp implements BoardDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static String namespace= "com.mysql.mappers.BoardMapper";
	
	//게시판 추가
	@Override
	public void create(BoardVO vo) throws Exception {
		sqlSession.insert(namespace+".insertBoard",vo);

	}

	//게시판 보기
	@Override
	public List<BoardVO> listAll() throws Exception {
		sqlSession.selectList(namespace+".listBoard");
		return null;
	}
	
	//게시판 상세
	@Override
	public BoardVO read(Integer b_no) throws Exception {
		sqlSession.selectOne(namespace+".detailBoard",b_no);
		return null;
	}

	//게시판 삭제
	@Override
	public void delete(Integer b_no) throws Exception {
		sqlSession.delete(namespace+".deleteBoard",b_no);
	}

	//게시판 수정
	@Override
	public void update(BoardVO vo) throws Exception {
		sqlSession.update(namespace+".updateBoard",vo);

	}

}
