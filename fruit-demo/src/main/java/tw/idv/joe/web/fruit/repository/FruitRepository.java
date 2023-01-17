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
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.idv.joe.web.fruit.entity.Fruit;
@Repository
public interface FruitRepository extends JpaRepository<Fruit, Integer>,FruitOperation {

	Fruit findByName(String name);
	
	List<Fruit> findByNameContaining(String name);

	List<Fruit> findByPriceBetween(@Param("price") Integer price, @Param("price2") Integer price2);
}
