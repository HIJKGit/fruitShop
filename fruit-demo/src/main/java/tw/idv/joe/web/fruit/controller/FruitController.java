/**
 * 
 */
package tw.idv.joe.web.fruit.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RestController
@RequestMapping("/fruit")
public class FruitController {
	@Autowired
	FruitService service;
	
	@GetMapping
	public List<Fruit> getInfo() {
		return service.selectAll();
	}

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

	@GetMapping("/search/price")
	public List<Fruit> searchList(Integer price, Integer price2) {
		var list = service.selectForPriceRange(price, price2);
		var list2 = new ArrayList<Fruit>();
		list.stream().forEach(e -> {
									e.setSuccessful(true);
									e.setMessage("查詢成功，以下為價格：");
									list2.add(e);
									});
									return list2;
	}

	@PostMapping("/add")
	public Fruit add(@RequestBody Fruit fruit) {
		return service.insert(fruit);
	}
	@DeleteMapping("/delete/{id}")
	public boolean delete(@PathVariable Integer id) {
		return service.removeById(id);
	}
}
