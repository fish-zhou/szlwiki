package com.service;

import java.util.List;

import util.PageBean;

import com.entity.Paragraph;
import com.entity.Text;

public interface TextService {
	public Text viewText (int textid);
	public PageBean viewText(PageBean pageBean);
	public boolean addText (Text text);
	public boolean updateText (Text text,Paragraph para);
	public boolean updateText (Text text);
	public boolean deleteText (int textid );
}
