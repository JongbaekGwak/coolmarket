package coolmarket.dto;

import java.util.List;

import lombok.Data;

@Data
public class MarketDto {
	private int marNo;
	private int marUserNo;
	private int marBuyUserNo;
	private String marTitle;
	private String marContents;
	private String marCate;
	private String marPrice;
	private String marAddr1;
	private String marAddr2;
	private String marAddr3;
	private int marView;
	private int marLike;
	private int marTalkCnt;
	private String marCreaDt;
	private String marUpDt;
	private String marCreaNickName;
	private String adCate;
	private List<ImgDto> imgList;
}
