package com.soffid.iam.addons.webservice;

import java.util.Collection;
import java.util.Date;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.Session;
import com.soffid.iam.api.User;

import es.caib.seycon.ng.comu.AccountCriteria;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
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