package com.soffid.iam.addons.webservice;

import java.util.Collection;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Printer;

import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * @author bubu
 * 
 */
public class Printers extends AbstractService {
	
	/**
	 * Find printers
	 * 
	 * @param name the printer name (accepts % wildcards)
	 * @param model the printer model (accepts % wildcards)
	 * @param address IP address (accepts % wildcards)
	 * @param local true to find local printers. false to find network printers
	 * @param serverName server host name (accepts % wildcards)
	 * @return
	 * @throws UnexpectedException
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
	
	
	/**
	 * Creates a Printer
	 * 
	 * @param printer Printer to create
	 * @return created Printer
	 * @throws UnexpectedException
	 */
	public Printer create (Printer printer) throws UnexpectedException
	{
		try {
			return getPrinterService().create(printer);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Removes a Printer
	 * 
	 * @param printer printer to remove
	 * @throws UnexpectedException
	 */
	public void remove (Printer printer) throws UnexpectedException
	{
		try {
			getPrinterService().delete(printer);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Update Printer data
	 * 
	 * @param printer the Printer to update
	 * @return the updated Printer
	 * @throws UnexpectedException
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

