package kr.co.board.board.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

public class ImageDTO {

	private int imageFileNO;
	private String imageFileName;
	private Date regDate;
	private int articleNO;
	public int getImageFileNO() {
		return imageFileNO;
	}
	public void setImageFileNO(int imageFileNO) {
		this.imageFileNO = imageFileNO;
	}
	public String getImageFileName() {
		
		if (imageFileName != null && imageFileName.length() != 0) {
			try {
				imageFileName = URLDecoder.decode(imageFileName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		
		if (imageFileName != null && imageFileName.length() != 0) {
			try {
				this.imageFileName = URLEncoder.encode(imageFileName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getArticleNO() {
		return articleNO;
	}
	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}
	
	
	
}
