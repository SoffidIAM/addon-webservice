package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebService(
        portName = "Configuration",
        serviceName = "services/Configuration",
        targetNamespace = "http://iam.soffid.com/wsdl",
        endpointInterface = "com.soffid.iam.addons.webservice.ConfigurationWS"
)
public class Configuration extends AbstractService implements ConfigurationWS {
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.ConfigurationWS#findConfig(java.lang.String, java.lang.String, java.lang.String)
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
