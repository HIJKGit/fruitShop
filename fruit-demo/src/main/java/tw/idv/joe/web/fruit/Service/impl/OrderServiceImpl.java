/**
 * 
 */
package tw.idv.joe.web.fruit.Service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import tw.idv.joe.web.fruit.Service.OrderService;
import tw.idv.joe.web.fruit.entity.Order;
import tw.idv.joe.web.fruit.repository.OrderRepository;

/**
 * 
 * @ClassName: OrderServiceImpl
 * @author:Joe
 * @date 2022年12月20日 下午2:10:09
 *
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository repository;
	
	//查詢單一會員訂單
	@Override
	public List<Order> selectForMemId(Integer memId) {
		var list = repository.findByMemId(memId);
		if (CollectionUtils.isEmpty(list))
			return Collections.emptyList();
		return list;
	}

	//單一會員新增當前購物車全部資料
	@Override
	public int addAllOrder(Integer memId) {
		return repository.saveAllOrder(memId);
	}
	
	//查詢所有會員訂單
	@Override
	public List<Order> selectAll() {
		return repository.findAll();
	}
	
	//新增訂單 可為多數或少數
	@Override
	public int addOrders(Integer memId, Integer[] id) {
		try {
			repository.saveOrder(memId, id);
			return 1;
		} catch (Exception ignore) {
			return -1;
		}
	}
	
	
}
