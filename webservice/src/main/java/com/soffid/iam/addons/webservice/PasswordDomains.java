package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.PasswordDomain;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebService(
		portName = "PasswordDomains",
		serviceName = "services/PasswordDomains",
		targetNamespace = "http://iam.soffid.com/wsdl",
		endpointInterface = "com.soffid.iam.addons.webservice.PasswordDomainsWS"
)
public class PasswordDomains extends AbstractService implements PasswordDomainsWS {

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.PasswordDomainsWS#findPasswordDomains()
	 */
	public Collection<PasswordDomain> findPasswordDomains()
			throws UnexpectedException {
		try {
			return getUserDomainService().findAllPasswordDomain();
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.PasswordDomainsWS#create(com.soffid.iam.api.PasswordDomain)
	 */
	public PasswordDomain create (PasswordDomain passwordDomain) throws UnexpectedException
	{
		try {
			return getUserDomainService().create(passwordDomain);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.PasswordDomainsWS#remove(com.soffid.iam.api.PasswordDomain)
	 */
	public void remove (PasswordDomain passwordDomain) throws UnexpectedException
	{
		try {
			getUserDomainService().delete(passwordDomain);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.PasswordDomainsWS#update(com.soffid.iam.api.PasswordDomain)
	 */
	public PasswordDomain update (PasswordDomain passwordDomain) throws UnexpectedException
	{
		try {
			getUserDomainService().update(passwordDomain);
			return passwordDomain;
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
}
