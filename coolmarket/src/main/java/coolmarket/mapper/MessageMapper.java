package coolmarket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import coolmarket.dto.ChatRoomDto;
import coolmarket.dto.MarketDto;
import coolmarket.dto.MessageDto;


@Mapper
public interface MessageMapper {

	void insertMessage(MessageDto messageDto);
	
	List<ChatRoomDto> checkRoom(ChatRoomDto chatRoomDto) throws Exception;
	
	int createRoom(ChatRoomDto chatRoomDto) throws Exception;

	List<MessageDto> getChatList(@Param("roomNo") int roomNo) throws Exception;

	List<MessageDto> getRoomList(@Param("buyerNo") int buyerNo) throws Exception;
	
	MarketDto getContactItem(@Param("marNo") int marNo) throws Exception;
	
}
