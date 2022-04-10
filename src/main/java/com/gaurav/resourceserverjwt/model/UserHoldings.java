package com.gaurav.resourceserverjwt.model;

import java.util.List;

public class UserHoldings {
	
	private MSUser user;
	private List<Holding> holdings;
	public MSUser getUser() {
		return user;
	}
	public void setUser(MSUser user) {
		this.user = user;
	}
	public List<Holding> getHoldings() {
		return holdings;
	}
	public void setHoldings(List<Holding> holdings) {
		this.holdings = holdings;
	}
	
	

}
