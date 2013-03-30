package com.entity;

import java.sql.Timestamp;
import java.util.Set;

/**
 * Text entity. @author MyEclipse Persistence Tools
 */

public class Text implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// Fields

	private Integer textid;
	private Integer readTimes;
	private String title;
	private String username;
	private String lastEditor;
	private Timestamp lasttime;
	private Timestamp createTime;
	private Set<Paragraph> paragraphs ;

	// Constructors

	/** default constructor */
	public Text() {
	}

	/** minimal constructor */
	public Text(String title, String username) {
		this.title = title;
		this.username = username;
	}

	/** full constructor */
	public Text(String title, String username, Timestamp lasttime) {
		this.title = title;
		this.username = username;
		this.lasttime = lasttime;
	}
	
	public void addPara(Paragraph paragraph){
		this.getParagraphs().add(paragraph);
	}

	// Property accessors

	public Integer getTextid() {
		return this.textid;
	}

	public void setTextid(Integer textid) {
		this.textid = textid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Timestamp getLasttime() {
		return this.lasttime;
	}

	public void setLasttime(Timestamp lasttime) {
		this.lasttime = lasttime;
	}
	public Set<Paragraph> getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(Set<Paragraph> paragraphs) {
		this.paragraphs = paragraphs;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getLastEditor() {
		return lastEditor;
	}
	public void setLastEditor(String lastEditor) {
		this.lastEditor = lastEditor;
	}
	public Integer getReadTimes() {
		return readTimes;
	}
	public void setReadTimes(Integer readTimes) {
		this.readTimes = readTimes;
	}
	
}