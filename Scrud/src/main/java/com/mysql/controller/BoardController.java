package com.mysql.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.domain.BoardVO;
import com.mysql.service.BoardService;

@Controller
@RequestMapping("/board/") //url요청이 /board/로 시작하는 것을 여기서 처리
//http://localhost:포트번호/board/로 시작되는 요청을 boardController에서 받겠다는 뜻
public class BoardController {
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public void createGET(BoardVO board,Model model) throws Exception{
		// 스프링MVC에서 제공하는 데이터를 전달해주는 객체
		System.out.println("/board/create입니다. GET방식");	
	
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String createPOST(BoardVO board,RedirectAttributes rttr) throws Exception{
		System.out.println("/board/create POST방식");
		System.out.println(board.toString());
		
		service.create(board);
		rttr.addFlashAttribute("msg","성공");
		
		return "redirect:/board/listAll";
		// RedirectAttributes는 리다이렉트 시점에 한번만 사용되는 데이터를 만듬
		// (addFlashAttribute("",""))를 입력하게되면
		// 한번만 사용되는 데이터 + url상에는 보이지 않는 숨겨진 데이터의 형태로 전달
		// url상에서는 확인이 불가
	}
	@RequestMapping(value="/listAll", method=RequestMethod.GET)
	public void listAll(Model model) throws Exception{
		System.out.println("전체목록 페이지");
		model.addAttribute("boardList",service.listAll());
	}

}
