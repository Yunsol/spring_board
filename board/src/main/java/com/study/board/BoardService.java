package com.study.board;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BoardService
{
	@Autowired
	private BoardDAO boardDAO;

	public List<Board> getBoardList()
	{
		Board board = new Board();
		return boardDAO.selectList(board);
	}

	public void saveBoard(Board board)
	{
		boardDAO.insertBoard(board);
	}

	public void deleteBoard(Long id)
	{
		boardDAO.deleteBoard(id);
	}

	public void updateBoard(Board board)
	{
		boardDAO.updateBoard(board);
	}
	
	public Board readBoard(Long id) {
		return boardDAO.readBoard(id);
	}
}
