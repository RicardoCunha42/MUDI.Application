package br.com.alura.mvc.mudi.interceptor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class InterceptadorDeAcessos implements HandlerInterceptor {
	public static List<Acesso> acessos = new ArrayList<>();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Acesso acesso = new Acesso();
		acesso.setPath(request.getRequestURI());
		acesso.setData(LocalDateTime.now());
		
		request.setAttribute("acesso", acesso);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		Acesso acesso = (Acesso) request.getAttribute("acesso");
		acesso.setDuracao(Duration.between(acesso.getData(), LocalDateTime.now()));
		acessos.add(acesso);
	}

	public static class Acesso {
		private String path;
		private LocalDateTime data;
		private Duration duracao;

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public LocalDateTime getData() {
			return data;
		}

		public void setData(LocalDateTime data) {
			this.data = data;
		}

		public Duration getDuracao() {
			return duracao;
		}

		public void setDuracao(Duration duracao) {
			this.duracao = duracao;
		}

	}
}
