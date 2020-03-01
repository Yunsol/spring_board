package com.study.board.file;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.study.board.Board;

@Entity
@Table(name = "files")
public class BoardFile
{
	public BoardFile()
	{
		super();
	}

	public BoardFile(Board board)
	{
		super();
		this.board = board;
	}

	@ManyToOne
	@JoinColumn(name = "boardid")
	private Board board;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fileid", updatable = false, nullable = false)
	private Long fileId;

	@Column(name = "boardid", insertable = false, updatable = false)
	private Long boardId;

	@Column(name = "path")
	private String path;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_size", nullable = false)
	private long fileSize;

	public Long getFileId()
	{
		return fileId;
	}

	public void setFileId(Long fileId)
	{
		this.fileId = fileId;
	}

	public Long getBoardId()
	{
		return boardId;
	}

	public void setBoardId(Long boardId)
	{
		this.boardId = boardId;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public long getFileSize()
	{
		return fileSize;
	}

	public void setFileSize(long fileSize)
	{
		this.fileSize = fileSize;
	}

}
