package com.soffid.iam.addons.webservice;

import java.util.Collection;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Host;

import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * @author bubu
 * 
 */
public class Hosts extends AbstractService {
	
	/**
	 * Find hosts
	 * 
	 * @param name the host name (accepts % wildcards)
	 * @param operationSystem operating system (accepts % wildcards)
	 * @param address IP address (accepts % wildcards)
	 * @param dhcp DHCP parameters 
	 * @param mailServer true to find hosts acting as mail server
	 * @param driveServer true to find hosts acting as driver server
	 * @param alias host alias filter
	 * @param mac MAC address (accepts % wildcards)
	 * @param description description filter (accepts % wildcards)
	 * @param network network name filter (accepts % wildcards)
	 * @param user active user filter (accepts % wildcards)
	 * @return
	 * @throws UnexpectedException
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
	
	
	/**
	 * Creates a Host
	 * 
	 * @param host Host to create
	 * @return created Host
	 * @throws UnexpectedException
	 */
	public Host create (Host host) throws UnexpectedException
	{
		try {
			return getNetworkService().create(host);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Removes a Host
	 * 
	 * @param host host to remove
	 * @throws UnexpectedException
	 */
	public void remove (Host host) throws UnexpectedException
	{
		try {
			getNetworkService().delete(host);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Update Host data
	 * 
	 * @param host the Host to update
	 * @return the updated Host
	 * @throws UnexpectedException
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

	/**
	 * Gets local administrator user and password
	 * 
	 * @param host the host whose credentials to know
	 * @return two strings containing local administrator user and password
	 * @throws UnexpectedException
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

