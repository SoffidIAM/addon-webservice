package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.OsType;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface OperatingSystemsWS {

	/**
	 * Find operatingSystems
	 * 
	 * @return the operating systems list
	 * @throws UnexpectedException
	 */
	Collection<OsType> getOsTypes() throws UnexpectedException;

	/**
	 * Creates a operationg system
	 * 
	 * @param operatingSystem Operating system to create
	 * @return created Operating System
	 * @throws UnexpectedException
	 */
	OsType create(OsType operatingSystem) throws UnexpectedException;

	/**
	 * Removes a Operating System
	 * 
	 * @param operatingSystem operatingSystem to remove
	 * @throws UnexpectedException
	 */
	void remove(OsType operatingSystem) throws UnexpectedException;

	/**
	 * Update Operating System data
	 * 
	 * @param operatingSystem the Operating System to update
	 * @return the updated Operating System
	 * @throws UnexpectedException
	 */
	OsType update(OsType operatingSystem) throws UnexpectedException;
}
