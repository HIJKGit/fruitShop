/**
 * 
 */
package tw.idv.joe.web.fruit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tw.idv.joe.core.pojo.Core;

/**  
* 
* @ClassName: cart
* @author:Joe
* @date 2022年12月19日 上午9:34:16
*
*/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
@Entity
public class Cart extends Core{
	private static final long serialVersionUID = 450252964669563716L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "MEM_ID",insertable = false)
	private Integer memId;
	private String name;
	private Integer price;
	private Integer amount;
}
