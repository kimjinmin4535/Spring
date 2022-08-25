package kr.co.annotation.anno02;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("loginController")		//컨트롤러 빈을 자동으로 생성함
public class LoginController {
	
	// /anno/loginForm.do와 /anno/loginForm2.do로 요청시 loginForm()을 호출함
	@RequestMapping(value = {"/anno/loginForm.do", "/anno/loginForm2.do"}, method = {RequestMethod.GET})
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginForm");
		return mav;
	}
	
	// GET방식과 POST 방식 요청시 모두 처리함 
	@RequestMapping(value = "/anno/login.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		
		mav.addObject("userId", userId);
		mav.addObject("userName", userName);
		
		return mav;
	}
	
	/*
	 * @RequestParam을 이용해 매개변수가 userId이면 그 값을 변수 userId에 자동으로 설정함
	 * @RequestParam을 이용해 매개변수가 userName이면 그 값을 변수 userName에 자동으로 설정함
	 */
	@RequestMapping(value = "/anno/login2.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login2(@RequestParam("userId") String userId, @RequestParam("userName") String userName,
								HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		
		System.out.println("userId : " + userId);
		System.out.println("userName : " + userName);
	
		mav.addObject("userId", userId);
		mav.addObject("userName", userName);
		
		return mav;
	}
	

	@RequestMapping(value = "/anno/login2_.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login2_(@RequestParam("userId") String userId, 
								@RequestParam(value = "userName", required = true) String userName,
								@RequestParam(value = "email", required = true) String email,
								HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		
		System.out.println("userId : " + userId);
		System.out.println("userName : " + userName);
		System.out.println("email : " + email);
	
		mav.addObject("userId", userId);
		mav.addObject("userName", userName);
		
		return mav;
	}	
	

	/*
	 * @RequestParam 이용해 Map에 전송된 매개변수 이름을 key, 값을 value로 저장함
	 */
	@RequestMapping(value = "/anno/login3.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login3(@RequestParam Map<String, String> info,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		
		//Map에 저장된 매개변수 이름으로 전달된 값을 가져옴
		String userId = info.get("userId");
		String userName = info.get("userName");
		
		System.out.println("userId : " + userId);
		System.out.println("userName : " + userName);		
		
		//@RequestParam에서 설정한 Map 이름으로 바인딩함
		mav.addObject("info", info);
		mav.setViewName("result");
		
		return mav;
	}
	
	/*
	 * @ModelAttribute를 이용해 전달되는 매개변수 값을 LoginDTO 클래스와 이름이 같은 속성에 자동으로 설정함.
	 * addObject() 이용할 필요없이 info를 이용해 바로 JSP에서 LoginDTO 속성에 접근할수 있음
	 */
	@RequestMapping(value = "/anno/login4.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login4(@ModelAttribute("info1") LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		
		System.out.println("userId : " + loginDTO.getUserId());
		System.out.println("userName : " + loginDTO.getUserName());
		
		mav.setViewName("result");
		return mav;
	}
	
	// 메서드 호출 시 Model 클래스 객체를 생성함
	@RequestMapping(value = "/anno/login5.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String login5(Model model ,HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		model.addAttribute("userId", "ezenac");			//JSP에 전달할 데이터를 model에 바인딩함
		model.addAttribute("userName", "이젠아카데미");
		return "result";
	}
}





















