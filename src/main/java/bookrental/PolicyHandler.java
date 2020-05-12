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

        @StreamListener(KafkaProcessor.INPUT)
        public void wheneverReserved_Orderresultalarm(@Payload Reserved reserved){

            if(reserved.isMe()){
                System.out.println("##### 예약 완료 되었습니다  #####" );//+ reserved.toJson());

            }

        }

        @StreamListener(KafkaProcessor.INPUT)
        public void wheneverCanceled_Orderresultalarm(@Payload Canceled canceled){

            if(canceled.isMe()){
                System.out.println("##### 예약 취소 되었습니다  #####" );//+ reserved.toJson());

            }

        }

       //public void onEventByString(@Payload Reserved reserved){
       //     if( reserved.getEventType().equals("Reserved")){

       //     }

       // }

}