/**
 * 
 */
package tw.idv.joe.web.fruit.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.joe.web.fruit.Service.FruitService;
import tw.idv.joe.web.fruit.entity.Fruit;

/**
 * 
 * @ClassName: FruitController
 * @author:Joe
 * @date 2022年12月16日 上午11:13:27
 *
 */
// 加在方法或類別名皆可
//@CrossOrigin("http://127.0.0.1:5500/")
@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/")
public class FruitController {
	@Autowired
	FruitService service;

	// 查詢所有水果
	@GetMapping
	public List<Fruit> getInfo() {
		return service.selectAll();
	}

	// 找尋水果名稱 (明確比對)
	@GetMapping("/search/{name}")
	public Fruit search(@PathVariable String name) {
		Fruit fruit = new Fruit();
		if (name.isBlank()) {
			fruit.setSuccessful(false);
			fruit.setMessage("輸入內容為空！");
			return fruit;
		}
		fruit = service.selectForName(name);
		if (fruit == null) {
			fruit = new Fruit();
			fruit.setSuccessful(false);
			fruit.setMessage("查無資料！");
			return fruit;
		}

		fruit.setMessage("查詢成功！");
		fruit.setSuccessful(true);
		return fruit;
	}

	// 查詢水果名 (模糊比對)
	@GetMapping("/searchForLike/{name}")
	public List<Fruit> searchList(@PathVariable String name) {
		List<Fruit> fruit = new ArrayList<>();
		if (name.isBlank()) {
			return Collections.emptyList();
		}
		fruit = service.selectForLikeName(name);
		if (fruit.isEmpty()) {
			return Collections.emptyList();
		}
		return fruit;
	}

	// 查詢在價格區間的水果
	@GetMapping("/search/price/{price},{price2}")
	public List<Fruit> searchList(@PathVariable Integer price, @PathVariable Integer price2) {
		var list = service.selectForPriceRange(price, price2);
		var list2 = new ArrayList<Fruit>();
		list.stream().forEach(e -> {
			e.setSuccessful(true);
			e.setMessage("查詢成功");
			list2.add(e);
		});
		return list2;
	}

	// 新增一筆水果
	@PostMapping("/add")
	public Fruit add(@RequestBody Fruit fruit) {
		return service.insert(fruit);
	}

	// 刪除一筆水果
	@DeleteMapping("/delete/{id}")
	public boolean delete(@PathVariable Integer id) {
		return service.removeById(id);
	}

	// 修改一個水果
	@PutMapping("/update")
	public boolean update(@RequestBody Fruit fruit) {
		if (fruit == null) {
			return false;
		}
		return service.update(fruit);
	}
}
