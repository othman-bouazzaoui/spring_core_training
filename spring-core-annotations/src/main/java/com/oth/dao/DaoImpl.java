package com.oth.dao;

import org.springframework.stereotype.Repository;

@Repository("dao")
public class DaoImpl implements IDao {
	@Override
	public void getData() {
		System.out.println("Fetch Data from DB .....");
	}
}
