package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Session;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface SessionsWS {

	/**
	 * find sessions on a host name
	 * 
	 * @param hostName the host name
	 *            
	 * @return list of active sessions on this host
	 * @throws UnexpectedException
	 */
	Collection<Session> findSessionsByHost(String hostName) throws UnexpectedException;

	/**
	 * find sessions for a user
	 * 
	 * @param userName the user name
	 *            
	 * @return list of user's active sessions
	 * @throws UnexpectedException
	 */
	Collection<Session> findSessionsByUser(String userName) throws UnexpectedException;
}
