package tw.idv.joe.web.fruit.Service;

import java.util.List;


import tw.idv.joe.web.fruit.entity.Cart;

/**  
* 
* @ClassName: CartService
* @author:Joe
* @date 2022年12月19日 上午10:03:03
*
*/
public interface CartService {
	
	List<Cart> selectAll();
	
	List<Cart> selectName(String name);
	
	Cart edit(Cart cart);
	
	boolean deleteById(Integer id);
	
	Cart addCart(Cart cart);
	
	List<Cart> findByMemId(Integer memId);
	
}
