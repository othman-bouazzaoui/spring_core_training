package com.oth.business;

import com.oth.dao.IDao;

public class BusinessImpl implements IBusiness {

	private IDao dao;

	@Override
	public void fetchData() {
		dao.GetData();
	}

	public void setDao(IDao dao) {
		this.dao = dao;
	}
}
