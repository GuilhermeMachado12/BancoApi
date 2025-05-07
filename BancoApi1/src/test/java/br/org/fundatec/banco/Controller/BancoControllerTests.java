package br.org.fundatec.banco.Controller;

import br.org.fundatec.banco.BancoApiApplication;
import br.org.fundatec.banco.service.BancoService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = BancoApiApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class BancoControllerTests {
    private static final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @MockBean
    private BancoService bancoService;


    @Test
    void testaCadastroBanco () {

    }


}
