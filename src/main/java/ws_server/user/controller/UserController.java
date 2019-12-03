package ws_server.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ws_server.user.service.UserService;

@Controller
public class UserController {
	
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
	
	@RequestMapping("/user/selectOneUser.do")
	@ResponseBody
	public Boolean selectOneUser(@RequestBody String userId)throws Exception{
		
		//boolean value = userService.selectOneUser(userId);
		
		return true;
	}
}
