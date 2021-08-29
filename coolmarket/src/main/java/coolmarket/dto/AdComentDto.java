package coolmarket.dto;

import lombok.Data;

@Data
public class AdComentDto {
	private int adComentNo;
	private int adComentAdNo;
	private int adComentUserNo;
	private String adComentNickName;
	private String adComentContents;
	private String adComentCreaDt;
}
