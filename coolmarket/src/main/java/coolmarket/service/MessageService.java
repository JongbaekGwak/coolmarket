package coolmarket.service;

import java.util.List;

import coolmarket.dto.ChatRoomDto;
import coolmarket.dto.MessageDto;

public interface MessageService {

	void insertMessage(MessageDto messageDto);
	
	List<ChatRoomDto> checkRoom(ChatRoomDto chatRoomDto) throws Exception;
}
