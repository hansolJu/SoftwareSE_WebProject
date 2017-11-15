/**
 * SuperInterface
 * 수정자:주한솔
 * 최종수정일 : 17.11.15
 */
package wedeal.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandAction {
	public String requestPro(
		HttpServletRequest request, HttpServletResponse response)
		throws Throwable;
}