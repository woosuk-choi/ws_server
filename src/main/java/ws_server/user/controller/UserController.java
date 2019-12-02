package ws_server.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@RequestMapping("/user/openUserLogin.do")
	public ModelAndView openUserLogin() throws Exception{
		ModelAndView mv = new ModelAndView("user/userLogin");
		
		return mv;
	}
}
