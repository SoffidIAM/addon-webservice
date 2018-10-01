package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.MailDomain;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebService(
		portName = "MailDomains",
		serviceName = "services/MailDomains",
		targetNamespace = "http://iam.soffid.com/wsdl",
		endpointInterface = "com.soffid.iam.addons.webservice.MailDomainsWS"
)
public class MailDomains extends AbstractService implements MailDomainsWS {

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.MailDomainsWS#findMailDomains(java.lang.String, java.lang.String, java.lang.Boolean)
	 */
	public Collection<MailDomain> findMailDomains(String name, String description, Boolean obsolete)
			throws UnexpectedException {
		try {
			return getMailListsService().findMailDomainsByFilter(name, description, obsolete == null ? null: obsolete.booleanValue() ? "S": "N");
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.MailDomainsWS#create(com.soffid.iam.api.MailDomain)
	 */
	public MailDomain create (MailDomain mailDomain) throws UnexpectedException
	{
		try {
			return getMailListsService().create(mailDomain);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.MailDomainsWS#remove(com.soffid.iam.api.MailDomain)
	 */
	public void remove (MailDomain mailDomain) throws UnexpectedException
	{
		try {
			getMailListsService().delete(mailDomain);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.MailDomainsWS#update(com.soffid.iam.api.MailDomain)
	 */
	public void update (MailDomain mailDomain) throws UnexpectedException
	{
		try {
			getMailListsService().update(mailDomain);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
}
