package com.googlecode.npackdweb;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.npackdweb.wlib.Action;
import com.googlecode.npackdweb.wlib.ActionSecurityType;
import com.googlecode.npackdweb.wlib.Page;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;

/**
 * Start a copy of a package version.
 */
public class CopyPackageVersionAction extends Action {
	/**
	 * -
	 */
	public CopyPackageVersionAction() {
		super("^/package-version/copy$", ActionSecurityType.EDITOR);
	}

	@Override
	public Page perform(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String name = req.getParameter("name");
		Objectify ofy = NWUtils.OBJECTIFY.get();
		PackageVersion p = ofy.get(new Key<PackageVersion>(
				PackageVersion.class, name));
		return new CopyPackageVersionPage(p);
	}
}
