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
// UI를 담당하는 컨트롤러 클래스 사용자의 요청과응답 처리
public class BoardController {
	@Autowired
	private BoardService boardService;

	@GetMapping(value="/board/write.do")
	// 요청 메서드의 타입별로 매핑을 처리할 수 있는 애너테이션이 추가됨
	// @RequestMapping이 4.3버전에 들어서 업데이트 된거라고 하더라~
	public String openBoardWrite(@RequestParam(value="idx",required = false)Long idx,Model model) {
		// 메서드의 파라미터로 지정된 Model 인터페이스는 데이터를 뷰로 전달하는 사용
		if(idx==null) {
			model.addAttribute("board",new BoardDTO());
		}else {
			BoardDTO board = boardService.getBoardDetail(idx);
			if(board==null) {
				return "redirect:/board/list.do";
			}
			model.addAttribute("board",board);
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

// addAttribute -> html으로 데이터를 전달
// 메서드의 인자로는 String , Object value값 전달
// 저장된 데이터는 html에서 ${}표현식을 이용해서 접근할 수 있다
