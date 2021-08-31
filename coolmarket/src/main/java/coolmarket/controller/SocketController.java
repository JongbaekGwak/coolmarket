package coolmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import coolmarket.dto.MessageDto;
import coolmarket.service.MessageService;



@Controller

public class SocketController {
	
	@Autowired
	private MessageService messageService;
	@Autowired
	private SimpMessagingTemplate template;
	// 메세지를 받을 EndPoint
	@MessageMapping("/receive")
	
	// /send로 메세지를 반환
//	밑에 convertAndSend쓸거라 필요 없을 듯
//	@SendTo("/send")
	
	public void MessageHandler(MessageDto messageDto) {
		
//		String userName = messageDto.getUserName();
//		String content = messageDto.getContent();
//		MessageDto result = new MessageDto(userName, content);
//		return result;
		
		// db에 저장하기
		messageService.insertMessage(messageDto);
		// 메세지? 데이터? 를 반환하는 부분인거 같은데 simptemplate있으면 위에 @SendTo 어노테이션은 필요없겠네
//		template.convertAndSend("/send/" + chatMessage.getChatroomId(), ch	atMessage);
//		template.convertAndSend("/send/" + messageDto);
		template.convertAndSend("/send/" + 1, messageDto);
		
	}
}
