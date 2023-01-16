/**
 * 
 */
package tw.idv.joe.web.fruit.repository;

/**  
* 
* @ClassName: FruitRepository
* @author:Joe
* @date 2022年12月16日 上午10:26:36
*
*/

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.idv.joe.web.fruit.entity.Fruit;
public interface FruitRepository extends JpaRepository<Fruit, Integer>,FruitOperation {

	Fruit findByName(String name);

	@Query(value = "INSERT INTO Cart (memId,name,amount,price) SELECT memId,name,amount,price FROM Fruit WHERE memId = :memId AND id IN :id")
	int saveOrder(Integer memId,Integer[] id);
	
	List<Fruit> findByNameContaining(String name);

	List<Fruit> findByPriceBetween(@Param("price") Integer price, @Param("price2") Integer price2);
}
