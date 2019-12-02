package ws_server.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ws_server.board.dto.BoardDto;

@Mapper
public interface BoardMapper {

	public List<BoardDto> selectBoardList() throws Exception;

	void insertBoard(BoardDto board) throws Exception;

	BoardDto selectBoardDetail(int boardIdx) throws Exception;

	void updateHitCount(int boardIdx) throws Exception;

	void updateBoard(BoardDto board) throws Exception;

	void deleteBoard(int boardIdx) throws Exception;
}
