/**
 * 
 */
package tw.idv.joe.web.fruit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.joe.web.fruit.Service.CartService;
import tw.idv.joe.web.fruit.Service.FruitService;
import tw.idv.joe.web.fruit.entity.Cart;
import tw.idv.joe.web.fruit.entity.Fruit;

/**  
* 
* @ClassName: CartController
* @author:Joe
* @date 2022年12月19日 上午11:03:48
*
*/

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CartService service;
	@Autowired
	FruitService fService;
	
	
	@GetMapping()
	public List<Cart> getAll(){
		return service.selectAll();
	}
	//搜尋購物車內容
	@GetMapping("/search/{name}")
	public List<Cart> selectName(@PathVariable String name){
		return service.selectName(name);
	}
	//查詢該會員購物車內容
	@GetMapping("/get/{memId}")
	public List<Cart> getCart(@PathVariable Integer memId){
		return service.findByMemId(memId);
	}
	@PutMapping("/edit")
	public Cart edit(@RequestBody Cart cart) {
		if (cart == null || cart.getId() == null) {
			cart = new Cart();
			cart.setSuccessful(false);
			cart.setMessage("請輸入資料");
			return cart;
		}
		return service.edit(cart);
	}
	@PostMapping("/add")
	public Cart add(@RequestBody Cart cart) {
		if(cart == null) {
			cart = new Cart();
			cart.setSuccessful(false);
			cart.setMessage("輸入資料為空 請檢查後再送出。");
			return cart;
		}
		return service.addCart(cart);
	}
	
	@DeleteMapping("/delete/{id}")
	public boolean delete(@PathVariable Integer id) {
		return service.deleteById(id);
	}
	@DeleteMapping("/delete/all/{memId}")
	public boolean deleteAll(@PathVariable Integer memId) {
		System.out.println(memId);
		return service.deleteByAll(memId);
	}
	@DeleteMapping("/delete/list/{memId}")
	public boolean deleteCarts(@PathVariable Integer memId, @RequestBody List<Map<String,Object>> list) {
		List<Integer> idList = new ArrayList<>();
		list.forEach(e -> idList.add((Integer) e.get("id")));
		return service.deleteByIds(idList);
	}
	
}
