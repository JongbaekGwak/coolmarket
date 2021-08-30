package coolmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
