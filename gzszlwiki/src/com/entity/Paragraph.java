package com.entity;

import java.sql.Timestamp;

/**
 * Paragraph entity. @author MyEclipse Persistence Tools
 */

public class Paragraph implements java.io.Serializable {

	// Fields

	private Integer paraid;
	private String ptitle;
	private String content;
	private Integer status;
	private String porder;
	private Integer textid;
	private Timestamp createTime;
	private String creater;
	private Timestamp lasttime;
	private String lastEditor;
	
	// Constructors

	/** default constructor */
	public Paragraph() {
	}

	/** minimal constructor */
	public Paragraph(Integer textid,String ptitle, String content) {
		this.textid = textid;
		this.ptitle = ptitle;
		this.content = content;
	}

	/** full constructor */
	public Paragraph( Integer textid,String ptitle, String content,
			Integer status, String porder,
			Timestamp lasttime,String lastEditor) {
		this.textid = textid;
		this.ptitle = ptitle;
		this.content = content;
		this.status = status;
		this.porder = porder;
		this.lasttime = lasttime;
		this.lastEditor = lastEditor;
	}

	// Property accessors

	public Integer getParaid() {
		return this.paraid;
	}

	public void setParaid(Integer paraid) {
		this.paraid = paraid;
	}


	public String getPtitle() {
		return this.ptitle;
	}

	public void setPtitle(String title) {
		this.ptitle = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPorder() {
		return this.porder;
	}

	public void setPorder(String porder) {
		this.porder = porder;
	}

	public Integer getTextid() {
		return textid;
	}

	public void setTextid(Integer textid) {
		this.textid = textid;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Timestamp getLasttime() {
		return lasttime;
	}

	public void setLasttime(Timestamp lasttime) {
		this.lasttime = lasttime;
	}

	public String getLastEditor() {
		return lastEditor;
	}

	public void setLastEditor(String lastEditor) {
		this.lastEditor = lastEditor;
	}
	
}