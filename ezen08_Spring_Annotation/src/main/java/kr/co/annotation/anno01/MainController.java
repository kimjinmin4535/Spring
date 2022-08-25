package kr.co.annotation.anno01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("mainController")	//@Controller 애너테이션을 이용해 MainController 클래스를 빈으로 자동 변환함. 빈id는 mainController임.
@RequestMapping("/anno") 		//@RequestMapping을 이용해 첫번째 단계의 URL 요청이 /anno이면 mainController 빈을 요청함
public class MainController {

	@RequestMapping("/main1.do")	//@RequestMapping을 이용해 두번째 단계의 URL 요청이 /main1.do이면 mainController빈의 ezen1() 메서드에게 요청함
	public ModelAndView ezen1(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "이젠아카데미");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("/main2.do")
	public ModelAndView ezen2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "이젠아카데미2");
		mav.setViewName("main");
		return mav;
	}	
}
