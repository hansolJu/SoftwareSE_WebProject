package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandAction {
	public void requestPro(
		HttpServletRequest request, HttpServletResponse response)
		throws Throwable;
}
