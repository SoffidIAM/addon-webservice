package com.soffid.iam.addons.webservice;

import java.util.Collection;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.Network;

import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * @author bubu
 * 
 */
public class Networks extends AbstractService {
	
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
	
	
	/**
	 * Creates a Network
	 * 
	 * @param network network to create
	 * @return created network
	 * @throws UnexpectedException
	 */
	public Network create (Network network) throws UnexpectedException
	{
		try {
			return getNetworkService().create(network);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Removes a Network
	 * 
	 * @param network network to remove
	 * @throws UnexpectedException
	 */
	public void remove (Network network) throws UnexpectedException
	{
		try {
			getNetworkService().delete(network);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Update Network data
	 * 
	 * @param network the Network to update
	 * @return the updated Network
	 * @throws UnexpectedException
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

