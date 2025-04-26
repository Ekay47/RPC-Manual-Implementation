package proxy.dynamicProxy.cglib;

import net.sf.cglib.proxy.Enhancer;

public class CglibProxyFactory {
    public static Object getProxy(Class<?> clazz) {
        // create dynamic enhancer
        Enhancer enhancer = new Enhancer();
        // setup class loader
        enhancer.setClassLoader(clazz.getClassLoader());
        enhancer.setSuperclass(clazz);
        // setup method interceptor
        enhancer.setCallback(new DebugMethodInterceptor());
        return enhancer.create();
    }

    public static void main(String[] args) {
        AliSmsService service = (AliSmsService) getProxy(AliSmsService.class);
        service.send("CGLIB Method...");
    }
}
