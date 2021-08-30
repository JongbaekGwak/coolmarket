package coolmarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

	msg_no int AI PK 
	mar_no int 
	read_yn int 
	seller_no int 
	buyer_no int 
	seller_name varchar(200) 
	buyer_name varchar(200) 
	send_dt datetime 
	msg_del_yn varchar(45)
	
	private int msgNo;
	private int marNo;
	private int readYn;
	private int sellerNo;
	private int buyerdNo;
	private String msgTitle;
	private String msgContents;
	private String recvDt;
	private String sendDt;
	private String msgDelYn;
}
