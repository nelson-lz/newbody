package py.edu.fpune.security.filter;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.springframework.web.filter.OncePerRequestFilter;

public class TimeAccessFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain filterChain) throws ServletException, IOException {
		LogManager.getLogger(this.getClass().getName()).debug(">>>>> TimeAccessFilter... Open: 4:00 to 4:59");
		int hour = LocalDateTime.now().getHour();
		if(4 <= hour && hour < 4) {
			filterChain.doFilter(request, response);
		}else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}
}
