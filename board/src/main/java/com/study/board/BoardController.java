package com.study.board;

import java.util.List;

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
		boardService.deleteBoard(id);
		return "redirect:/";
	}
	
	@RequestMapping(value="/board/read", method = RequestMethod.GET)
	public ModelAndView read(Long id, Model model) throws Exception{
		Board board = boardService.readBoard(id);
		model.addAttribute("board", board);
		ModelAndView mv = new ModelAndView("board/read");
		return mv;
	}
	
	@RequestMapping(value="/board/update", method = RequestMethod.GET)
	public ModelAndView goUpdate(Long id, Model model) {
		Board board = boardService.readBoard(id);
		model.addAttribute("board", board);
		ModelAndView mv = new ModelAndView("board/update");
		return mv;
	}
	
	@RequestMapping(value="/board/updateSave", method = RequestMethod.POST)
	public String update(@ModelAttribute Board board) throws Exception{
		boardService.updateBoard(board);
		return "redirect:/board/read?id=" + board.getId();
	}
}
