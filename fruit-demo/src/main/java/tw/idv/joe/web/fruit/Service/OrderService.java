/**
 * 
 */
package tw.idv.joe.web.fruit.Service;

import java.util.List;

import tw.idv.joe.web.fruit.entity.Order;

/**  
* 
* @ClassName: OrderService
* @author:Joe
* @date 2022年12月20日 上午11:59:42
*
*/
public interface OrderService {
	
	List<Order> selectForMemId(Integer memId);
	
	int addAllOrder(Integer memId);
	
	int addOrders(Integer memId, Integer[] id);
	
	List<Order> selectAll();
	
}
