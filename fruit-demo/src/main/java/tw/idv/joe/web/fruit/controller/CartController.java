/**
 * 
 */
package tw.idv.joe.web.fruit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.joe.web.fruit.Service.CartService;
import tw.idv.joe.web.fruit.entity.Cart;

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
	
	@GetMapping("/{name}")
	public List<Cart> selectName(@PathVariable String name){
		return service.selectName(name);
	}
	@GetMapping("/get/{id}")
	public List<Cart> getByMemId(@PathVariable Integer id){
		return service.findByMemId(id);
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
	public Cart insert(@RequestBody Cart cart) {
		if(cart == null) {
			cart = new Cart();
			cart.setSuccessful(false);
			cart.setMessage("輸入資料為空 請檢查後再送出。");
			return cart;
		}
		cart = service.addCart(cart);
		return cart;
	}
}
