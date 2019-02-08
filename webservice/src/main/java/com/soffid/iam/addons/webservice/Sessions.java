package com.soffid.iam.addons.webservice;

import java.util.Collection;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Session;

import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * @author bubu
 * 
 */
public class Sessions extends AbstractService {
	/**
	 * find sessions on a host name
	 * 
	 * @param hostName the host name
	 *            
	 * @return list of active sessions on this host
	 * @throws UnexpectedException
	 */
	public Collection<Session> findSessionsByHost(String hostName)
			throws UnexpectedException {
		try {
			return getNetworkService().findSessionsByHostName(hostName);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * find sessions for a user
	 * 
	 * @param userName the user name
	 *            
	 * @return list of user's active sessions
	 * @throws UnexpectedException
	 */
	public Collection<Session> findSessionsByUser(String userName)
			throws UnexpectedException {
		try {
			return getUsersService().findSessionByUserName(userName);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
}
