package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 컨트롤러 구현체들이 구현하는 인터페이스
 * execute는 url 값을 반환
 * @author KOSTA
 */
public interface Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
