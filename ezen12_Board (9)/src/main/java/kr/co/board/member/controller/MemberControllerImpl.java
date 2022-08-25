package kr.co.board.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.board.member.dto.MemberDTO;
import kr.co.board.member.service.MemberService;


/*
 * @Controller를 이용해 MemberControllerImpl 클래스에 대해 id가 memberController인 빈을 자동 생성함
 */
@Controller("memberController")
@EnableAspectJAutoProxy 		//aop 기능 활성화 
public class MemberControllerImpl extends MultiActionController implements MemberController {
	
	//private static final Logger logger = LoggerFactory.getLogger("MemberControllerImpl.class");
	
	//@Autowired를 이용해 id가 memberService인 빈을 자동 주입함
	@Autowired
	private MemberService memberService;
	
	//@Autowired를 이용해 id가 memberDTO인 빈을 자동 주입함
	@Autowired
	private MemberDTO memberDTO;

	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.setViewName(viewName);
		return mav;
	}
	
	//두 단계로 요청 시 바로 해당 메서드를 호출하도록 매핑함
	@RequestMapping(value = "/member/listMembers.do", method = RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/* 브라우저에서의 요청명에서 확장명 .do를 제외한 뷰이름을 가져옴 */
		//String viewName = getViewName(request);
		//System.out.println("viewName = " + viewName);
		//logger.debug("debug 레벨 : viewName = " + viewName);
		
		// 인터셉터에서 바인딩된 뷰이름을 가져옴
		String viewName = (String) request.getAttribute("viewName");
		
		List<MemberDTO> membersList = memberService.listMembers();
				
		ModelAndView mav = new ModelAndView(viewName);	 // viewName이 definition태그에 설정한 뷰이름과 일치함
		// 조회한 회원 정보를 ModelAndView의 addObject() 이용해 바인딩함
		mav.addObject("membersList", membersList);
		return mav;		// ModelAndView 객체에 설정한 뷰이름을 타일즈 뷰리졸버로 반환함 
	}

	//요청명이 Form.do로 끝나면 form() 메서드 호출함
//	@RequestMapping(value = "/member/*Form.do", method = RequestMethod.GET)
//	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String viewName = getViewName(request);
//		
//		//db 연동 작업이 없는 입력창 요청시 뷰이름만 ModelAndView로 반환함
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName(viewName);
//		return mav;
//	}

	
	/*
	 * 로그인창 요청 시 매개변수 result가 전송되면 
	 * 변수 result에 값을 저장함.
	 * 매개변수 result가 전송되지 않으면 무시함.
	 * 
	 * 로그인 후 수행할 글쓰기 요청명을 action에 저장
	 */
	@RequestMapping(value = "/member/*Form.do", method = RequestMethod.GET)
	public ModelAndView form(@RequestParam(value = "result", required = false) String result,
							@RequestParam(value = "action", required = false) String action,
							HttpServletRequest request, HttpServletResponse response) throws Exception {
		//String viewName = getViewName(request);
		String viewName = (String) request.getAttribute("viewName");	//인터셉터에서 바인딩된 뷰이름 가져옴
		
		HttpSession session = request.getSession();
		session.setAttribute("action", action);
		
		//db 연동 작업이 없는 입력창 요청시 뷰이름만 ModelAndView로 반환함
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}	
	
	


	//회원 가입창에서 전송된 회원 정보를 바로 MemberDTO 객체에 설정함
	@Override
	@RequestMapping(value = "/member/addMember.do", method = RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") MemberDTO memberDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
//		String id=request.getParameter("id");
//		String pwd=request.getParameter("pwd");
//		String name=request.getParameter("name");
//		String email=request.getParameter("email");
//		memberDTO.setId(id);
//		memberDTO.setPwd(pwd);
//		memberDTO.setName(name);
//		memberDTO.setEmail(email);
	
		//bind(request, memberDTO);
		
		//설정된 MemberDTO 객체를 SQL문으로 전달해 회원등록 함
		int result = memberService.addMember(memberDTO);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	//전송된 id를 변수 id에 설정함
	@Override
	@RequestMapping(value = "/member/removeMember.do", method = RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		//String id = request.getParameter("id");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	@Override
	@RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
																		//리다이렉트 시 매개변수를 전달함	
	public ModelAndView login(@ModelAttribute("member")MemberDTO member, RedirectAttributes rAttributes, 
			HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView();
		
		memberDTO = memberService.login(member);
		
		if (memberDTO != null) {	// 로그인 성공 시
			HttpSession session = request.getSession();
			session.setAttribute("member", memberDTO);				//세션에 회원 정보 저장함
			session.setAttribute("isLogOn", true);					//세션에 로그인 상태를 true로 설정함
			
			String action = (String) session.getAttribute("action");  //로그인 성공시 세션에 저장된 action 값 가져옴
			
			if (action != null) {
				if (action.equals("/board/articleForm.do")) {		
					mav.setViewName("redirect:" + action);			  //action값을 뷰이름으로 지정해 글쓰기창으로 이동함
				}
			} else {
				mav.setViewName("redirect:/main.do");
			}
				
			session.removeAttribute("action");
			
		} else {
			rAttributes.addAttribute("result", "loginFailed");		//로그인 실패 시 실패 메시지를 로그인창 전달
			mav.setViewName("redirect:/member/loginForm.do");		//로그인 실패 시 다시 로그인창으로 리다리엑트함
		}
		
		return mav;
	}

	@Override
	@RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		//로그아웃 요청 시 세션에 저장된 로그인 정보와 회원 정보 삭제함
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/member/loginForm.do");
		
		return mav;
	}
	
	
	
}












