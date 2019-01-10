package uk.ac.qub.kettleaccess;

import com.google.gson.annotations.SerializedName;

/**
 * Class to define the parameters of the object "Bean"
 */

public class Bean {

    /**
     * Declaration of private variables to be used
     * as the parameters of the bean object
     */

    @SerializedName("name")
    private String Name;

    @SerializedName("origin")
    private String Origin;

    @SerializedName("origins")
    private String[] Origins;

    @SerializedName("temp")
    private int Temp;

    @SerializedName("desc")
    private String Description;

    @SerializedName("flag")
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
     */

    public Bean(String name, String origin, int temp, String description){
        this.Name = name;
        this.Origin = origin;
        this.Temp = temp;
        this.Description = description;

    }

    /**
     * Constructor used to pass the parameters of the bean
     * object to other classes in the project
     *
     * @param name
     * @param origin
     * @param origins
     * @param temp
     * @param description
     */

    public Bean(String name, String origin, String[] origins, int temp, String description){
        this.Name = name;
        this.Origin = origin;
        this.Origins = origins;
        this.Temp = temp;
        this.Description = description;

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
     * Getter for the origins parameter
     * @return
     */

    public String[] getOrigins() {
        return Origins;
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
     * Setter for the origins parameter
     * @param origins
     */

    public void setOrigins(String[] origins) {
        Origins = origins;
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