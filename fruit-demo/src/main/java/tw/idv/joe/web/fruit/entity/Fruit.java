package tw.idv.joe.web.fruit.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tw.idv.joe.core.pojo.Core;

/**  
* 
* @ClassName: Fruit
* @author:Joe
* @date 2022年12月16日 上午9:15:47
*
*/

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fruit extends Core{
	private static final long serialVersionUID = 5704790026598983854L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer price;
	private Integer amount;
	private byte[] image;
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	@Column(name = "CREATED_DATE", insertable = false, updatable = false)
	private Timestamp createdDate;
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	@Column(name = "LAST_UPDATED_DATE", insertable = false, columnDefinition = "DATETIME DEFAULT NOW()")
	private Timestamp lastUpdatedDate;
	
}
