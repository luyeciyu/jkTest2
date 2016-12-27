package com.swu.jk.dao;

import com.swu.jk.domain.PackingList;
import com.swu.jk.vo.PackingListVO;

public interface PackingListDao extends BaseDao<PackingList>{

	PackingListVO view(String id);

}
