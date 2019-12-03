package ws_server.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ws_server.board.service.BoardService;
import ws_server.board.dto.BoardDto;

@Controller
public class BoardController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/openMain.do")
	public ModelAndView openMain()throws Exception{
		ModelAndView mv = new ModelAndView("/main");
		
		List<BoardDto> list = boardService.selectAllBoardList();
		mv.addObject("list", list);
		
		return mv;
	}
	
	@RequestMapping("/board/openBoardList.do")
	public ModelAndView openBoardList(@RequestParam String category) throws Exception{
		log.debug("openBoardList");
		ModelAndView mv = new ModelAndView("/board/boardList");
		
		List<BoardDto> list = boardService.selectBoardList(category);
		mv.addObject("list", list);
		
		return mv;
	}
	
	@RequestMapping("/board/openBoardWrite.do")
	public String openBoardWrite() throws Exception{
		return "/board/boardWrite";
	}
	
	@RequestMapping("/board/insertBoard.do")
	public String insertBoard(BoardDto board) throws Exception{
		//board.setCategory(category);
		boardService.insertBoard(board);
		log.debug(board.getCategory());
		return "redirect:/openMain.do";
	}
	
	@RequestMapping("/board/openBoardDetail.do")
	public ModelAndView openBoardDetail(@RequestParam int idKey) throws Exception{
		ModelAndView mv = new ModelAndView("/board/boardDetail");
		
		BoardDto board = boardService.selectBoardDetail(idKey);
		mv.addObject("board", board);
		
		return mv;
	}
	
	@RequestMapping("/board/updateBoard.do")
	public String updateBoard(BoardDto board)throws Exception{
		boardService.updateBoard(board);
		return "redirect:/openMain.do";
	}
	
	@RequestMapping("/board/deleteBoard.do")
	public String deleteBoard(int idKey)throws Exception{
		boardService.deleteBoard(idKey);
		return "redirect:/openMain.do";
	}
}
