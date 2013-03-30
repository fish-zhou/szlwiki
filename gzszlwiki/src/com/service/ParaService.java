package com.service;

import com.entity.Paragraph;

public interface ParaService {
	
	Paragraph getParaById (int paraid); 
	boolean changeParaStatus(Paragraph para );
	boolean deletePara(int paraid);
	
}
