package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.MailList;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebService(
		portName = "MailLists",
		serviceName = "services/MailLists",
		targetNamespace = "http://iam.soffid.com/wsdl",
		endpointInterface = "com.soffid.iam.addons.webservice.MailListsWS"
)
public class MailLists extends AbstractService implements MailListsWS {

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.MailListsWS#findMailLists(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Collection<MailList> findMailLists(String name, String domain, String description, String members)
			throws UnexpectedException {
		try {
			return getMailListsService().findMailListsByData(name, domain, description, members);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.MailListsWS#create(com.soffid.iam.api.MailList)
	 */
	public MailList create (MailList mailList) throws UnexpectedException
	{
		try {
			return getMailListsService().create(mailList);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.MailListsWS#remove(com.soffid.iam.api.MailList)
	 */
	public void remove (MailList mailList) throws UnexpectedException
	{
		try {
			getMailListsService().delete(mailList);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.MailListsWS#update(com.soffid.iam.api.MailList)
	 */
	public void update (MailList mailList) throws UnexpectedException
	{
		try {
			getMailListsService().update(mailList);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
}
