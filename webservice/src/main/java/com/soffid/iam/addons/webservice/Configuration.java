package com.soffid.iam.addons.webservice;

import java.util.Collection;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;

import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * @author bubu
 * 
 */
public class Configuration extends AbstractService {
	
	/**
	 * Returns soffid configuration parameters
	 * @param name name filter (accepts % wildcard)
	 * @param description description filter (accepts % wildcard) 
	 * @param networkName network name filter (accepts % wildcard)
	 * @return the list of configuration parameters
	 * 
	 * @throws UnexpectedException
	 */
	public Collection<com.soffid.iam.api.Configuration> findConfig (String name, String description, String networkName)
			throws UnexpectedException {
		try {
			return getConfigurationService().findConfigurationByFilter(name, null, description, networkName);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
}

