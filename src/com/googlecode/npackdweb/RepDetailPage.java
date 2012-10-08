package com.googlecode.npackdweb;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

/**
 * Repository details.
 */
public class RepDetailPage extends FramePage {
	private Repository r;

	/**
	 * @param id
	 *            repository id
	 */
	public RepDetailPage(Repository r) {
		this.r = r;
	}

	@Override
	public String createContent(HttpServletRequest request) throws IOException {
		return NWUtils.tmpl("rep/Repository.html", NWUtils.newMap("title",
				r.name, "id", r.id.toString()));
	}

	@Override
	public String getTitle() {
		return "Repository";
	}
}
