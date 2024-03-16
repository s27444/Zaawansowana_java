package pl.pjatk.adrkem;

import org.springframework.stereotype.Component;

@Component
public class MyFirstComponent {
    public MyFirstComponent(){
        System.out.println("Hello from my first component");
    }

    public void ShowMyClassNameAndMethodName(){
        String nameMethod = new Object(){}.getClass().getEnclosingMethod().getName();
        String className = new Object(){}.getClass().getName();

        System.out.println("Class name: " + className + " Method name: " + nameMethod);
    }

}
