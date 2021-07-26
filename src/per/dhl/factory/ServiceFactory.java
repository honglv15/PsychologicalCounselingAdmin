package per.dhl.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceFactory {

    private static ServiceFactory factory = new ServiceFactory();
    private ServiceFactory() {}
    public static ServiceFactory newInstance() {
        return factory;
    }

    private ApplicationContext context = new ClassPathXmlApplicationContext(
            "application.xml");

    public <T>T getService(Class<T> clazz) {
        return context.getBean(clazz);
    }
}
