package com.study.board;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class BoardController
{
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;

	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model model) {
		List<Board> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		return "index";
	}
	
	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public ModelAndView goWrite(Model model) {
		ModelAndView mv = new ModelAndView("board/write");
		return mv;
	}
	
	@RequestMapping(value = "/board/save", method = RequestMethod.POST)
	public String save(@ModelAttribute Board board) throws Exception {
		boardService.saveBoard(board);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/board/delete", method = RequestMethod.GET)
	public String delete(Long id) throws Exception {
		boardService.deleteBoard(id);;
		return "redirect:/";
	}
}
