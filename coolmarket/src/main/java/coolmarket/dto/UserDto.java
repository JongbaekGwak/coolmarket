package coolmarket.dto;

import lombok.Data;

@Data
public class UserDto {
	private int userNo;
	private String email;
	private String password;
	private String name;
	private String nickName;
	private String tel;
	private String addr1;
	private String addr2;
	private String addr3;
	private int rank;
	private String preDt;
	private String userCreaDt;
}
