package uploaderDB;


import java.io.Serializable;

public class UploaderImpl implements Uploader, Serializable {
    private String name;
    public UploaderImpl(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public String toString(){
        return this.getName();
    }
}
