package com.soffid.iam.addons.webservice;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.service.ejb.AccountService;
import com.soffid.iam.service.ejb.AdditionalDataService;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.service.ejb.AttributeTranslationService;
import com.soffid.iam.service.ejb.ConfigurationService;
import com.soffid.iam.service.ejb.DispatcherService;
import com.soffid.iam.service.ejb.EntryPointService;
import com.soffid.iam.service.ejb.GroupService;
import com.soffid.iam.service.ejb.MailListsService;
import com.soffid.iam.service.ejb.NetworkService;
import com.soffid.iam.service.ejb.PasswordService;
import com.soffid.iam.service.ejb.PrinterService;
import com.soffid.iam.service.ejb.SelfService;
import com.soffid.iam.service.ejb.UserDomainService;

// ERROR: ERROR
// Missing user-printer group-printer
// Missing password management ??
// Missing session management
// Missing access log query
// Missing audit log query

public class AbstractService {

	private com.soffid.iam.service.ejb.UserService userService = null;
	private GroupService groupService;
	private ApplicationService appService;
	private AccountService accountService;
	private DispatcherService dispatcherService;
	private MailListsService mailListService;
	private EntryPointService accessTreeService;
	private NetworkService networkService;
	private ConfigurationService configurationService;
	private UserDomainService userDomainService;
	private AdditionalDataService additionalDataService;
	private PrinterService printerService;
	private PasswordService passwordService;
	private SelfService selfService;
	private AttributeTranslationService attributeTranslationService;

	public SelfService getSelfService() throws UnexpectedException {
		try {
			if (selfService == null)
				selfService = EJBLocator.getSelfService();
			return selfService;
		} catch (NamingException e) {
			throw new UnexpectedException(e);
		} catch (CreateException e) {
			throw new UnexpectedException(e);
		}
	}

	public AbstractService() {
		super();
	}

	protected com.soffid.iam.service.ejb.UserService getUsersService() throws UnexpectedException {
		try {
			if (userService == null)
				userService = EJBLocator.getUserService();
			return userService;
		} catch (NamingException e) {
			throw new UnexpectedException(e);
		} catch (CreateException e) {
			throw new UnexpectedException(e);
		}
	}
	protected com.soffid.iam.service.ejb.GroupService getGroupService() throws UnexpectedException {
		try {
			if (groupService == null)
				groupService = EJBLocator.getGroupService();
			return groupService;
		} catch (NamingException e) {
			throw new UnexpectedException(e);
		} catch (CreateException e) {
			throw new UnexpectedException(e);
		}
	}

	protected ApplicationService getAppService() throws UnexpectedException {
		try {
			if (appService == null)
				appService = EJBLocator.getApplicationService();
			return appService;
		} catch (NamingException e) {
			throw new UnexpectedException(e);
		} catch (CreateException e) {
			throw new UnexpectedException(e);
		}
	}

	protected AccountService getAccountService() throws UnexpectedException {
		try {
			if (accountService == null)
				accountService = EJBLocator.getAccountService();
			return accountService;
		} catch (NamingException e) {
			throw new UnexpectedException(e);
		} catch (CreateException e) {
			throw new UnexpectedException(e);
		}
	}

	protected DispatcherService getDispatcherService() throws UnexpectedException {
		try {
			if (dispatcherService == null)
				dispatcherService = EJBLocator.getDispatcherService();
			return dispatcherService;
		} catch (NamingException e) {
			throw new UnexpectedException(e);
		} catch (CreateException e) {
			throw new UnexpectedException(e);
		}
	}

	protected MailListsService getMailListsService() throws UnexpectedException {
		try {
			if (mailListService == null)
				mailListService = EJBLocator.getMailListsService();
			return mailListService;
		} catch (NamingException e) {
			throw new UnexpectedException(e);
		} catch (CreateException e) {
			throw new UnexpectedException(e);
		}
	}

	protected EntryPointService getAccessTreeService() throws UnexpectedException {
		try {
			if (accessTreeService == null)
				accessTreeService = EJBLocator.getEntryPointService();
			return accessTreeService;
		} catch (NamingException e) {
			throw new UnexpectedException(e);
		} catch (CreateException e) {
			throw new UnexpectedException(e);
		}
	}

	protected NetworkService getNetworkService() throws UnexpectedException {
		try {
			if (networkService == null)
				networkService = EJBLocator.getNetworkService();
			return networkService;
		} catch (NamingException e) {
			throw new UnexpectedException(e);
		} catch (CreateException e) {
			throw new UnexpectedException(e);
		}
	}

	protected UserDomainService getUserDomainService() throws UnexpectedException {
		try {
			if (userDomainService == null)
				userDomainService = EJBLocator.getUserDomainService();
			return userDomainService;
		} catch (NamingException e) {
			throw new UnexpectedException(e);
		} catch (CreateException e) {
			throw new UnexpectedException(e);
		}
	}

	protected ConfigurationService getConfigurationService() throws UnexpectedException {
		try {
			if (configurationService == null)
				configurationService = EJBLocator.getConfigurationService();
			return configurationService;
		} catch (NamingException e) {
			throw new UnexpectedException(e);
		} catch (CreateException e) {
			throw new UnexpectedException(e);
		}
	}
	
	protected AdditionalDataService getAdditionalDataService() throws UnexpectedException {
		try {
			if (additionalDataService == null)
				additionalDataService = EJBLocator.getAdditionalDataService();
			return additionalDataService;
		} catch (NamingException e) {
			throw new UnexpectedException(e);
		} catch (CreateException e) {
			throw new UnexpectedException(e);
		}
	}

	protected PrinterService getPrinterService() throws UnexpectedException {
		try {
			if (printerService == null)
				printerService = EJBLocator.getPrinterService();
			return printerService;
		} catch (NamingException e) {
			throw new UnexpectedException(e);
		} catch (CreateException e) {
			throw new UnexpectedException(e);
		}
	}

	protected PasswordService getPasswordService() throws UnexpectedException {
		try {
			if (passwordService == null)
				passwordService = EJBLocator.getPasswordService();
			return passwordService;
		} catch (NamingException e) {
			throw new UnexpectedException(e);
		} catch (CreateException e) {
			throw new UnexpectedException(e);
		}
	}

	protected AttributeTranslationService getAttributeTranslationServiceService() throws UnexpectedException {
		try {
			if (attributeTranslationService == null)
				attributeTranslationService = EJBLocator.getAttributeTranslationService();
			return attributeTranslationService;
		} catch (NamingException e) {
			throw new UnexpectedException(e);
		} catch (CreateException e) {
			throw new UnexpectedException(e);
		}
	}	
}
