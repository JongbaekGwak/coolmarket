package coolmarket.dto;

import lombok.Data;

@Data
public class MsgDto {
	private int msgNo;
	private int marNo;
	private int read;
	private int recvNo;
	private int sendNo;
	private String msgTitle;
	private String msgContents;
	private String recvDt;
	private String sendDt;
}
