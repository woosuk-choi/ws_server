package ws_server.board.dto;

import lombok.Data;

@Data
public class BoardDto {
	private int idKey;
	private String title;
	private String contents;
	private int hitCnt;
	private String createdDate;
	private String creatorId;
	private String updatedDate;
	private String updaterId;
	private String category;
}
