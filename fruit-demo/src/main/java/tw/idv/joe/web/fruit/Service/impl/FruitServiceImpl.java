/**
 * 
 */
package tw.idv.joe.web.fruit.Service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.idv.joe.web.fruit.Service.FruitService;
import tw.idv.joe.web.fruit.entity.Fruit;
import tw.idv.joe.web.fruit.repository.FruitRepository;

/**
 * 
 * @ClassName: FruitServiceImpl
 * @author:Joe
 * @date 2022年12月16日 上午10:57:28
 *
 */

@Service
public class FruitServiceImpl implements FruitService {
	@Autowired
	FruitRepository repository;

	@Override
	public List<Fruit> selectAll() {
		return repository.findAll();
	}

	@Override
	public Fruit selectForName(String name) {
		return repository.findByName(name);
	}

	
	@Override
	public boolean removeById(Integer id) {
		try {
			repository.deleteById(id);			
			return true;
		} catch (Exception ignore) {
			return false;
		}
	}

	@Transactional
	@Override
	public boolean update(Fruit fruit) {
		return repository.update(fruit) > 0;
	}

	@Transactional
	@Override
	public Fruit insert(Fruit fruit) {
		if (fruit == null) {
			fruit = new Fruit();
			fruit.setSuccessful(false);
			fruit.setMessage("無水果資訊");
			return fruit;
		}
		final String name = fruit.getName();
		final Integer amount = fruit.getAmount();
		final byte[] image = fruit.getImage();
		final Integer price = fruit.getPrice();
		Fruit oFruit = repository.findByName(name);
		if(oFruit != null) {
			fruit.setMessage("此品名已有人註冊");
			fruit.setSuccessful(false);
			return fruit;
		}
		if (name.isBlank()) {
			fruit.setMessage("請輸入品名");
			fruit.setSuccessful(false);
			return fruit;
		}
		if (amount == null || amount <= 0) {
			fruit.setMessage("數量不可為空或是低於一");
			fruit.setSuccessful(false);
			return fruit;
		}
		if (price == null || price <= 0) {
			fruit.setMessage("價格不可為空或是低於一");
			fruit.setSuccessful(false);
			return fruit;
		}
//		if (image == null || image.length == 0) {
//			fruit.setMessage("請上傳圖片");
//			fruit.setSuccessful(false);
//			return fruit;
//		}
		fruit.setSuccessful(true);
		fruit.setMessage("新增成功");
		return repository.save(fruit);
	}

	@Override
	public List<Fruit> selectForLikeName(String name) {
		List<Fruit> list = new ArrayList<>();
		for(var obj : repository.findByNameContaining(name)) {
			obj.setSuccessful(true);
			obj.setMessage("查詢成功");
			list.add(obj);
		}
		return list;
	}

	@Override
	public List<Fruit> selectForPriceRange(Integer price, Integer price2) {
		if(price == null || price2 == null)
			return Collections.emptyList();
		return repository.findByPriceBetween(price, price2);
	}

	@Override
	public Optional<Fruit> selectForId(Integer id) {
		return repository.findById(id);
	}

}
