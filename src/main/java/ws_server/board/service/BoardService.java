package ws_server.board.service;

import java.util.List;

import ws_server.board.dto.BoardDto;

public interface BoardService {

	List<BoardDto> selectBoardList(String category) throws Exception;

	void insertBoard(BoardDto board) throws Exception;

	BoardDto selectBoardDetail(int idKey) throws Exception;

	void updateBoard(BoardDto board) throws Exception;

	void deleteBoard(int idKey) throws Exception;

	List<BoardDto> selectAllBoardList()throws Exception;

}
