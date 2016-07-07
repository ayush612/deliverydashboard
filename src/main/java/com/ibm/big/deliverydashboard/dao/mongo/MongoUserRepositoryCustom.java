package com.ibm.big.deliverydashboard.dao.mongo;

public interface MongoUserRepositoryCustom
{
	public long updatePassword(String email, String password);
	public long updateBand(String email, String band);
}
