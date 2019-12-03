package ws_server.user.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDto {
	private long idKey;
	private String userName;
	private String userId;
	private String password;
	private Date joinDate;
}
