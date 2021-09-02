package coolmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coolmarket.dto.ChatRoomDto;
import coolmarket.dto.MarketDto;
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

		}
		return chatRoom;
	}
	
	public List<MessageDto> getChatList(int roomNo) throws Exception {
		
		return messageMapper.getChatList(roomNo);
	}
	
	public List<MessageDto> getRoomList(int buyerNo) throws Exception {
		
		return messageMapper.getRoomList(buyerNo);
	}
	
	public MarketDto getContactItem(int marNo) throws Exception {
		
		return messageMapper.getContactItem(marNo);
	}

}
