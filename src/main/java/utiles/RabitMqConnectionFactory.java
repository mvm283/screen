package utiles;

import com.rabbitmq.client.ConnectionFactory;

public class RabitMqConnectionFactory {
    public static ConnectionFactory rabitMqConnectionFactory(){

        ConnectionFactory factory=new ConnectionFactory() ;

       /*
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(15672);
        factory.setHost("http://127.0.0.1:15672");
        */
        return  factory;
    }
}
