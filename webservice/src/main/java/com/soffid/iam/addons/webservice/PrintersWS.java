package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Printer;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface PrintersWS {

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
	Collection<Printer> findPrinters(String name, String model, Boolean local, String serverName)
			throws UnexpectedException;

	/**
	 * Creates a Printer
	 * 
	 * @param printer Printer to create
	 * @return created Printer
	 * @throws UnexpectedException
	 */
	Printer create(Printer printer) throws UnexpectedException;

	/**
	 * Removes a Printer
	 * 
	 * @param printer printer to remove
	 * @throws UnexpectedException
	 */
	void remove(Printer printer) throws UnexpectedException;

	/**
	 * Update Printer data
	 * 
	 * @param printer the Printer to update
	 * @return the updated Printer
	 * @throws UnexpectedException
	 */
	Printer update(Printer printer) throws UnexpectedException;
}
