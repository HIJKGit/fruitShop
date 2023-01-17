/**
 * 
 */
package tw.idv.joe.web.fruit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tw.idv.joe.web.fruit.entity.Cart;

/**
 * 
 * @ClassName: CartPRepository
 * @author:Joe
 * @date 2022年12月19日 上午9:55:09
 *
 */

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>, CartOperation {

	Cart findByNameAndMemId(String name,Integer memId);

	List<Cart> findByNameContaining(String name);

	@Query(value = "INSERT INTO Cart (memId,name,amount,price) SELECT memId,name,amount,price FROM Cart WHERE memId = :memId AND id IN ([:id])", nativeQuery = true)
	int saveCarts(Integer memId, List<Integer> id);

	void deleteByIdIn(List<Integer> ids);

	@Query(value = "SELECT * FROM Cart WHERE mem_Id = :memId", nativeQuery = true)
	List<Cart> findListByMemId(Integer memId);
	
	@Query(value = "SELECT * FROM Cart WHERE id in ([:ids])" , nativeQuery = true)
	List<Cart> findAllByIds(List<Integer> ids);
	
	@Modifying
	@Query(value = "DELETE FROM Cart WHERE memId = :memId")
	void deleteByMemId(Integer memId);
}
