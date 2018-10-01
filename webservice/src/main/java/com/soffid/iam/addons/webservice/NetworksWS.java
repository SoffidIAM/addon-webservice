package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Network;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface NetworksWS {

	/**
	 * @param name
	 * @param description
	 * @param address
	 * @param mask
	 * @param lan
	 * @param dhcpParams
	 * @param networkName
	 * @return
	 * @throws UnexpectedException
	 */
	Collection<Network> findNetworks(String name, String description, String address, String mask, Boolean lan,
			String dhcpParams, String networkName) throws UnexpectedException;

	/**
	 * Creates a Network
	 * 
	 * @param network network to create
	 * @return created network
	 * @throws UnexpectedException
	 */
	Network create(Network network) throws UnexpectedException;

	/**
	 * Removes a Network
	 * 
	 * @param network network to remove
	 * @throws UnexpectedException
	 */
	void remove(Network network) throws UnexpectedException;

	/**
	 * Update Network data
	 * 
	 * @param network the Network to update
	 * @return the updated Network
	 * @throws UnexpectedException
	 */
	Network update(Network network) throws UnexpectedException;
}
