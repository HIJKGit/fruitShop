/**
 * 
 */
package tw.idv.joe.web.fruit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.joe.core.pojo.Core;
import tw.idv.joe.web.fruit.Service.CartService;
import tw.idv.joe.web.fruit.Service.OrderService;
import tw.idv.joe.web.fruit.entity.Order;

/**  
* 
* @ClassName: OrderController
* @author:Joe
* @date 2022年12月21日 下午4:58:02
*
*/

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService oService;
	@Autowired
	private CartService cService;
	
	@GetMapping()
	public List<Order> getAll(){
		return oService.selectAll();
	}
	
	@GetMapping("/{memId}")
	public List<Order> getAll(@PathVariable Integer memId){
		return oService.selectForMemId(memId);
	}
	@PostMapping("/addOrders/{memId}")
	public boolean addToOrder(@PathVariable Integer memId,@RequestBody List<Map<String,Object>> list) {
		List<Integer> idList = new ArrayList<>();
		list.forEach(e -> idList.add((Integer) e.get("id")));
		oService.addOrders(memId, idList);
		return cService.deleteByIds(idList);
	}
	
	@PostMapping("/addAll/{memId}")
	public Core addAllOrders(@PathVariable Integer memId) {
		Core core = new Core();
		if(cService.findByMemId(memId) == null) {
			core.setMessage("購物車資料為空");
			core.setSuccessful(false);
			return core;
		}
		if(oService.addAllOrder(memId) > 0) {
			core.setMessage("送出成功");
			core.setSuccessful(true);
			return core;
		}else {
			core.setMessage("送出失敗，請確認購物車內容");
			core.setSuccessful(false);
		}
		return core;
	}
	
	@DeleteMapping("/delete/{id}")
	public boolean deleteById(@PathVariable Integer id) {
		return oService.remove(id);
	}
}
