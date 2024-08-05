package tech.inno.payment.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.inno.payment.dto.BalanceDto;
import tech.inno.payment.dto.ProductDto;
import tech.inno.payment.dto.ChangeBalanceDto;
import tech.inno.payment.exception.PaymentsException;

@Service
public class PaymentsService {
    private final RestTemplate executorPayments;

    public PaymentsService(RestTemplate executorPayments) {
        this.executorPayments = executorPayments;
    }

    public ProductDto getProductById(Long productId) {
        String url = "/api/v1/products/find/id/?id={id}";
        return executorPayments.getForObject(url, ProductDto.class, productId);
    }

    public BalanceDto checkBalance(Long productId) {
        String url = "/api/v1/products/find/id/?id={id}";
        System.out.println("url: " + url);
        ProductDto product = executorPayments.getForObject(url, ProductDto.class, productId);
        return new BalanceDto(product.id(), product.account(), product.type(), product.balance());
    }

    public ProductDto changeBalance(ChangeBalanceDto request) {
        String url = "/api/v1/products/update/";
        ProductDto productDto = getProductById(request.id());
        System.out.println("id: " + productDto.id());
        System.out.println("productDto: " + productDto);
        System.out.println("summa: " + request.summa());
        System.out.println("action: " + request.action());
        if (request.action().equals("-")) {
            if (productDto.balance().compareTo(request.summa()) >= 0) {
                executorPayments.put(
                        url,
                        new BalanceDto(productDto.id(), productDto.account(), productDto.type(), (productDto.balance().subtract(request.summa())))
                );
            } else {
                throw new PaymentsException("Недостаточно средств на счете для списания суммы " + request.summa(), HttpStatus.BAD_REQUEST);
            }
        } else if (request.action().equals("+")) {
            executorPayments.put(
                    url,
                    new BalanceDto(productDto.id(), productDto.account(), productDto.type(), (productDto.balance().add(request.summa())))
                    //new ProductDto(request.id(), productDto.account(), (productDto.balance().add(request.summa())), productDto.type(), productDto.userid())
            );

        }
        return getProductById(request.id());
    }
}
