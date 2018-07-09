package controler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import model.Studentdetail;
import service.RegistrationDBOperation;

/**
 *
 * @author LEOGOLD
 */
@ManagedBean
@Named(value = "registration")
@SessionScoped
public class RegistrationForm implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private String userId;
    private String firstName;
    private String lastName;
    private String gender;
    private Date dob;
    private String address;
    private String phone;
    private String email;
    private String password;
    private String confirmPassword;
    private String finalPassword;
    private String userIDError;
    
    public RegistrationForm() {
    }
    
    public RegistrationForm(String firstName, String lastName, String gender, String address, String contactNumber, String emailID, String userID, String fPassword, Date dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.phone = contactNumber;
        this.email = emailID;
        this.userId = userID;        
        this.finalPassword = fPassword;
        this.dob = dob;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getConfirmPassword() {
        return confirmPassword;
    }
    
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    public Date getDob() {
        return dob;
    }
    
    public void setDob(Date d) {
        this.dob = d;
    }
    
    public String getFinalPassword() {
        return finalPassword;
    }
    
    public void setFinalPassword(String finalPassword) {
        this.finalPassword = finalPassword;
    }

    public String getUserIDError() {
        return userIDError;
    }
    
    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }
    
    public void validateEmail(FacesContext fc, UIComponent c, Object value) throws ValidatorException {
        String email = (String) value;
        Pattern mask = null;
        mask = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher matcher = mask.matcher(email);
        if (!matcher.matches()) {
            FacesMessage message = new FacesMessage();
            message.setDetail("Invalid e-mail ID");
            message.setSummary("Input a valid e-mail");
            throw new ValidatorException(message);
        }
    }
    
    public void validateFinalPassword(FacesContext fc, UIComponent c, Object value) throws ValidatorException {
        setFinalPassword((String) value);
        getFinalPassword();
    }
    
    public void validateCPassword(FacesContext fc, UIComponent c, Object value) throws ValidatorException {
        String cPassword = (String) value;
        //  RegistrationForm regForm = new RegistrationForm(); 
        if (cPassword.compareTo(getFinalPassword()) != 0) {
            FacesMessage message = new FacesMessage();
            message.setSummary("Password does not match");
            throw new ValidatorException(message);
        }
        
    }

    public void validateInput(FacesContext fc, UIComponent c, Object value) {
        if (((String) value).contains("!") || ((String) value).contains("@") || ((String) value).contains("#") || ((String) value).contains("$") || ((String) value).contains("%") || ((String) value).contains("^") || ((String) value).contains("&") || ((String) value).contains("*") || ((String) value).contains("(") || ((String) value).contains(")") || ((String) value).contains("<") || ((String) value).contains(">") || ((String) value).contains("/") || ((String) value).contains("?") || ((String) value).contains(";") || ((String) value).contains(":") || ((String) value).contains("|") || ((String) value).contains("\\")) {
            throw new ValidatorException(new FacesMessage("Cannot contain special character"));
        }
        
    }
    
    //This method is being called on the registration form page when a user submits form
    public String save() {
        
        RegistrationDBOperation regOperation = new RegistrationDBOperation();
        
        //Calling the Studentdetail Entity class mapped to DB and passing the values from the registrationForm bean
        //through its constructor
        Studentdetail student = new Studentdetail(userId, firstName, lastName, gender, dob, address, phone, email, password);
        
//        student.setFirstname(getFirstName());
//        student.setLastname(getLastName());
//        student.setGender(getGender());
//        student.setDob(getDOB());
//        student.setAddress(getAddress());
//        student.setPhone(getContactNumber());
//        student.setEmail(getEmailID());
//        student.setUserid(getUserID());
//        student.setPassword(getPassword());
        

    //calling the save() method in RegistrationDBOperation and returns a String value either "true" or "error"
        String message = regOperation.save(student);
        
        //This uses the retured value of save method notifies the user of the existence of the chosing detail
        if(message.equals("error")){
            setUserIDError("User Already exist. Please select another user");
            setUserId("");
            return "registration";
        }
        else {
            return "success";
        }
    }
}
