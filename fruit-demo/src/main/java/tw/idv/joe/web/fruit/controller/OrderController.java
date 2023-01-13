/**
 * 
 */
package tw.idv.joe.web.fruit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.joe.core.pojo.Core;
import tw.idv.joe.web.fruit.Service.CartService;
import tw.idv.joe.web.fruit.Service.OrderService;

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
	public Core saveAll(Integer memId) {
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
}
