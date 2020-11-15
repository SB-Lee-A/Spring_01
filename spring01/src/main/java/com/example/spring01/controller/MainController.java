package com.example.spring01.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring01.model.dto.PointDTO;
import com.example.spring01.model.dto.ProductDTO;

@Controller
// Controller를 경유해서 url을 매핑합니다
// Controller bean으로 등록됨
public class MainController {
	
	// http://localhost:7002/spring01
	@RequestMapping("/")
	public String main(Model model) {
		model.addAttribute("name","이지은");
		model.addAttribute("message","홈페이지 방문을 환영합니다.");
		return "main"; //main.jsp
	}
	
	@RequestMapping("gugu.do")
	public String write() {
		//test/gugu로 이동
		return "test/gugu";
	}
	@RequestMapping("gugu_result.do") //gugu.jsp에 form action값
	public String gugu(@RequestParam(defaultValue="3")int dan,Model model)
	// int dan = gugu.jsp에 name="dan"값을 넘겨받아야해서 선언 
	{
		String result="";
		for(int i = 1; i <= 9; i++) {
			result += dan + "x" + i + "=" + dan*i+"<br>";
		}
		model.addAttribute("result",result);
		//result변수를 model에 담아서 test/gugu_result로 넘겨줌
		return "test/gugu_result";
	}
	@RequestMapping("point.do")
	public String point() {
		return "test/point";
	}
	@RequestMapping("point_result.do")
	public String point_result(@ModelAttribute PointDTO dto,Model model)
	// pointdto = class pointdto를 만들어서 dto라는 변수로 값을 넘겨받음
	{
		dto.setTotal(dto.getKor() + dto.getEng() + dto.getMat());
//		double average =  dto.getTotal()/3.0;
//		dto.setAverage(average);
		String average = String.format("%.2f", dto.getTotal()/3.0);
		// 소숫점 2자리까지 출력
		dto.setAverage(Double.parseDouble(average));

		model.addAttribute("dto",dto);
		// model에 dto를 저장하고 변수명 값 넘겨받음
		return "test/point_result";
	}
	@RequestMapping("move.do")
	public String move() {
		return "redirect:/result.do";
		// return "redirect:/result.do?name=kim&age=20";
	}
	@RequestMapping("result.do")
	public String result(Model model,
			@RequestParam(defaultValue = "noname")String name,
			@RequestParam(defaultValue = "10") int age) {
		model.addAttribute("name",name);
		model.addAttribute("age",age);
		return "test/result";
	}
	@RequestMapping("mav.do")
	public ModelAndView mav() {
		Map<String,Object> map = new HashMap<String,Object>();
		// 해쉬 맵의 자료를 추가하는데 앞에 변수명에 값을 줘서 객체를 담음
		map.put("product", new ProductDTO("샤프",1000));
		return new ModelAndView("test/mav_result","map",map);
		// ModelandView 모델에 뉴 데이터 모델링
		// test/mav_result --> url
		// "map" = 모델 변수명 , map = 데이터
		
	}
}


// @RequestParam - 개별변수로 전달
// @ModelAttribute - 모델 클래스로 한꺼번에 전달
// 페이지 이동 
// forward 방식 : url 고정, 데이터 전달
// 주로 컨트롤러의 method에서 처리한 내용들을 jsp page로 전달할 경우 사용
// redirect 방식 : url 변경, 데이터 전달 X (파라미터값만 전달)
// 주로 컨트롤러의 다른 method로 이동할 경우 주로 사용
// ex) 게시판에서 글을 작성 완료 후 목록으로 이동할 경우

//ModelAndView
// 모델 : 전달할 데이터
// 뷰 : jsp page
