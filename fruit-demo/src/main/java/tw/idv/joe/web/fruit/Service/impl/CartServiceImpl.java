/**
* 
*/
package tw.idv.joe.web.fruit.Service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.idv.joe.web.fruit.Service.CartService;
import tw.idv.joe.web.fruit.entity.Cart;
import tw.idv.joe.web.fruit.entity.Fruit;
import tw.idv.joe.web.fruit.repository.CartRepository;
import tw.idv.joe.web.fruit.repository.FruitRepository;

/**
 * 
 * @ClassName: CartServiceImpl
 * @author:Joe
 * @date 2022年12月19日 上午10:05:52
 *
 */
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	CartRepository cRepository;
	@Autowired
	FruitRepository fRepository;

	@Override
	public List<Cart> selectAll() {
		return cRepository.findAll();
	}

	@Override
	public List<Cart> selectName(String name) {
		return cRepository.findByNameContaining(name);
	}

	@Transactional
	@Override
	public Cart edit(Cart cart) {
		Optional<Cart> oCart = cRepository.findById(cart.getId());
		if (oCart.isEmpty()) {
			cart.setSuccessful(false);
			cart.setMessage("查無該筆購物車訂單");
			return cart;
		}
		Cart eCart = oCart.get();
		// 檢查數量
		final Optional<Integer> amount = Optional.ofNullable(cart.getAmount());
		if (amount.isEmpty() || amount.get() < 1) {
			cart.setSuccessful(false);
			cart.setMessage("數量請勿為空或低於1");
			return cart;
		}
		// 驗證是否有該筆水果資料
		Fruit fruit = fRepository.findByName(eCart.getName());
		if (fruit == null) {
			cart.setSuccessful(false);
			cart.setMessage("查無該筆水果訂單！");
			return cart;
		}
		// 檢查水果庫存 
		if(fruit.getAmount() < amount.get()) {
			cart.setSuccessful(false);
			cart.setMessage("水果庫存不足！");
			return cart;
		}
		eCart.setAmount(amount.get());
		if(cRepository.update(eCart) > 0) {
			eCart.setSuccessful(true);
			eCart.setMessage("更改成功！");
		}
		return eCart;
	}

	@Transactional
	@Override
	public boolean deleteById(Integer id) {
		cRepository.deleteById(id);
		return true;
	}

	@Override
	public Cart addCart(Cart cart) {
		if (cart == null) {
			cart = new Cart();
			cart.setMessage("資料為空 無法新增");
			cart.setSuccessful(false);
			return cart;
		}
		final String name = cart.getName();
		final Integer price = cart.getPrice();
		final Integer amount = cart.getAmount();
		Fruit fruit = fRepository.findByName(name);
		// 檢查資料庫是否有此項水果
		if (fruit == null) {
			cart.setMessage("查無當前水果資料");
			cart.setSuccessful(false);
			return cart;
		}
		if (name == null || name.isBlank()) {
			cart.setMessage("請輸入水果名");
			cart.setSuccessful(false);
			return cart;
		}
		// 檢核價格是否無誤
		if (price == null || !Objects.equals(price, fruit.getPrice())) {
			cart.setMessage("請輸入正確價格");
			cart.setSuccessful(false);
			return cart;
		}
		if (amount == null || amount <= 0) {
			cart.setMessage("請輸入數量");
			cart.setSuccessful(false);
			return cart;
		}

		// 檢查水果庫存量
		if (fruit.getAmount() <= amount) {
			cart.setMessage("此商品庫存不足 請刷新頁面後查看剩餘庫存");
			cart.setSuccessful(false);
			return cart;
		}
		cRepository.save(cart);
		cart.setSuccessful(true);
		cart.setMessage("新增成功");
		return cart;
	}

	@Override
	public List<Cart> findByMemId(Integer memId) {
		return cRepository.findListByMemId(memId);
	}

}
