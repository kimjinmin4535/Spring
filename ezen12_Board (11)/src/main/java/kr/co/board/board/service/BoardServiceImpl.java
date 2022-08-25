package kr.co.board.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.board.board.dao.BoardDAO;
import kr.co.board.board.dto.ArticleDTO;
import kr.co.board.board.dto.ImageDTO;

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;

	@Override
	public List<ArticleDTO> listArticles() throws Exception {
	
		List<ArticleDTO> ariclesList = boardDAO.selectAllArticlesList();
		return ariclesList;
	}
	
	@Override
	public Map<String, Integer> listArticles(Map<String, Integer> pagingMap) throws Exception {

		Map articlesMap = new HashMap();
		
		List<ArticleDTO> ariclesList = boardDAO.selectAllArticlesList(pagingMap);
		int totArtices = boardDAO.selectTotArticles();
		
		articlesMap.put("ariclesList", ariclesList);
		articlesMap.put("totArtices", totArtices);
			
		return articlesMap;
	}	

	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		// dao 호출
		int articleNo = boardDAO.insertNewArticle(articleMap);		//글 정보를 저장한 후 글번호를 가져옴.
		articleMap.put("articleNo", articleNo);						//글번호를 articleMap에 저장한 후 
		
		boardDAO.insertNewImage(articleMap);						//이미지 정보를 저장함
		
		return articleNo;
	}

	@Override
	public Map<String, Object> viewArticle(int articleNO) throws Exception {
		
		Map<String, Object> articleMap = new HashMap<>();
		
		ArticleDTO articleDTO = boardDAO.selectArticle(articleNO);
		
		//이미지 부분 정보 요청
		List<ImageDTO> imageFileList = boardDAO.selectImageFileList(articleNO);
		
		articleMap.put("article", articleDTO);
		articleMap.put("imageFileList", imageFileList);
		
		return articleMap;
	}
	
	@Override
	public Map<String, Object> viewArticle(Map<String, Object> viewMap) throws Exception {
		int articleNO = (int) viewMap.get("articleNO");
		String id = (String) viewMap.get("id");
		
		// 조회수를 갱신하기 전 먼저 글번호에 해당되는 글정보를 조회
		ArticleDTO articleDTO = boardDAO.selectArticle(articleNO);
		
		// 비로그인 상태와 
		// 로그인한 아이디(id)와 게시글의 글쓴이 아이디를 비교함
		String writerId = articleDTO.getId();
		if (id == null || !(id.equals(writerId))) {
			// 조회수 1 증가시킴
			boardDAO.updateViewCounts(articleNO);
			articleDTO = boardDAO.selectArticle(articleNO);		
		}

		//이미지 부분 정보 요청
		List<ImageDTO> imageFileList = boardDAO.selectImageFileList(articleNO);		
		
		Map<String, Object> articleMap = new HashMap<>();
		articleMap.put("article", articleDTO);
		articleMap.put("imageFileList", imageFileList);		

		return articleMap;
	}	

	@Override
	public void modArticle(Map<String, Object> articleMap) throws Exception {
		boardDAO.updateArticle(articleMap);
		
		List<ImageDTO> imageFileList = (List<ImageDTO>) articleMap.get("imageFileList");
		List<ImageDTO> modAddImageFileList = (List<ImageDTO>) articleMap.get("modAddImageFileList");
		
		if (imageFileList != null && imageFileList.size() != 0) {
			int added_img_num = Integer.parseInt((String)articleMap.get("added_img_num"));
			int pre_img_num = Integer.parseInt((String)articleMap.get("pre_img_num"));
			
			if (pre_img_num < added_img_num) {				//기존 이미지도 수정하고 새 이미지도 추가한 경우
				boardDAO.updateImageFile(articleMap);		//기존 이미지 수정
				boardDAO.insertModNewImage(articleMap);		//새 이미지 추가
			}
			else {											//기존 이미지를 수정만 한 경우
				boardDAO.updateImageFile(articleMap);
			}
		}
		// 새 이미지를 추가한 경우
		else if (modAddImageFileList != null && modAddImageFileList.size() != 0) {													
			boardDAO.insertModNewImage(articleMap);
		}
	}

	@Override
	public void removeArticle(int articleNO) throws Exception {
		boardDAO.deleteArticle(articleNO);
		
	}

	@Override
	public void removeModImage(ImageDTO imageDTO) throws Exception {
		boardDAO.deleteModImage(imageDTO);
		
	}

	@Override
	public int addReplyArticle(Map articleMap) throws Exception {
		
		int articleNO = boardDAO.insertReplyArticle(articleMap);
		articleMap.put("articleNO", articleNO);
		
		boardDAO.insertNewImage(articleMap);
		
		return articleNO;
	}





}



















