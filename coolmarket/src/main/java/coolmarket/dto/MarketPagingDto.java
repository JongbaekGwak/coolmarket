package coolmarket.dto;

import lombok.Data;

@Data
public class MarketPagingDto {
	private String selected;
	private String addr1;
	private String addr2;
	private int marStartNum;
	private int marTotalNum;
	private int adStartNum;
	private int adTotalNum;
	private int comStartNum;
	private int comTotalNum;
}
