package coolmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coolmarket.dto.ChatRoomDto;
import coolmarket.dto.ImgDto;
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
	
//	List<CommuDto> comList = map.getCommuList(marketPageDto);
//	for (int i = 0; i < comList.size(); i++) {
//		List<ImgDto> imgList = map.comImgList(comList.get(i).getComNo());
//		comList.get(i).setImgList(imgList);
//	}
//	return comList;
	public MarketDto getContactItem(int marNo) throws Exception {
		MarketDto marketItem = messageMapper.getContactItem(marNo);
		List<ImgDto> imgList = messageMapper.getContactImgList(marNo);
		marketItem.setImgList(imgList);

		return marketItem;
	}
	
	
	public void deleteMsg(int roomNo) throws Exception {
		messageMapper.deleteMsg(roomNo);
		messageMapper.deleteRoom(roomNo);
	}

}
