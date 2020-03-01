package com.study.board;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.study.board.file.BoardFile;
import com.study.common.FileUtils;

@Service
@Transactional
public class BoardService
{
	@Autowired
	private BoardDAO boardDAO;

	@Resource(name = "fileUtils")
	private FileUtils fileUtils;

	public List<Board> getBoardList()
	{
		Board board = new Board();
		return boardDAO.selectList(board);
	}

	public void saveBoard(Board board, MultipartHttpServletRequest req) throws Exception
	{
		boardDAO.insertBoard(board);

		try
		{

			List<BoardFile> fList = fileUtils.uploadFileInfo(board, req);
			for (BoardFile f : fList)
			{
				boardDAO.uploadFile(f);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void deleteBoard(Long id)
	{
		Board board = boardDAO.readBoard(id);
		List<BoardFile> fList = board.getBoardFiles();
		for (BoardFile f : fList)
		{
			fileUtils.deleteFile(f.getPath());
//			boardDAO.deleteFile(f);
		}

		boardDAO.deleteBoard(id);
	}

	public void updateBoard(Board board)
	{
		boardDAO.updateBoard(board);
	}

	public Board readBoard(Long id)
	{
		return boardDAO.readBoard(id);
	}

	public BoardFile readBoardFile(Long id)
	{
		return boardDAO.readFile(id);
	}
}
