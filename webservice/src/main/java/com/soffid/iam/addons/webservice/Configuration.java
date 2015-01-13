package com.soffid.iam.addons.webservice;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.Network;

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

