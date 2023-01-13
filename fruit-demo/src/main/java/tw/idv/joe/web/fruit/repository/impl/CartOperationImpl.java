/**
 * 
 */
package tw.idv.joe.web.fruit.repository.impl;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;

import tw.idv.joe.web.fruit.entity.Cart;
import tw.idv.joe.web.fruit.repository.CartOperation;

/**  
* 
* @ClassName: CartOperationImpl
* @author:Joe
* @date 2022年12月19日 上午10:22:36
*
*/

public class CartOperationImpl implements CartOperation{
	@PersistenceContext
	private Session session;
	
	@Transactional
	@Override
	public int update(Cart cart) {
		final StringBuilder hql = new StringBuilder("UPDATE Cart Set ");
		final String name = cart.getName();
		final Integer price = cart.getPrice();
		final Integer amount = cart.getAmount();
		if(name == null || name.isBlank())
			return -1;
		
		if(price != null)
			hql.append("price = :price,");
		
		if(amount != null)
			hql.append("amount = :amount, ");
		hql.append("name = :name ");
		hql.append("where id = :id");
		
		Query query = session.createQuery(hql.toString());
		if(price != null)
			query.setParameter("price", price);
	
		if(amount != null)
			query.setParameter("amount", amount);
		return query.setParameter("name", cart.getName())
					.setParameter("id", cart.getId())
					.executeUpdate();
	}

}
