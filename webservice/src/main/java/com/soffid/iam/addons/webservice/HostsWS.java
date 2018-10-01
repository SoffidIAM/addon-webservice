package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Host;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface HostsWS {

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
	Collection<Host> findHosts(String name, String operationSystem, String address, String dhcp, Boolean mailServer,
			Boolean driveServer, String alias, String mac, String description, String network, String user)
			throws UnexpectedException;

	/**
	 * Creates a Host
	 * 
	 * @param host Host to create
	 * @return created Host
	 * @throws UnexpectedException
	 */
	Host create(Host host) throws UnexpectedException;

	/**
	 * Removes a Host
	 * 
	 * @param host host to remove
	 * @throws UnexpectedException
	 */
	void remove(Host host) throws UnexpectedException;

	/**
	 * Update Host data
	 * 
	 * @param host the Host to update
	 * @return the updated Host
	 * @throws UnexpectedException
	 */
	Host update(Host host) throws UnexpectedException;

	/**
	 * Gets local administrator user and password
	 * 
	 * @param host the host whose credentials to know
	 * @return two strings containing local administrator user and password
	 * @throws UnexpectedException
	 */
	String[] getAdminCredentials(Host host) throws UnexpectedException;
}
