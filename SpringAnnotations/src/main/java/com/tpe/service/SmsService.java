package service;

import domain.Message;
import org.springframework.stereotype.Component;

@Component
public class SmsService implements MessageService {


    @Override
    public void sendMessage(Message message) {
        System.out.println(" Mesajiniz Sms gönderildi.Mesaj: "+message.getBody());
    }

    @Override
    public void saveMessage(Message message) {

    }
}
