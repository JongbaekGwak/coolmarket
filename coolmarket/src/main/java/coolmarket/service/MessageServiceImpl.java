package coolmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coolmarket.dto.ChatRoomDto;
import coolmarket.dto.MessageDto;
import coolmarket.mapper.MessageMapper;


@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageMapper messageMapper;
	
	@Override
	public void insertMessage(MessageDto messageDto) {
		
		messageMapper.insertMessage(messageDto);

	}
	
	public List<ChatRoomDto> checkRoom (ChatRoomDto chatRoomDto) throws Exception {
		
		List<ChatRoomDto> chatRoom = messageMapper.checkRoom(chatRoomDto);
		
		if (chatRoom.isEmpty()) {
			messageMapper.createRoom(chatRoomDto);
			chatRoom = messageMapper.checkRoom(chatRoomDto);
//			chatRoom = messageMapper.createRoom(chatRoomNo);
//			List<ChatRoomDto> newChatRoom = messageMapper.createRoom(chatRoomDto);
			
//			if (newChatRoom.isEmpty()) {
//			
//				chatRoom = messageMapper.checkRoom(chatRoomDto);
//			
//			}
		}
		return chatRoom;
		
		
		
	}

}
