package com.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dao.ParaDao;
import com.entity.Paragraph;
import com.service.ParaService;

@Component("paraservice")
public class ParaServiceImpl implements ParaService {
	ParaDao paradao;
	
	@Resource(name="paradao")
	public void setParadao(ParaDao paradao) {
		this.paradao = paradao;
	}
	
	public ParaDao getParadao() {
		return paradao;
	}
	
	public Paragraph getParaById(int paraid) {
		// TODO Auto-generated method stub	
		Paragraph para = paradao.getParaById(paraid);
		
		return para;
	}

	public boolean changeParaStatus(Paragraph para ){
		return paradao.changeParaStatus(para);
	}
	
	//删除段落
	public boolean deletePara(int paraid){
		return paradao.deletePara(paraid);
	}


}
