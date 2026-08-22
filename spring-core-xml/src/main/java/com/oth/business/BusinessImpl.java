package com.oth.business;

import com.oth.dao.IDao;

public class BusinessImpl implements IBusiness {

	private IDao dao;

//	public BusinessImpl(IDao dao) {
//		this.dao = dao;
//	}

	@Override
	public void fetchData() {
		dao.getData();
	}

	public void setDao(IDao dao) {
		this.dao = dao;
	}
}
