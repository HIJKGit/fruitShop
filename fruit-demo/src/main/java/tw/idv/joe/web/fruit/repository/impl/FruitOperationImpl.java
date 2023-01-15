/**
 * 
 */
package tw.idv.joe.web.fruit.repository.impl;

import java.util.Optional;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;

import tw.idv.joe.web.fruit.entity.Fruit;
import tw.idv.joe.web.fruit.repository.FruitOperation;

/**
 * 
 * @ClassName: FruitOprationImpl
 * @author:Joe
 * @date 2022年12月19日 下午2:08:57
 *
 */
public class FruitOperationImpl implements FruitOperation {
	@PersistenceContext
	private Session session;
	
	@Transactional
	@Override
	public int update(Fruit fruit) {
		final StringBuilder hql = new StringBuilder("UPDATE Fruit SET ");
		final Optional<String> name = Optional.ofNullable(fruit.getName());
		final Optional<Integer> price = Optional.ofNullable(fruit.getPrice());
		final Optional<Integer> amount = Optional.ofNullable(fruit.getAmount());
		if(fruit.getId() == null)
			return -1;
		if (name.isPresent())
			hql.append("name = :name,");
		if (price.isPresent())
			hql.append("price = :price,");
		if (amount.isPresent())
			hql.append("amount = :amount,");
		hql.append("lastUpdatedDate = NOW() ");
		hql.append("where id = :id");
		Query query = session.createQuery(hql.toString());
		if (name.isPresent())
			query.setParameter("name", name.get());
		if (price.isPresent())
			query.setParameter("price", price.get());
		if (amount.isPresent())
			query.setParameter("amount", amount.get());
		return query.setParameter("id", fruit.getId())
					.executeUpdate();
	}

}
