package ws_server.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ws_server.user.dto.UserDto;
import ws_server.user.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean idCheck(String userId) throws Exception {
		return userMapper.idCheck(userId);
	}

	@Override
	public void insertUser(UserDto user) throws Exception {
		userMapper.insertUser(user);
	}

	@Override
	public UserDto selectOneUser(String userId) throws Exception {
		return userMapper.selectOneUser(userId);
	}

}
