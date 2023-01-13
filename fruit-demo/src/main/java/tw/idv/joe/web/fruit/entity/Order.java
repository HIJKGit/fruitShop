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
import lombok.Data;
import lombok.NoArgsConstructor;

/**  
* 
* @ClassName: Order
* @author:Joe
* @date 2022年12月19日 上午9:37:21
*
*/
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table
@Entity
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "MEM_ID", insertable = false)
	private Integer memId;
	private String name;
	private Integer amount;
	private Integer total;
	
}
