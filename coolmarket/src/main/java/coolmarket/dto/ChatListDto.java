package coolmarket.dto;

import lombok.Data;

@Data
public class ChatListDto {
	
	private int roomNo;
	private int sellerNo;
	private int buyerNo;
	private int marNo;
	private String nickName;
	private String addr1;
	private String addr2;
}
