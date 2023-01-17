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

	List<Cart> findByMemId(Integer memId);

	List<Cart> selectAllByIds(List<Integer> ids);

	Cart edit(Cart cart);

	Cart addCart(Cart cart);

	boolean deleteById(Integer id);


	boolean deleteByIds(List<Integer> id);


	boolean deleteByAll(Integer memId);
}
