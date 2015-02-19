package com.tesco.ofs.order.resource;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import com.codahale.metrics.annotation.Timed;
import com.tesco.ofs.order.representation.FulfillmentOrderBean;
import com.tesco.ofs.order.representation.OrderLines.OrderLine;
import com.tesco.ofs.order.application.FulfillmentOrderExecutorService;
import com.tesco.ofs.platform.mediation.protocoladpter.jms.SyncJmsClient;
import com.tesco.ofs.platform.mediation.transformation.OFSPlatformTransformationUtil;
import com.tesco.ofs.platform.trace.logger.OFSPlatformLogger;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
@Path(value = "v1/api/Order")
@Api(value="v1/api/Order")
public class FulfillmentOrder   {
	
	private static final OFSPlatformLogger logger = OFSPlatformLogger.getLogger(FulfillmentOrder.class);
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value="create order")
    @Produces(MediaType.APPLICATION_JSON)
	@Timed(name = "createOrder")
	 
	public FulfillmentOrderBean createOrder(final FulfillmentOrderBean fulfillmentOrderBean)
			throws Exception {
		
		//create GUID for order number
		fulfillmentOrderBean.setOrderNo(UUID.randomUUID().toString());

		FulfillmentOrderExecutorService.getFulfillmentOrderExecutorService().submit(
				new Runnable() {
					public void run() {
						try {
																					
							//set the shipTo and BillTo address at order header level
							fulfillmentOrderBean.setAddressInfo();							
							
							//set the shipTo and BillTo address at order header level
							fulfillmentOrderBean.setOrderHeaderExtnAttributes();
							
							//Set the ship to address and Extn attributes  at order line level
							List<OrderLine> orderLines = fulfillmentOrderBean.getOrderLines();
					    	for (OrderLine orderLine : orderLines) 
					    	{
					    		fulfillmentOrderBean.setShipToAddresAtOrderLine(orderLine);
					    		fulfillmentOrderBean.setOrderLineExtnAttributes(orderLine);
					    	}
							
							String createOrderInputXML = OFSPlatformTransformationUtil
									.objectToXml(fulfillmentOrderBean,
											FulfillmentOrderBean.class);
							
							logger.debug("createOrderInputXML::" + createOrderInputXML);

							// Publish to JMS Queue
							SyncJmsClient.jmsSendMessageToQueue(
									"receiveDestination", createOrderInputXML);
						} catch (JAXBException e) {
							e.printStackTrace();
						}
					}
				});

		FulfillmentOrderBean responseFulfillmenOrderBean = new FulfillmentOrderBean();
		responseFulfillmenOrderBean.setOrderNo(fulfillmentOrderBean.getOrderNo());
		responseFulfillmenOrderBean.setCustomerPONo(fulfillmentOrderBean.getCustomerPONo());
		responseFulfillmenOrderBean.setCustomerOrderID(fulfillmentOrderBean.getCustomerOrderID());
		responseFulfillmenOrderBean.setEnterpriseCode(fulfillmentOrderBean.getEnterpriseCode());
		responseFulfillmenOrderBean.setSellerOrganizationCode(fulfillmentOrderBean.getSellerOrganizationCode());
		
		
		//return Response.ok().build();
		return responseFulfillmenOrderBean;
	}

}
