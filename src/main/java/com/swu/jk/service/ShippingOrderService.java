package com.swu.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.swu.jk.domain.Contract;
import com.swu.jk.domain.ShippingOrder;
import com.swu.jk.pagination.Page;

public interface ShippingOrderService {
	public List<ShippingOrder> findPage(Page page);
	public List<ShippingOrder> find(Map params);
	public ShippingOrder get(Serializable id);
	public void update(ShippingOrder shippingOrder);
	public void deleteById(Serializable id);
	public void delete(Serializable[] ids);
	public void insert(ShippingOrder shippingOrder, String subid);
}
