package com.tesco.ofs.platform.mediation.protocoladpter.jms;

import javax.jms.Message;

public interface ISyncJmsClient {
	
	 public Message jmsSyncReceiveMessage(final String destinationQueue);
	 
	 public void jmsSendMessageToQueue(final String destinationQueue, final String message);
	 
	 public void jmsSendMessageToTopic(final String destinationQueue, final String message);

}
