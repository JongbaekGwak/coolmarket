package coolmarket.dto;

import java.util.List;

import lombok.Data;

@Data
public class AdDto {
	private int adNo;
	private int adUserNo;
	private String adTitle;
	private String adContents;
	private String adAddr1;
	private String adAddr2;
	private String adAddr3;
	private int adView;
	private int adLike;
	private int adTalkCnt;
	private String adCreaDt;
	private String adUpDt;
	private String adCate;
	private String adCreaNickName;
	private List<ImgDto> imgList;
}
