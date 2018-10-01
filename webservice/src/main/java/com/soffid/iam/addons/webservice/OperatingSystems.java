package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.OsType;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebService(
		portName = "OperatingSystems",
		serviceName = "services/OperatingSystems",
		targetNamespace = "http://iam.soffid.com/wsdl",
		endpointInterface = "com.soffid.iam.addons.webservice.OperatingSystemsWS"
)
public class OperatingSystems extends AbstractService implements OperatingSystemsWS {

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.OperatingSystemsWS#getOsTypes()
	 */
	public Collection<OsType> getOsTypes()
			throws UnexpectedException {
		try {
			return getNetworkService().findAllOSTypes();	
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.OperatingSystemsWS#create(com.soffid.iam.api.OsType)
	 */
	public OsType create (OsType operatingSystem) throws UnexpectedException
	{
		try {
			return getNetworkService().create(operatingSystem);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.OperatingSystemsWS#remove(com.soffid.iam.api.OsType)
	 */
	public void remove (OsType operatingSystem) throws UnexpectedException
	{
		try {
			getNetworkService().delete(operatingSystem);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.OperatingSystemsWS#update(com.soffid.iam.api.OsType)
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
