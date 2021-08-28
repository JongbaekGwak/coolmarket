package coolmarket.dto;

import lombok.Data;

@Data
public class NoticeDto {
	private int notNo;
	private String notTitle;
	private String notContents;
	private int notView;
	private String notCreaDt;
	private String notUpDt;
}
