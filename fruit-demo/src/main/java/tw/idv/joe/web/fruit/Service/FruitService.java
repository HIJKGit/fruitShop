/**
 * 
 */
package tw.idv.joe.web.fruit.Service;

import java.util.List;

import tw.idv.joe.web.fruit.entity.Fruit;

/**  
* 
* @ClassName: FruitService
* @author:Joe
* @date 2022年12月16日 上午10:32:35
*
*/
public interface FruitService {
	List<Fruit> selectForPriceRange(Integer price, Integer price2);
	List<Fruit> selectAll();
	
	List<Fruit> selectForLikeName(String name);
	
	Fruit selectForName(String name);
	boolean removeById(Integer id);
	
	boolean update(Fruit fruit);
	
	Fruit insert(Fruit fruit);
}
