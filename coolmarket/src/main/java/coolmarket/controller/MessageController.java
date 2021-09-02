package coolmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import coolmarket.dto.ChatRoomDto;
import coolmarket.service.MessageService;

@RestController
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@GetMapping("/checkRoom")
	public Object CheckRoom(ChatRoomDto chatRoomDto) throws Exception {
		
		return messageService.checkRoom(chatRoomDto);
	}
	
	@GetMapping("/getChatList")
	public Object getChatList(int roomNo) throws Exception {
		
		return messageService.getChatList(roomNo);
	}
	
	@GetMapping("/getRoomList")
	public Object getRoomList(int buyerNo) throws Exception {
		
		return messageService.getRoomList(buyerNo);
	}
	
	@GetMapping("/getContactItem")
	public Object getContactItem(int marNo) throws Exception {
		
		return messageService.getContactItem(marNo);
	}
}
