package com.study.board;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.study.board.file.BoardFile;
import com.study.common.FileUtils;

@Controller
@RequestMapping("")
public class BoardController
{
	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model)
	{
		List<Board> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		return "index";
	}

	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public ModelAndView goWrite(Model model)
	{
		ModelAndView mv = new ModelAndView("board/write");
		return mv;
	}

	@RequestMapping(value = "/board/save", method = RequestMethod.POST)
	public String save(@ModelAttribute Board board, MultipartHttpServletRequest req) throws Exception
	{
		boardService.saveBoard(board, req);

		return "redirect:/";
	}

	@RequestMapping(value = "/board/delete", method = RequestMethod.GET)
	public String delete(Long id) throws Exception
	{
		boardService.deleteBoard(id);
		return "redirect:/";
	}

	@RequestMapping(value = "/board/read", method = RequestMethod.GET)
	public ModelAndView read(Long id, Model model) throws Exception
	{
		Board board = boardService.readBoard(id);
		model.addAttribute("board", board);
		ModelAndView mv = new ModelAndView("board/read");
		return mv;
	}

	@RequestMapping(value = "/board/update", method = RequestMethod.POST)
	public ModelAndView goUpdate(Long id, String pswd, Model model)
	{
		Board board = boardService.readBoard(id);
		ModelAndView mv = new ModelAndView();
		model.addAttribute("board", board);
		if (board.getPswd().equals(pswd))
		{
			mv.setViewName("board/update");
		}
		else
		{
			model.addAttribute("wrongPswd", true);
			mv.addObject("id", id);
			mv.setViewName("board/check.pswd");
		}
		return mv;
	}

	@RequestMapping(value = "/board/updateSave", method = RequestMethod.POST)
	public String update(@ModelAttribute Board board) throws Exception
	{
		boardService.updateBoard(board);
		return "redirect:/board/read?id=" + board.getId();
	}

	@RequestMapping(value = "/board/fileDown")
	public void fileDown(Long fileId, HttpServletResponse response) throws Exception
	{
		BoardFile bf = boardService.readBoardFile(fileId);

		String storedFileName = bf.getPath();
		String originalFileName = bf.getFileName();
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = org.apache.commons.io.FileUtils
			.readFileToByteArray(new File(FileUtils.filePath + storedFileName));

		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response
			.setHeader(
				"Content-Disposition",
				"attachment; fileName=\"" + URLEncoder.encode(originalFileName, "UTF-8") + "\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	@RequestMapping(value = "/board/checkPswd", method = RequestMethod.GET)
	public ModelAndView checkPswd(Long id, Model model)
	{
		Board board = boardService.readBoard(id);
		model.addAttribute("board", board);
		ModelAndView mv = new ModelAndView("board/check.pswd");
		return mv;
	}
}
