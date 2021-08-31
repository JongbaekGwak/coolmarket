package coolmarket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import coolmarket.dto.ChatRoomDto;
import coolmarket.dto.MessageDto;


@Mapper
public interface MessageMapper {

	void insertMessage(MessageDto messageDto);
	
	List<ChatRoomDto> checkRoom(ChatRoomDto chatRoomDto) throws Exception;
	int createRoom(ChatRoomDto chatRoomDto) throws Exception;
//	List<ChatRoomDto> checkRoom(@Param("roomNo") int chatRoomNo) throws Exception;
	
}
