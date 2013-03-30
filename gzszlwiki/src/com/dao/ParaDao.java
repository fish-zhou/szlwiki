package com.dao;

import com.entity.Paragraph;

public interface ParaDao {
	boolean save (Paragraph  para);
	Paragraph getParaById (int  paraid);
	int getStatus ( int  ParaId );
	boolean deletePara ( int  paraid);
	boolean changeParaStatus(Paragraph para );
}
