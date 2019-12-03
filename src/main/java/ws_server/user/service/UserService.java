package ws_server.user.service;

import ws_server.user.dto.UserDto;

public interface UserService {

	boolean idCheck(String userId) throws Exception;

	void insertUser(UserDto user)throws Exception;

	UserDto selectOneUser(String userId)throws Exception;

}
