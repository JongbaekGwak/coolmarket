package coolmarket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import coolmarket.dto.ImgDto;
import coolmarket.dto.MarketDto;

@Mapper
public interface WriteMapper {
	void marketWrite(MarketDto market) throws Exception;

	String marketWriteRe(int marUserNo) throws Exception;

	void imgInsert(List<ImgDto> list) throws Exception;
}
