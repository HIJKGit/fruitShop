/**
 * 
 */
package tw.idv.joe.web.fruit.repository;

import tw.idv.joe.web.fruit.entity.Fruit;

/**  
* 
* @ClassName: FruitOperation
* @author:Joe
* @date 2022年12月19日 下午2:06:21
*
*/
public interface FruitOperation {
	
	//更新水果數量及價格
	int update(Fruit fruit);
}
