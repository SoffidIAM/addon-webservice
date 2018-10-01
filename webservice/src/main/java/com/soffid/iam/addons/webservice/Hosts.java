package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Host;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebService(
        portName = "Hosts",
        serviceName = "services/Hosts",
        targetNamespace = "http://iam.soffid.com/wsdl",
        endpointInterface = "com.soffid.iam.addons.webservice.HostsWS"
)
public class Hosts extends AbstractService implements HostsWS {

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.HostsWS#findHosts(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Collection<Host> findHosts(String name, String operationSystem, String address, String dhcp, Boolean mailServer,
			Boolean driveServer, String alias, String mac, String description, String network, String user)
			throws UnexpectedException {
		try {
			return getNetworkService().findHostByFilter(name, operationSystem, address, dhcp,
					mailServer == null? null: mailServer.booleanValue() ? "S": "N",
					driveServer == null? null: driveServer.booleanValue() ? "S": "N",
					alias, mac, description, network, user, Boolean.FALSE);	
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.HostsWS#create(com.soffid.iam.api.Host)
	 */
	public Host create (Host host) throws UnexpectedException
	{
		try {
			return getNetworkService().create(host);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.HostsWS#remove(com.soffid.iam.api.Host)
	 */
	public void remove (Host host) throws UnexpectedException
	{
		try {
			getNetworkService().delete(host);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.HostsWS#update(com.soffid.iam.api.Host)
	 */
	public Host update (Host host) throws UnexpectedException
	{
		try {
			getNetworkService().update(host);
			return host;
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.HostsWS#getAdminCredentials(com.soffid.iam.api.Host)
	 */
	public String[] getAdminCredentials (Host host) throws UnexpectedException
	{
		try {
			return getNetworkService().getHostAdminUserAndPassword(host.getName());
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
}
