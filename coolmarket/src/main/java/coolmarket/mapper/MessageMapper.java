package coolmarket.mapper;

import org.apache.ibatis.annotations.Mapper;

import coolmarket.dto.MessageDto;


@Mapper
public interface MessageMapper {

	void insertMessage(MessageDto messageDto);
	
}
