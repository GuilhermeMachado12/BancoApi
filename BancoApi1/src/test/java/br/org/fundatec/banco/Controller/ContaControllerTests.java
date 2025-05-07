package br.org.fundatec.banco.Controller;

import br.org.fundatec.banco.BancoApiApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = BancoApiApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ContaControllerTests {
}
