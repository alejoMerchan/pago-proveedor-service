import com.mientrascompila.pago.proveedor.clients.RabbitMqClient;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;


public class RabbitMqTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test() throws Exception {
        rabbitTemplate.convertAndSend(RabbitMqClient.publishQname, "RabbitMQ Spring JSON Example");
        System.out.println("Is listener returned ::: "+rabbitTemplate.isReturnListener());
    }
}
