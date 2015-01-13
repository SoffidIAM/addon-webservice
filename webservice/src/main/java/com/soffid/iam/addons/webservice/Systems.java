package com.soffid.iam.addons.webservice;

import java.util.Collection;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.System;

import es.caib.seycon.ng.exception.InternalErrorException;


public class Systems extends AbstractService {
	
	
	public Collection<System> findDispatchers(String name, String className, String url, Boolean roleBased, Boolean trusted, Boolean active  )
			throws UnexpectedException {
		try {
			Collection<System> systems = getDispatcherService().findDispatchersByFilter(
					name, className, url, 
						roleBased == null? null: roleBased.booleanValue()? "S": "N",
								trusted == null? null: trusted.booleanValue()? "S": "N",
										active);
			for (System system: systems)
			{
				system.setParam0(null);
				system.setParam1(null);
				system.setParam2(null);
				system.setParam3(null);
				system.setParam4(null);
				system.setParam5(null);
				system.setParam6(null);
				system.setParam7(null);
				system.setParam8(null);
				system.setParam9(null);
			}
			return systems;
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	

}

