package proxy.dynamicProxy.jdk;

import proxy.dynamicProxy.jdk.SmsService;

public class SmsServiceImpl implements SmsService {

    @Override
    public String sendSms(String message) {
        System.out.println("Send Message: " + message);
        return message;
    }
}
