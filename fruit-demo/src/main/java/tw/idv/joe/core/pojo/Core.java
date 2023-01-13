/**
 * 
 */
package tw.idv.joe.core.pojo;

import lombok.Data;
import java.io.Serializable;
import tw.idv.joe.core.pojo.Core;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**  
* 
* @ClassName: Core
* @author:Joe
* @date 2022年12月16日 上午11:15:42
*
*/
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class Core implements Serializable {
	private static final long serialVersionUID = -1L;
	private boolean successful;
	private String message;

	public Core() {
	}

	public Core(boolean successful, String message) {
		this.successful = successful;
		this.message = message;
	}
}
