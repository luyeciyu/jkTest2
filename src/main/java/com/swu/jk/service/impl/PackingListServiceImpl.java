package com.swu.jk.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swu.jk.dao.ExportDao;
import com.swu.jk.dao.PackingListDao;
import com.swu.jk.domain.Export;
import com.swu.jk.domain.PackingList;
import com.swu.jk.pagination.Page;
import com.swu.jk.service.PackingListService;
import com.swu.jk.vo.PackingListVO;

@Service
public class PackingListServiceImpl implements PackingListService{

	@Resource
	private PackingListDao packingListDao;
	@Resource
	private ExportDao exportDao;
	
	@Override
	public List<PackingList> findPage(Page page) {
		List<PackingList> packingLists = packingListDao.findPage(page);
		return packingLists;
	}

	@Override
	public List<PackingList> find(Map paraMap) {
		List<PackingList> packingLists = packingListDao.find(paraMap);
		return packingLists;
	}

	@Override
	public PackingList get(Serializable id) {
		return packingListDao.get(id);
	}

	@Override
	public void insert(PackingList packingList) {
		packingList.setId(UUID.randomUUID().toString());
		packingListDao.insert(packingList);
	}

	@Override
	public void update(PackingList packingList) {
		packingListDao.update(packingList);
	}

	@Override
	public void deleteById(Serializable id) {
//		packingListDao.deleteById(id);
		
	}

	@Override
	public void delete(Serializable[] ids) {
		
		//级联删除   未实现
		packingListDao.delete(ids);
	}

	@Override
	public PackingListVO view(String id) {
		return packingListDao.view(id);
	}

	@Override
	public List<Map> getExportToPackingListData(String[] ids) {
		List<Map> data = new ArrayList<Map>();
		Map<String, Object> map = null;
		Export export = null;
		for(int i = 0; i < ids.length; i++){
			map = new HashMap<String, Object>();
			export = exportDao.get(ids[i]);
			map.put("exportId", ids[i]);
			map.put("exportNo", export.getCustomerContract());
			data.add(map);
		}
		
		return data;
	}

}
