package pl.pjatk.adrkem;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MySecondComponent {
    public MySecondComponent(){
        System.out.println("This is my second Component");
    }

    public void ShowMyClassNameAndMethodName(){
        String nameMethod = new Object(){}.getClass().getEnclosingMethod().getName();
        String className = new Object(){}.getClass().getName();


        System.out.println("Class name: " + className + " Method name: " + nameMethod);
    }
}
