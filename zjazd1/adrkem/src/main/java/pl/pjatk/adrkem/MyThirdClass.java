package pl.pjatk.adrkem;

import java.util.List;

public class MyThirdClass {
    List<String> defaultData;

    public MyThirdClass(List<String> defaultData) {
        this.defaultData = defaultData;
    }

    public List<String> getDefaultData() {
        return defaultData;
    }

    public void setDefaultData(List<String> defaultData) {
        this.defaultData = defaultData;
    }
}
