/**
 * 
 */
package tw.idv.joe.web.fruit.repository;


import java.util.List;

import tw.idv.joe.web.fruit.entity.Cart;

/**  
* 
* @ClassName: OrderOperation
* @author:Joe
* @date 2022年12月21日 下午3:29:45
*
*/
public interface OrderOperation {
	int insertAll(List<Cart> cart);
}
