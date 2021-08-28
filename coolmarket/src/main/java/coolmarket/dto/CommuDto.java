package coolmarket.dto;

import java.util.List;

import lombok.Data;

@Data
public class CommuDto {
	private int comNo;
	private int comUserNo;
	private String comTitle;
	private String comContents;
	private String comCate;
	private String comAddr1;
	private String comAddr2;
	private String comAddr3;
	private int comView;
	private int comLike;
	private int comTalkCnt;
	private String comCreaDt;
	private String comUpDt;
	private String comCreaNickName;
	private List<ImgDto> imgList;
}
