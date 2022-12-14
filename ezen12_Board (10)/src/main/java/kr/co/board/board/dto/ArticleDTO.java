package kr.co.board.board.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

/*
	articleNO		number(10)	PRIMARY KEY 
	,parentNO		number(10) 	DEFAULT 0
	,title			varchar2(500)	NOT NULL
	,content		varchar2(4000)
	,imageFileName	varchar2(100)
	,writeDate		DATE		DEFAULT sysdate NOT NULL
	,id				varchar2(10) * 
 */
@Component("articleDTO")
public class ArticleDTO {

	private int level;
	private int groupNO;
	private int articleNO;
	private int parentNO;
	private String title;
	private String content;
	private String imageFileName;
	private String id;
	private Date writeDate;
	
	public ArticleDTO() {
		// TODO Auto-generated constructor stub
	}

	public ArticleDTO(int level, int articleNO, int parentNO, String title, String content, String imageFileName,
			String id) {
		//super();
		this.level = level;
		this.articleNO = articleNO;
		this.parentNO = parentNO;
		this.title = title;
		this.content = content;
		this.imageFileName = imageFileName;
		this.id = id;
	}

	public int getGroupNO() {
		return groupNO;
	}

	public void setGroupNO(int groupNO) {
		this.groupNO = groupNO;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getArticleNO() {
		return articleNO;
	}

	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}

	public int getParentNO() {
		return parentNO;
	}

	public void setParentNO(int parentNO) {
		this.parentNO = parentNO;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	
	
}























