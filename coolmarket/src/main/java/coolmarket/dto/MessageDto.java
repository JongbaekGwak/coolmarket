package coolmarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

	private int roomNo;
	private int msgNo;
	private int marNo;
	private int readYn;
	private int sellerNo;
	private int buyerNo;
	private String sellerName;
	private String buyerName;
	private String sendDt;
	private String msgDelYn;
	private String message;
}
