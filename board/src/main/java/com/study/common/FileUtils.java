package com.study.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.study.board.Board;
import com.study.board.file.BoardFile;

/**
 * 
 * <desc>
 *  @Component 명칭과 사용하는 곳에서 @Resource name 명칭이 같아야함 
 */
@Component("fileUtils")
public class FileUtils
{
	public final String filePath = "/Users/choyunsol/git/repository/";

	public List<BoardFile> uploadFileInfo(Board board, MultipartHttpServletRequest mpRequest) throws Exception
	{
//		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) req;
		Iterator<String> iterator = mpRequest.getFileNames();

		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;

		List<BoardFile> list = new ArrayList<BoardFile>();

		Long id = board.getId();
		System.out.println("id??" + id);

		File file = new File(filePath);
		if (file.exists() == false)
		{
			file.mkdirs();
		}

		while (iterator.hasNext())
		{
			multipartFile = mpRequest.getFile(iterator.next());
			if (multipartFile.isEmpty() == false)
			{
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;

				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);
				
				System.out.println("fileName::" + originalFileName);

				BoardFile f = new BoardFile(board);
				f.setBoardId(id);
				f.setFileName(originalFileName);
				f.setPath(storedFileName);
				f.setFileSize(multipartFile.getSize());
				list.add(f);
			}
		}
		return list;
	}

	public static String getRandomString()
	{
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
