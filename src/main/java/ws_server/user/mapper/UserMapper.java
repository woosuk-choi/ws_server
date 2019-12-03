package ws_server.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import ws_server.user.dto.UserDto;

@Mapper
public interface UserMapper {

	boolean idCheck(String userId)throws Exception;

	void insertUser(UserDto user)throws Exception;

	UserDto selectOneUser(String userId)throws Exception;

}
