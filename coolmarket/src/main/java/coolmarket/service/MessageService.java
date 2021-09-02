package coolmarket.service;

import java.util.List;

import coolmarket.dto.ChatRoomDto;
import coolmarket.dto.MarketDto;
import coolmarket.dto.MessageDto;

public interface MessageService {

	void insertMessage(MessageDto messageDto);
	
	List<ChatRoomDto> checkRoom(ChatRoomDto chatRoomDto) throws Exception;
	
	List<MessageDto> getChatList(int roomNo) throws Exception;
	
	List<MessageDto> getRoomList(int buyerNo) throws Exception;
	
	MarketDto getContactItem(int marNo) throws Exception;
	
	
}
