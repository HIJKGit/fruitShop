/**
 * 
 */
package tw.idv.joe.web.fruit.Service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import tw.idv.joe.web.fruit.Service.OrderService;
import tw.idv.joe.web.fruit.entity.Cart;
import tw.idv.joe.web.fruit.entity.Fruit;
import tw.idv.joe.web.fruit.entity.Order;
import tw.idv.joe.web.fruit.repository.CartRepository;
import tw.idv.joe.web.fruit.repository.FruitRepository;
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
	@Autowired
	private FruitRepository fRepository;
	@Autowired
	private CartRepository cRepository;

	// 查詢單一會員訂單
	@Override
	public List<Order> selectForMemId(Integer memId) {
		var list = repository.findByMemId(memId);
		if (CollectionUtils.isEmpty(list))
			return Collections.emptyList();
		return list;
	}

	// 單一會員新增當前購物車全部資料
	@Override
	public int addAllOrder(Integer memId) {
		return repository.saveAllOrder(memId);
	}

	// 查詢所有會員訂單
	@Override
	public List<Order> selectAll() {
		return repository.findAll();
	}

	// 新增訂單
	@Transactional
	@Override
	public int addOrders(Integer memId, List<Integer> idList) {
		try {
			repository.saveOrders(memId, idList);
			return 1;
		} catch (Exception ignore) {
			return -1;
		}
	}

	@Transactional
	@Override
	public boolean remove(Integer id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
