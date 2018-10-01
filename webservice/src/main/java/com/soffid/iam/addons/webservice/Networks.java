package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Network;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebService(
		portName = "Networks",
		serviceName = "services/Networks",
		targetNamespace = "http://iam.soffid.com/wsdl",
		endpointInterface = "com.soffid.iam.addons.webservice.NetworksWS"
)
public class Networks extends AbstractService implements NetworksWS {

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.NetworksWS#findNetworks(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String)
	 */
	public Collection<Network> findNetworks(String name, String description, String address, String mask, Boolean lan,
			String dhcpParams, String networkName)
			throws UnexpectedException {
		try {
			return getNetworkService().findNetworkByFilter(
					name, address, description, mask, lan == null ? null: lan.booleanValue() ? "S": "N",
							dhcpParams, networkName);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.NetworksWS#create(com.soffid.iam.api.Network)
	 */
	public Network create (Network network) throws UnexpectedException
	{
		try {
			return getNetworkService().create(network);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.NetworksWS#remove(com.soffid.iam.api.Network)
	 */
	public void remove (Network network) throws UnexpectedException
	{
		try {
			getNetworkService().delete(network);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.NetworksWS#update(com.soffid.iam.api.Network)
	 */
	public Network update (Network network) throws UnexpectedException
	{
		try {
			getNetworkService().update(network);
			return network;
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
}
