package com.ibm.big.deliverydashboard.beans.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
public class SkillTag
{
	String name;

	public String getName()
	{
		return name;
	}

	public void setName(String tag)
	{
		this.name = tag;
	}
}
