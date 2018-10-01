package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface ConfigurationWS {

	/**
	 * Returns soffid configuration parameters
	 * @param name name filter (accepts % wildcard)
	 * @param description description filter (accepts % wildcard) 
	 * @param networkName network name filter (accepts % wildcard)
	 * @return the list of configuration parameters
	 * 
	 * @throws UnexpectedException
	 */
	Collection<com.soffid.iam.api.Configuration> findConfig(String name, String description, String networkName)
			throws UnexpectedException;
}
