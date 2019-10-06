package py.edu.fpune;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import py.edu.fpune.rest.FuncionarioController;
import py.edu.fpune.rest.RestBuilder;

@ApiTestConfig
public class ApiFuncionarioTI {

	@Value("${local.server.port}")
	private int port = 0;
	@Test
	void testAllFuncionariosOk() {
		new RestBuilder<>(port).basicAuth("nelson-lz", "123")
							   .path(FuncionarioController.FUNCTIONARY)
							   .path(FuncionarioController.ALL)
							   .get().build();
	}
}
