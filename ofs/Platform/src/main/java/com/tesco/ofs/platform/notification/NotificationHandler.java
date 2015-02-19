package com.tesco.ofs.platform.notification;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.tesco.ofs.platform.mediation.protocoladpter.jms.SyncJmsClient;
import com.tesco.ofs.platform.mediation.transformation.OFSPlatformTransformationUtil;
import com.tesco.ofs.platform.mediation.transformation.OFSPlatformXMLUtil;

@Path(value = "v1/platform/api")
public class NotificationHandler {

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/Event")
	public Response orderCreationException(final String xmlString) throws Exception {

		PlatformExecutorService.getPlatformExecutorService().submit(
				new Runnable() {
					public void run() {
						// Transform & Translate

						Document doc = OFSPlatformXMLUtil.createDocument(xmlString);
						Element ele = doc.getDocumentElement();

						ErrorBean err = new ErrorBean();
						err.setType(ele.getAttribute("Type"));
						err.setErrorCode(ele.getAttribute("ErrorCode"));
						err.setChannelOrderID(ele.getAttribute("CustomerPONo"));
						err.setGUID(ele.getAttribute("OrderNo"));
						err.setErrorMessage(ele.getAttribute("ErrorMessage"));
						err.setBusinessUnit(ele
								.getAttribute("SellerOrganizationCode"));
						err.setEnterpriseCode(ele
								.getAttribute("EnterpriseCode"));
						err.setTimeOfFailure(ele.getAttribute("TimeOfFailure"));

						String createOrderErrorMessage = OFSPlatformTransformationUtil.object2Json(err);

						// Publish to Topic
						SyncJmsClient.jmsSendMessageToTopic(
								"createExceptionNotification", createOrderErrorMessage);
					}
				});
		return Response.ok().build();
	}

}
