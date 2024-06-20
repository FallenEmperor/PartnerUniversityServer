 package de.fhws.fiw.fds.suttondemo.server.api.models;

 import com.fasterxml.jackson.annotation.JsonInclude;
 import com.fasterxml.jackson.annotation.JsonRootName;
 import de.fhws.fiw.fds.sutton.server.api.hyperlinks.Link;
 import de.fhws.fiw.fds.sutton.server.api.hyperlinks.annotations.SuttonLink;
 import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
 import jakarta.xml.bind.annotation.XmlRootElement;
 
 @JsonRootName("partner")
 @JsonInclude(JsonInclude.Include.NON_NULL)
 @XmlRootElement(name = "partner")

 public class PartnerUniversity extends AbstractModel {
 
    private String universityName;
    private String country;
    private String departmentName;
    private String departmentUrl;
    private String contactPerson;
    private int outgoingStudents;
    private int incomingStudents;
    private String springSemesterStart;
    private String autumnSemesterStart;
 
    @SuttonLink(
            value = "partners/${id}",
            rel = "self"
    )
    private transient Link selfLink;

    @SuttonLink(
            value = "partners/${id}/modules",
            rel = "getLocationsOfPerson"
    )
    private transient Link moduleLink;
 
     public PartnerUniversity() {
         // make JPA happy
     }
 
    public PartnerUniversity(String universityName, String country, String departmentName, String departmentUrl,
                                  String contactPerson, int outgoingStudents, int incomingStudents, String springSemesterStart,
                                  String autumnSemesterStart) {
        this.universityName = universityName;
        this.country = country;
        this.departmentName = departmentName;
        this.departmentUrl = departmentUrl;
        this.contactPerson = contactPerson;
        this.outgoingStudents = outgoingStudents;
        this.incomingStudents = incomingStudents;
        this.springSemesterStart = springSemesterStart;
        this.autumnSemesterStart = autumnSemesterStart;
    }
 
     public Link getSelfLink() {
         return selfLink;
     }
     public void setSelfLink(Link selfLink) {
         this.selfLink = selfLink;
     }
 
     public Link getModuleLink() {
         return moduleLink;
     }
 
     public void setModuleLink(Link moduleLink) {
         this.moduleLink = moduleLink;
     }
 
     public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentUrl() {
        return departmentUrl;
    }

    public void setDepartmentUrl(String departmentUrl) {
        this.departmentUrl = departmentUrl;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public int getOutgoingStudents() {
        return outgoingStudents;
    }

    public void setOutgoingStudents(int outgoingStudents) {
        if (outgoingStudents < 0) {         // Check for negative (invalid) input
            throw new IllegalArgumentException("Number of outgoing students cannot be negative.");
        }
        this.outgoingStudents = outgoingStudents;
    }

    public int getIncomingStudents() {
        return incomingStudents;
    }

    public void setIncomingStudents(int incomingStudents) {
        if (incomingStudents < 0) {         // Check for negative (invalid) input
            throw new IllegalArgumentException("Number of incoming students cannot be negative.");
        }
        this.incomingStudents = incomingStudents;
    }

    public String getSpringSemesterStart() {
        return springSemesterStart;
    }

    public void setSpringSemesterStart(String springSemesterStart) {
        this.springSemesterStart = springSemesterStart;
    }

    public String getAutumnSemesterStart() {
        return autumnSemesterStart;
    }

    public void setAutumnSemesterStart(String autumnSemesterStart) {
        this.autumnSemesterStart = autumnSemesterStart;
    }

    @Override
    public String toString() {
        return "PartnerUniversityModel{" +
                "universityName='" + universityName + '\'' +
                ", country='" + country + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", departmentUrl='" + departmentUrl + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", outgoingStudents=" + outgoingStudents +
                ", incomingStudents=" + incomingStudents +
                ", springSemesterStart='" + springSemesterStart + '\'' +
                ", autumnSemesterStart='" + autumnSemesterStart + '\'';
    }
 }
 
