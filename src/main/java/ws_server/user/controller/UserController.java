package ws_server.user.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ws_server.user.dto.UserDto;
import ws_server.user.service.UserService;

@SuppressWarnings("serial")
@Controller
public class UserController extends HttpServlet{
	
	@Autowired 
	private UserService userService;

	@RequestMapping("/user/openUserLogin.do")
	public String openUserLogin() throws Exception{
		return "/user/userLogin";
	}
	
	@RequestMapping("/user/openUserJoin.do")
	public String openUserJoin()throws Exception{
		return "/user/userJoin";
	}
	
	@RequestMapping("/user/idCheck.do")
	@ResponseBody
	public Boolean idCheck(@RequestBody String userId)throws Exception{
		
		boolean value = userService.idCheck(userId);
		
		return value;
	}
	
	@RequestMapping("/user/insertUser.do")
	public String insertUser(UserDto user)throws Exception{
		userService.insertUser(user);
		return "redirect:/user/openUserLogin.do";
	} 
	
	@RequestMapping("/user/loginUser.do")
	public ModelAndView loginUser(@RequestParam String userId,
						  @RequestParam String password,
						  HttpServletRequest request)throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		UserDto user = userService.selectOneUser(userId);
		
		String msg = "";
		
		//아이디가 존재하지 않는 경우
		if(user == null) {
			msg = "아이디가 존재하지 않습니다.";
			
			mv.setViewName("/user/userLogin");
			mv.addObject("msg", msg);
		}else {
			//로그인 성공
			if(password == user.getPassword()) {
				msg = "[" + user.getUserName() + "]님 환영합니다.";
				
				//세션 저장
				request.setAttribute("userId", userId);
				
				mv.setViewName("/main");
				mv.addObject("msg", msg);
			}else {
				msg = "비밀번호가 틀립니다.";
				
				mv.setViewName("/user/userLogin");
				mv.addObject("msg", msg);
			}
		}
		
		return mv;
	}
	
}
