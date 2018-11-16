package uk.ac.qub.kettleaccess;

public class Bean {

    private String Name;
    private String Origin;
    private int Temp;
    private String Description;

    public Bean() {
    }

    public Bean(String name, String origin, int temp, String description){
        Name = name;
        Origin = origin;
        Temp = temp;
        Description = description;
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
}
