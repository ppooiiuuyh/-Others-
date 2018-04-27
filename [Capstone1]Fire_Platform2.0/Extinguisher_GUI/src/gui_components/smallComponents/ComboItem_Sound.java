package gui_components.smallComponents;

/**
 * Created by ppooi on 2017-03-27.
 */
public class ComboItem_Sound {
    String fileName;

    public ComboItem_Sound(String fileName){
        this.fileName = fileName;
    }

    public String getFileName(){
        return fileName;
    }

    @Override
    public String toString() {
        return fileName;
    }
}
