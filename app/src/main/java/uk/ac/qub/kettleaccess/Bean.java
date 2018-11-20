package uk.ac.qub.kettleaccess;

public class Bean {

    private String Name;
    private String Origin;
    private int Temp;
    private String Description;
    private int Image;

    public Bean() {
    }

    public Bean(String name, String origin, int temp, String description, int image){
        Name = name;
        Origin = origin;
        Temp = temp;
        Description = description;
        Image = image;

    }

    //Getter


    public String getName() {
        return Name;
    }

    public String getOrigin() {
        return Origin;
    }

    public int getTemp() {
        return Temp;
    }

    public String getDescription(){
        return Description;
    }

    public int getImage(){
        return Image;
    }

    //Setter


    public void setName(String name) {
        Name = name;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public void setTemp(int temp) {
        Temp = temp;
    }

    public void setDescription(String description){
        Description = description;
    }

    public void setImage (int image){
        Image = image;
    }
}
