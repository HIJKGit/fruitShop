/**
 * 
 */
package tw.idv.joe.web.fruit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tw.idv.joe.web.fruit.entity.Order;

/**
 * 
 * @ClassName: OrderRepository
 * @author:Joe
 * @date 2022年12月20日 上午11:11:04
 *
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	@Query(value = "INSERT INTO Order (memId,name,amount,total) SELECT memId,name,amount,price*amount FROM Cart WHERE memId = :memId", nativeQuery = true)
	int saveAllOrder(Integer memId);

	@Modifying
	@Query(value = "INSERT INTO Order (memId,name,amount,total) SELECT memId,name,amount,price*amount FROM Cart WHERE memId = :memId AND id IN :ids")
	int saveOrders(Integer memId, List<Integer> ids);

	List<Order> findByMemId(Integer id);
}
