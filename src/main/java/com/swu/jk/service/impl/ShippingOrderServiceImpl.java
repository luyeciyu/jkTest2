package com.swu.jk.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swu.jk.dao.ExportDao;
import com.swu.jk.dao.PackingListDao;
import com.swu.jk.dao.ShippingOrderDao;
import com.swu.jk.domain.Contract;
import com.swu.jk.domain.Export;
import com.swu.jk.domain.PackingList;
import com.swu.jk.domain.ShippingOrder;
import com.swu.jk.pagination.Page;
import com.swu.jk.service.ShippingOrderService;

@Service("shippingOrderService")
public class ShippingOrderServiceImpl implements ShippingOrderService{

	@Resource
	private ShippingOrderDao shippingOrderDao;
	@Resource
	private PackingListDao packingListDao;
	@Resource 
	private ExportDao exportDao;

	@Override
	public List<ShippingOrder> findPage(Page page) {
		List<ShippingOrder> shippingOrders = shippingOrderDao.findPage(page);
		return shippingOrders;
	}

	@Override
	public List<ShippingOrder> find(Map params) {
		List<ShippingOrder> shippingOrders = shippingOrderDao.find(params);
		return shippingOrders;
	}

	@Override
	public ShippingOrder get(Serializable id) {
		return shippingOrderDao.get(id);
	}

	@Override
	public void update(ShippingOrder shippingOrder) {
		shippingOrderDao.update(shippingOrder);
	}

	@Override
	public void deleteById(Serializable id) {
		shippingOrderDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		shippingOrderDao.delete(ids);
	}

	@Override
	public void insert(ShippingOrder shippingOrder, String subid) {
		
		if(subid == null || "".equals(subid)){     //新增
			shippingOrderDao.insert(shippingOrder); 
		}else{   //更新
			shippingOrderDao.update(shippingOrder);
		}
		
		//更改报运状态
		
		PackingList packingList = packingListDao.get(shippingOrder.getId());
		String[] exportIds = packingList.getExportIds().split("\\|");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", exportIds);
		map.put("state", 3);
		
		exportDao.updateState(map);
		
		/*Export export;
		List<String> _exportIds = new ArrayList<String>();
		for(int i = 0; i < exportIds.length; i++){
			export = exportDao.get(exportIds[i]);
			export.setState(3);
		}*/
	}
	
	

}
