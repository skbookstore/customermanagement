package bookrental;

import bookrental.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

    @Service
    public class PolicyHandler{

        @Autowired CustomerRepository CustomerRepository;

        @StreamListener(KafkaProcessor.INPUT)
        public void wheneverReserved_Orderresult(@Payload Reserved reserved){

            if(reserved.isMe()){
                System.out.println("##### 예약 완료 되었습니다  #####" );//+ reserved.toJson());
                System.out.println("======================");
                System.out.println("고객 정보 수정/등록");
                Customer c = new Customer();
                c.setBookid(reserved.getBookid());
                c.setOrderid(reserved.getId());
                c.setStatus(reserved.getStatus());
                c.setUserid(reserved.getUserid());
                CustomerRepository.save(c);
                System.out.println("======================");
            }

        }

       //public void onEventByString(@Payload Reserved reserved){
       //     if( reserved.getEventType().equals("Reserved")){

       //     }

       // }

}