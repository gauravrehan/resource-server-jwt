package com.gaurav.resourceserverjwt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.gaurav.resourceserverjwt.model.Holding;
import com.gaurav.resourceserverjwt.model.MSUser;
import com.gaurav.resourceserverjwt.model.UserHoldings;

@RestController
@RequestMapping(value = "/holdings")
public class HoldingsController {

	 private static final Logger logger = LoggerFactory.getLogger(HoldingsController.class);

   @GetMapping(value = "/get")
   public UserHoldings findOne(Authentication auth ) {
   	
   	//Get user from jwt
   	MSUser user = getUser(auth);
   	
   	//Get holdings for the current user
   	List<Holding> holdings = getUserHoldings(user.getUserId());
   	   
       //Return the holdings
       UserHoldings userHolding = new UserHoldings();
       userHolding.setUser(user);
       userHolding.setHoldings(holdings);
       
       return userHolding;
        
       
       
   }

   private MSUser getUser(Authentication auth)
   {
   	JwtAuthenticationToken token = (JwtAuthenticationToken) auth;
       Map<String, Object> attributes = token.getTokenAttributes();
       
       logger.info("UserDetails {}", attributes);
       MSUser user = new MSUser();
       user.setUserId(attributes.get("sub").toString());
       user.setName(attributes.get("name").toString());
       user.setEmailId(attributes.get("email").toString());
       return user;
   	
   }
   
   private List<Holding> getUserHoldings(String userId)
   {
   	List<Holding> holdings = new ArrayList<>();
   	
   	Holding holdingTesla = new Holding();
       holdingTesla.setInstrument("TESLA");
       holdingTesla.setQuantity(45l);
       
       Holding holdingAmazon = new Holding();
       holdingAmazon.setInstrument("Amazon");
       holdingAmazon.setQuantity(20l);
       
       holdings.add(holdingTesla);
       holdings.add(holdingAmazon);
       
       return holdings;
       
   }
 
}
