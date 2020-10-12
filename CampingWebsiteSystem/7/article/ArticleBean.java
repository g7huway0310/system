package article;

import java.io.Serializable;

public class ArticleBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String title;
	private String text;
	private String ariticleid;
	private String memberid;


public ArticleBean() {
	
}


public ArticleBean(String title, String text, String ariticleid, String memberid) {

	this.title = title;
	this.text = text;
	this.ariticleid= ariticleid;
	this.memberid = memberid;
}


public String getTitle() {
	return title;
}


public void setTitle(String title) {
	this.title = title;
}


public String getText() {
	return text;
}


public void setText(String text) {
	this.text = text;
}


public String getAriticleid() {
	return ariticleid;
}


public void setAriticleid(String ariticleid) {
	this.ariticleid = ariticleid;
}


public String getMemberid() {
	return memberid;
}


public void setMemberid(String memberid) {
	this.memberid = memberid;
}


public static long getSerialversionuid() {
	return serialVersionUID;
}





}
