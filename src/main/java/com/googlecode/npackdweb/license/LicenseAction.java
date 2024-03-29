package com.googlecode.npackdweb.license;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.npackdweb.DefaultServlet;
import com.googlecode.npackdweb.db.License;
import com.googlecode.npackdweb.wlib.Action;
import com.googlecode.npackdweb.wlib.ActionSecurityType;
import com.googlecode.npackdweb.wlib.Page;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;

/**
 * A license.
 */
public class LicenseAction extends Action {
	/**
	 * -
	 */
	public LicenseAction() {
		super("^/l/([^/]+)$", ActionSecurityType.ANONYMOUS);
	}

	@Override
	public Page perform(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String name = req.getRequestURI().substring(3);

		Objectify ofy = DefaultServlet.getObjectify();
		License r = ofy.find(new Key<License>(License.class, name));
		LicensePage pdp = null;
		User u = UserServiceFactory.getUserService().getCurrentUser();
		if (r == null) {
			if (u != null) {
				r = new License();
				r.name = name;
				pdp = new LicensePage();
				pdp.fill(r);
			} else {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} else {
			pdp = new LicensePage();
			pdp.fill(r);
		}
		return pdp;
	}
}
