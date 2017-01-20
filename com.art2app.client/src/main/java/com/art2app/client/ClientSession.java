package com.art2app.client;

import org.eclipse.scout.rt.client.AbstractClientSession;
import org.eclipse.scout.rt.client.IClientSession;
import org.eclipse.scout.rt.client.session.ClientSessionProvider;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.shared.services.common.code.CODES;
import org.eclipse.scout.rt.shared.services.common.ping.IPingService;

import com.art2app.client.Desktop;

/**
 * <h3>{@link ClientSession}</h3>
 *
 * @author Ecsoya
 */
public class ClientSession extends AbstractClientSession {
String username;
	public ClientSession() {
		super(true);
	}

	/**
	 * @return The {@link IClientSession} which is associated with the current
	 *         thread, or <code>null</code> if not found.
	 */
	public static ClientSession get() {
		return ClientSessionProvider.currentSession(ClientSession.class);
	}

	@Override
	protected void execLoadSession() {
		// pre-load all known code types
		CODES.getAllCodeTypes("com.art2app.shared");
	    BEANS.get(IPingService.class).ping("");
		setDesktop(new Desktop());
	}
}
