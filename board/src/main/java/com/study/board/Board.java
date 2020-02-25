package com.study.board;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name="board")
public class Board
{
	/** 번호 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="id", updatable = false, nullable = false)
	private Long id;
	/** 제목 */
	@Column(name="title", nullable=false, length=1000)
	private String title;
	/** 내용 */
	@Column(name="content", nullable=false, length=4000)
	private String content;
	/** 등록 일시 */
	@Generated(GenerationTime.INSERT)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="rgst_date", insertable = false)
	private Date rgstDate;
	
	/** 페이지 번호 */
	private String pageNum = "1";
	/** 목록 페이지 게시물 노출 수 */
	private int listCount = 10;
	/** 목록 페이지 네비게이터 블록 수 */
	private int pagePerBlock = 10;
	/** 목록 페이지 시작 인덱스 위치 */
	private int startIndex = 0;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public Date getRgstDate()
	{
		return rgstDate;
	}

	public void setRgstDate(Date rgstDate)
	{
		this.rgstDate = rgstDate;
	}
	
	public String getPageNum()
	{
		return pageNum;
	}

	public void setPageNum(String pageNum)
	{
		this.pageNum = pageNum;
	}

	public int getListCount()
	{
		return listCount;
	}

	public void setListCount(int listCount)
	{
		this.listCount = listCount;
	}

	public int getPagePerBlock()
	{
		return pagePerBlock;
	}

	public void setPagePerBlock(int pagePerBlock)
	{
		this.pagePerBlock = pagePerBlock;
	}

	public int getStartIndex()
	{
		startIndex = listCount * (Integer.parseInt(pageNum) - 1);
		return startIndex;
	}

	public void setStartIndex(int startIndex)
	{
		this.startIndex = startIndex;
	}

}