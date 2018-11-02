package com.cn.jxf.activeMq;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cn.jxf.Application;
import com.cn.jxf.util.activeMq.Producer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class ActiveMqTest {
	
	@Autowired  
    private Producer producer;
	
	@Test
	public void demo() throws InterruptedException{
		 Destination queueDestination = new ActiveMQQueue("mytest.queue"); 
		 
		 Destination topicDestination = new ActiveMQTopic("mytest.topic");  
         
	     producer.sendMessage(queueDestination, "myname is queue1!!!");  
	     producer.sendMessage(topicDestination, "myname is topic1!!!");  
	            
		
	}
}
