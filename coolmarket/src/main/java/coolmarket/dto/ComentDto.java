package coolmarket.dto;

import lombok.Data;

@Data
public class ComentDto {
	private int comentNo;
	private int comentComNo;
	private int comentUserNo;
	private String comentNickName;
	private String comentContents;
	private String comentCreaDt;
}
