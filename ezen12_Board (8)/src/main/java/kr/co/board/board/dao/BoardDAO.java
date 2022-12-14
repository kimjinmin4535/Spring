package kr.co.board.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import kr.co.board.board.dto.ArticleDTO;
import kr.co.board.board.dto.ImageDTO;

public interface BoardDAO {
	public List<ArticleDTO> selectAllArticlesList() throws DataAccessException;

	public int insertNewArticle(Map articleMap) throws DataAccessException;

	public void insertNewImage(Map articleMap) throws DataAccessException;

	public ArticleDTO selectArticle(int articleNO) throws DataAccessException;

	public List<ImageDTO> selectImageFileList(int articleNO) throws DataAccessException;

	public void updateArticle(Map<String, Object> articleMap) throws DataAccessException;

	public void updateImageFile(Map<String, Object> articleMap) throws DataAccessException;

	public void insertModNewImage(Map<String, Object> articleMap) throws DataAccessException;
}
