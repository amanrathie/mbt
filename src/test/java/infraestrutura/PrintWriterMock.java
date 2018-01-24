package infraestrutura;

import java.io.PrintWriter;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PrintWriterMock {

    public PrintWriter mock;
    private StringBuilder resultado;

    public PrintWriterMock() {
        mock = mock(PrintWriter.class);
        resultado = new StringBuilder();
        when(mock.append(anyString())).then(invocationOnMock -> {
            resultado.append(invocationOnMock.getArguments()[0]);
            return invocationOnMock.getMock();
        });
    }

    public String getResultado() {
        return resultado.toString();
    }
}
