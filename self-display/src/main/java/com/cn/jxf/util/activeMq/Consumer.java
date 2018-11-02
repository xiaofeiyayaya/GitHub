package com.cn.jxf.util.activeMq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	// 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
	@JmsListener(destination = "mytest.queue",containerFactory = "jmsListenerContainerQueue")
	public void receiveQueue(String text) {
		System.out.println("Consumer收到的报文为:" + text);
	}
	
	@JmsListener(destination = "mytest.queue",containerFactory = "jmsListenerContainerQueue")
	public void receiveQueue2(String text) {
		System.out.println("Consumer2收到的报文为:" + text);
	}
	
	@JmsListener(destination = "mytest.topic",containerFactory = "jmsListenerContainerTopic")
	public void receiveTopic(String text) {
		System.out.println("Consumer3收到的报文为:" + text);
	}
}
