package coolmarket.dto;

import lombok.Data;

@Data
public class ImgDto {
	private int imgNo;
	private String marNo;
	private String comNo;
	private String oriImgName;
	private String storedImgPath;
	private long imgSize;
}
