package proxy.staticProxy;

public class SmsProxy implements SmsService{
    private SmsService smsService;

    public SmsProxy(SmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public String sendSms(String message) {
        System.out.println("Before Sending Messages...");
        smsService.sendSms(message);
        System.out.println("After Sending Messages...");
        return null;
    }
}
