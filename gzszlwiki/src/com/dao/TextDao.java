package com.dao;

import java.util.List;

import util.PageBean;

import com.entity.Text;

public interface TextDao {
	PageBean viewText(PageBean pageBean);

	Text getText(int textid);

	boolean save(Text text);

	boolean updateText(Text text);

	boolean deleteText(int textid);
}
