package pl.mpas.parent_children.application;

import pl.mpas.parent_children.service.WelcomeService;

public class Application {

    public static void main(String[] args) {
        WelcomeService welcomeService = new WelcomeService(WelcomeService.EN_LANG);
        welcomeService.sayHello();
    }
}
