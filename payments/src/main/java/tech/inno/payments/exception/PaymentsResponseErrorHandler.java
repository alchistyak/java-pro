package tech.inno.payments.exception;

import ch.qos.logback.core.util.StringUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResponseErrorHandler;

import javax.swing.text.Utilities;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Component
public class PaymentsResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        System.out.println("code: " + response.getStatusCode());
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
            String body = new BufferedReader(new InputStreamReader(response.getBody()))
                    .lines()
                    .parallel()
                    .collect(Collectors.joining("\n"));
            throw new PaymentsException(body, HttpStatus.valueOf(response.getStatusCode().value()));
     }
}
