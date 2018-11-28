package uk.ac.qub.kettleaccess;

/**
 * Class to define the parameters of the object "Bean"
 */

public class Bean {

    /**
     * Declaration of private variables to be used
     * as the parameters of the bean object
     */

    private String Name;
    private String Origin;
    private int Temp;
    private String Description;
    private int Image;


    /**
     * A required public constructor
     */

    public Bean() {
    }


    /**
     * Constructor used to pass the parameters of the bean
     * object to other classes in the project
     *
     * @param name
     * @param origin
     * @param temp
     * @param description
     * @param image
     */

    public Bean(String name, String origin, int temp, String description, int image){
        Name = name;
        Origin = origin;
        Temp = temp;
        Description = description;
        Image = image;

    }

    /**
     * Getter for the name parameter
     * @return name
     */

    public String getName() {
        return Name;
    }

    /**
     * Getter for the origin parameter
     * @return
     */

    public String getOrigin() {
        return Origin;
    }

    /**
     * Getter for the temperature parameter
     * @return
     */

    public int getTemp() {
        return Temp;
    }

    /**
     * Getter for the description parameter
     * @return
     */

    public String getDescription(){
        return Description;
    }

    /**
     * Getter for the image parameter
     * @return
     */

    public int getImage(){
        return Image;
    }


    /**
     * Setter for the name parameter
     * @param name
     */

    public void setName(String name) {
        Name = name;
    }

    /**
     * Setter for the origin parameter
     * @param origin
     */

    public void setOrigin(String origin) {
        Origin = origin;
    }

    /**
     * Setter for the temperature parameter
     * @param temp
     */

    public void setTemp(int temp) {
        Temp = temp;
    }

    /**
     * Setter for the description parameter
     * @param description
     */

    public void setDescription(String description){
        Description = description;
    }

    /**
     * Setter for the image parameter
     * @param image
     */

    public void setImage (int image){
        Image = image;
    }
}