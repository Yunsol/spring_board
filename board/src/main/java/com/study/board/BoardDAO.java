package com.study.board;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.board.file.BoardFile;

@Repository
@Transactional
public class BoardDAO
{
	private SessionFactory sessionFactory;

	@Autowired
	public BoardDAO(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Board> selectList(Board board)
	{
		// Hibernate 세션 객체 Open!
		Session session = this.sessionFactory.openSession();
		try
		{
			// 직관적인 Criteria query API 사용한 경우!
			Criteria criteria = session.createCriteria(Board.class);
			// 행의 범위 설정 LIMIT ?, ? 값을 의미
			criteria.setMaxResults(board.getListCount());
			criteria.setFirstResult(board.getStartIndex());

			// 등록일시로 정렬 한다. (※컬럼명이 아니라 entity변수)
			criteria.addOrder(Order.desc("rgstDate"));

			return criteria.list();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			// 사용한 세션을 종료
			if (session != null)
				session.close();
		}

		return null;
	}

	public int selectCount(Board boardModel)
	{
		Session session = this.sessionFactory.openSession();
		try
		{
			Criteria criteria = session.createCriteria(Board.class);
			criteria.setProjection(Projections.rowCount());
			return ((Long) criteria.uniqueResult()).intValue();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (session != null)
				session.close();
		}
		return 0;
	}

	public void insertBoard(Board board)
	{
		getSession().save(board);
	}

	public void deleteBoard(Long id)
	{
		Board board = (Board) getSession().get(Board.class, id);
		getSession().delete(board);
	}

	public void updateBoard(Board board)
	{
		getSession().update(board);
	}

	public Board readBoard(Long id)
	{
		return (Board) getSession().get(Board.class, id);
	}

	public void uploadFile(BoardFile file) throws Exception
	{
		getSession().save(file);
	}

	public BoardFile readFile(Long id)
	{
		return (BoardFile) getSession().get(BoardFile.class, id);
	}
}
