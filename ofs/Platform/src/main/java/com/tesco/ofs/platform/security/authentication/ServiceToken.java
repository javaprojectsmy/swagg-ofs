package com.tesco.ofs.platform.security.authentication;

import java.util.Collection;

public class ServiceToken
{
     public String token;
     public String Status;    
     public Collection<Claims> Claims;
     public String UserId;
     public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}
	
     public String getToken() { 
    	 return token; 
     }
     
     public void setToken(String token) { 
    	 this.token = token; 
     }
     
     public String getStatus() { 
    	 return Status; 
     }
     
     public void setStatus(String status) { 
    	 this.Status = status; 
     }
     
/*     public String getClientID() { 
    	 return clientID; 
     }
     
     public void setClientID(String clientID) { 
    	 this.clientID = clientID; 
     }     */
     
     @Override
 	public String toString() {
 		return "ServiceToken [token=" + token + ", status=" + Status +  "Claims=" + Claims + "]";
 	}
     
     public Collection<Claims> getClaims() {
		return Claims;
	}

	public void setClaims(Collection<Claims> claims) {
		Claims = claims;
	}

	/*public static void main(String[] args) throws IOException, JAXBException {
			
			BufferedReader br = new BufferedReader(new FileReader("F:/Platform/APILayer/PlatformApi/ServiceToken-Json.txt"));
			
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        
	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	        }
	        
	        br.close();
	        System.out.println("line:::::" + sb.toString());
		    ServiceToken stoken = OFSPlatformTransformationUtil.json2Object(sb.toString(), new ServiceToken()); //convert object to xml string
		    Collection<Claims> claims = stoken.getClaims();
		    
		    Iterator<Claims> it = claims.iterator();
		    
		    while(it.hasNext())
		    {
		    	Claims clm = it.next();
		    	System.out.println("claimType:" + clm.getClaimType());
		    	System.out.println("Value:" + clm.getValue());
		    }
		    
		    System.out.println("outXml:::::" + stoken.toString());
	}*/
          
 }




