package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardDTO;
import com.board.service.BoardService;

@Controller
// UI를 담당하는 컨트롤러 클래스 임을 의미 & 사용자의 요청과 응답 처리
public class BoardController {
	// 컨트롤러 메서드의 리턴 타입은 void,String,ModelAndView,Map,List 등
	// 여러 가지 타입을 리턴 타입으로 지정할 수 있다.
	// 대표적으로 String과 ModelAndView는 사용자에게 보여줄 화면(html경로)를 리턴문에
	// 지정해서 처리 최근에는 String 선호 (접미사에 .html에 있어서 생략 가능)

	@Autowired
	private BoardService boardService;

	@GetMapping(value = "/board/write.do")
	// 요청 메서드의 타입별로 매핑을 처리할 수 있는 애너테이션
	// !애니테이션이란 : 자바 소스 코드에 추가하여 사용할 수 있는 메타데이터의 일종
	// 클래스 파일에 임베디드되어 컴파일러에 의해 생성된 후 자바 가상머신에 포함되어 작동
	public String openBoardWrite(@RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null) {
			model.addAttribute("board", new BoardDTO());
			// Model : 메서드의 파라미터로 지정된 MODEL 인터페이스는 데이터를 뷰로 전달하는데 사용
		} else {
			BoardDTO board = boardService.getBoardDetail(idx);
			if (board == null) {
				return "redirect:/board/list.do";
			}
			model.addAttribute("board", board);
			// addAttribute메서드를 이용해서 화면(HTML)으로 데이터 전달 가능
			// 메서드의 인자로는 이름(String),값(Object)를 전달
			// HTML에서는 ${}표현식으로 전달받은 데이터에 접근가능
		}

		return "board/write";
	}

	@PostMapping(value = "/board/register.do")
	public String registerBoard(final BoardDTO params) {
		try {
			boolean isRegistered = boardService.registerBoard(params);
			if (isRegistered == false) {
				// TODO => 게시글 등록에 실패하였다는 메시지를 전달
			}
		} catch (DataAccessException e) {
			// TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달

		} catch (Exception e) {
			// TODO => 시스템에 문제가 발생하였다는 메시지를 전달
		}

		return "redirect:/board/list.do";
	}

}