package org.iesfm.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(CompanyConfiguration.class);
        Menu menu = context.getBean(Menu.class);
        menu.run();

    }
}
