package com.soffid.iam.addons.webservice;

import java.util.Collection;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.OsType;

import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * @author bubu
 * 
 */
public class OperatingSystems extends AbstractService {
	
	/**
	 * Find operatingSystems
	 * 
	 * @return the operating systems list
	 * @throws UnexpectedException
	 */
	public Collection<OsType> getOsTypes()
			throws UnexpectedException {
		try {
			return getNetworkService().findAllOSTypes();	
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	
	/**
	 * Creates a operationg system
	 * 
	 * @param operatingSystem Operating system to create
	 * @return created Operating System
	 * @throws UnexpectedException
	 */
	public OsType create (OsType operatingSystem) throws UnexpectedException
	{
		try {
			return getNetworkService().create(operatingSystem);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Removes a Operating System
	 * 
	 * @param operatingSystem operatingSystem to remove
	 * @throws UnexpectedException
	 */
	public void remove (OsType operatingSystem) throws UnexpectedException
	{
		try {
			getNetworkService().delete(operatingSystem);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Update Operating System data
	 * 
	 * @param operatingSystem the Operating System to update
	 * @return the updated Operating System
	 * @throws UnexpectedException
	 */
	public OsType update (OsType operatingSystem) throws UnexpectedException
	{
		try {
			getNetworkService().update(operatingSystem);
			return operatingSystem;
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	
}

