package com.oth.business;

import com.oth.dao.IDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("business")
public class BusinessImpl implements IBusiness {

	/**
	 * We can inject the bean using @Autowired but not recommended because we won't respect the goode practices
	 * So it's recommended to use constructor injection
	 */
	//@Autowired
	private final IDao dao;

	public BusinessImpl(@Qualifier("dao") IDao dao) {
		this.dao = dao;
	}

	@Override
	public void fetchData() {
		dao.GetData();
	}

}
