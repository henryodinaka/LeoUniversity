package controler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author LEOGOLD
 */
@Named(value = "courseDetailsBean")
@RequestScoped
public class CourseDetailsBean {

   private  String courseName;
   private HtmlOutputText courseId, courseDuration,courseEligibility,courseFee;
   
    public CourseDetailsBean() {
    }
    public void courseDetailsAction(ValueChangeEvent vce){
        String iD = vce.getNewValue().toString();
        String cId = " ", cDuration =" ", cEligibility = " ",cFee =" ";
        if(iD.compareTo("Science")==0){
            cId = "A-101";
            cDuration ="4 Years";
            cEligibility = "70% in high school";
            cFee = "$5000 p.a";
        }
        else
            if(iD.compareTo("Law")==0){
                cId = "A-105";
            cDuration ="5 Years";
            cEligibility = "60% in high school";
            cFee = "$3000";
            }
        else
            if(iD.compareTo("Medical")==0){
                cId = "A-110";
            cDuration ="6 Years";
            cEligibility = "70% in high school";
            cFee = "$7000";
            }
        else
            if(iD.compareTo("Management")==0){
                cId = "A-115";
            cDuration ="2 Years";
            cEligibility = "50% in high school";
            cFee = "$3000";
            }
        getCourseId().setValue(cId);
        getCourseDuration().setValue(cDuration);
        getCourseEligibility().setValue(cEligibility);
        getCourseFee().setValue(cFee);
        
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public HtmlOutputText getCourseId() {
        return courseId;
    }

    public void setCourseId(HtmlOutputText courseId) {
        this.courseId = courseId;
    }

    public HtmlOutputText getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(HtmlOutputText courseDuration) {
        this.courseDuration = courseDuration;
    }

    public HtmlOutputText getCourseEligibility() {
        return courseEligibility;
    }

    public void setCourseEligibility(HtmlOutputText courseEligibility) {
        this.courseEligibility = courseEligibility;
    }

    public HtmlOutputText getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(HtmlOutputText courseFee) {
        this.courseFee = courseFee;
    }
}
