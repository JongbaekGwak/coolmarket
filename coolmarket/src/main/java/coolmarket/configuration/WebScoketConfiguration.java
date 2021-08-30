package coolmarket.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebScoketConfiguration implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/")
				.setAllowedOrigins("http://localhost:8080")  // "*"로 설정시 cors 해제 왜 안됨 ?
				.withSockJS();
				//.setHeartbeatTime(20000); // 소켓 연결상태 확인 주기 2초.
	}
	
	// 컨트롤러와 브로커가 있을때 클라이언트에서 받은 메세지는 브로커에 먼저 ? 컨트롤러에 먼저 ? 
	// 아.. 여기는 그냥 설정 부분 ??  얘가 실제로 메세지를 받고 보내주는게 아니라 받고 보내는 주소를 설정만 하는 ??
	// 그럼 기본 url을 여기서 지정하고 컨트롤러에서 mapping주소를 다르게해서 여러 컨트롤러를 만드는건가.. 컨트롤러 여러개 만들어서 어따쓰지 ..?
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// publish, destination(목적지)이 /pub으로 된 메세지를 Coㅜtroller의 MessageMapping으로 라우팅한다
		// 현재 컨트롤러의 MessageMapping은 /receive,  클라이언트에서 stompClient.send로 보낼때 주소가 /pub/receive가 됨
		// 컨트롤러의 MessageMapping만 있으면 될텐데 이건 왜 필요한거 ??
		// 위의 addEndpoint까지 있으면 주소는 /add/pub/receive가 되는데 ??
		registry.setApplicationDestinationPrefixes("/pub"); 
		// 클라이언트의 메세지 구독 endpoint
		// 웹소켓의 입장에서 /send를 subscribe(구독하는/요청하는)하는 클라이언트에게 메세지를 보낸다 ? 
		registry.enableSimpleBroker("/send"); //현재 컨트롤러의 @SendTo("/send") ?

		// 여튼 /pub+컨트롤러의 mapping 으로 클라이언트가 보낸걸 받고 /send로 구독 요청하는 클라이언트에게 보내준다 ?
	}
	
	
}
