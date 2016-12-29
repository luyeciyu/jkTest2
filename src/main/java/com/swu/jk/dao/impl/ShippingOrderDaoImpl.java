package com.swu.jk.dao.impl;

import org.springframework.stereotype.Repository;

import com.swu.jk.dao.ShippingOrderDao;
import com.swu.jk.domain.ShippingOrder;

@Repository("shippingOrderDao")
public class ShippingOrderDaoImpl extends BaseDaoImpl<ShippingOrder> implements ShippingOrderDao{

	public ShippingOrderDaoImpl() {
		super.setNs("com.swu.jk.mapper.ShippingOrderMapper");
	}
}
