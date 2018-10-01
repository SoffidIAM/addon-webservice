package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Printer;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebService(
		portName = "Printers",
		serviceName = "services/Printers",
		targetNamespace = "http://iam.soffid.com/wsdl",
		endpointInterface = "com.soffid.iam.addons.webservice.PrintersWS"
)
public class Printers extends AbstractService implements PrintersWS {

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.PrintersWS#findPrinters(java.lang.String, java.lang.String, java.lang.Boolean, java.lang.String)
	 */
	public Collection<Printer> findPrinters(String name, String model, Boolean local,
			String serverName)
			throws UnexpectedException {
		try {
			return getPrinterService().findPrintersByFilter(name,  model, 
					local == null? null: local.booleanValue() ? "S": "N",
					serverName);	
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.PrintersWS#create(com.soffid.iam.api.Printer)
	 */
	public Printer create (Printer printer) throws UnexpectedException
	{
		try {
			return getPrinterService().create(printer);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.PrintersWS#remove(com.soffid.iam.api.Printer)
	 */
	public void remove (Printer printer) throws UnexpectedException
	{
		try {
			getPrinterService().delete(printer);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.PrintersWS#update(com.soffid.iam.api.Printer)
	 */
	public Printer update (Printer printer) throws UnexpectedException
	{
		try {
			getPrinterService().update(printer);
			return printer;
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
}
