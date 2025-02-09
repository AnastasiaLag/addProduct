package ismgroup58.addProduct.app;

public class User {
    
    private String name;
    private String email;
    private String ssn;
    private String username;
    private String password;
    private String phone;
    private String city;
    private String address;
    private int zip;
    private String image;
    private String joined;

    /* First Constructor PROBBABLY WON'T NEED IT IN THIS PROJECT*/
    public User(String name, String email, String ssn,
                String username, String password) {
        this.name = name;
        this.email = email;
        this.ssn = ssn;
        this.username = username;
        this.password = password;
        this.image = "resources/static/images/profile-default.png";
    }

    /* Second Constructor */
    public User(String name, String email, String ssn,
                String username, String password, String phone, 
                String city, String address, int zip, String image, String joined) {
        this.name = name;
        this.email = email;
        this.ssn = ssn;
        this.username = username;
        this.password = password;
        this.image = image;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.zip = zip;
        this.joined = joined;
    }

    /* Getters */
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return this.email;
    }
    public String getSSN() {
        return this.ssn;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public String getJoined() {
        return this.joined;
    }
    
    public String getImage() {
        return this.image;
    }
    
    public String getCity() {
        return this.city;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public String getPhone() {
        return this.phone;
    }
    
    public int getZip() {
        return this.zip;
    }
}
