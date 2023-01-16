/**
 * 
 */
package tw.idv.joe.web.fruit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tw.idv.joe.web.fruit.entity.Cart;

/**
 * 
 * @ClassName: CartPRepository
 * @author:Joe
 * @date 2022年12月19日 上午9:55:09
 *
 */

public interface CartRepository extends JpaRepository<Cart, Integer>, CartOperation {

	Cart findByName(String name);

	List<Cart> findByNameContaining(String name);

	@Query(value = "INSERT INTO Cart (memId,name,amount,price) SELECT memId,name,amount,price FROM Cart WHERE memId = :memId AND id IN :id")
	int saveCarts(Integer memId, Integer[] id);

	@Query(value = "DELETE FROM Cart where id IN :id")
	boolean deleteCart(Integer[] id);

	@Query(value = "SELECT * FROM CART WHERE mem_Id = :memId", nativeQuery = true)
	List<Cart> findListByMemId(Integer memId);
}
