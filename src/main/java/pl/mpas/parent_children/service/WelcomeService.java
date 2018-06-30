package pl.mpas.parent_children.service;

public class WelcomeService {
    public static final String PL_LANG = "pl";
    public static final String EN_LANG = "en";

    private String lang;

    public WelcomeService(String lang) {
        this.lang = lang;
    }

    public void sayHello() {
        switch (lang) {
            case PL_LANG:
                System.out.println("Witaj przyjacielu!");
                break;
            case EN_LANG:
                System.out.println("Hello my friend!!!");
                break;
                default:
        }
    }
}
