package proxy.dynamicProxy.cglib;

public class AliSmsService {
    public String send(String message){
        System.out.println("Sending message: "+ message);
        return message;
    }
}
