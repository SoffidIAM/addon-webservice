package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Session;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebService(
		portName = "Sessions",
		serviceName = "services/Sessions",
		targetNamespace = "http://iam.soffid.com/wsdl",
		endpointInterface = "com.soffid.iam.addons.webservice.SessionsWS"
)
public class Sessions extends AbstractService implements SessionsWS {

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.SessionsWS#findSessionsByHost(java.lang.String)
	 */
	public Collection<Session> findSessionsByHost(String hostName)
			throws UnexpectedException {
		try {
			return getNetworkService().findSessionsByHostName(hostName);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.SessionsWS#findSessionsByUser(java.lang.String)
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
