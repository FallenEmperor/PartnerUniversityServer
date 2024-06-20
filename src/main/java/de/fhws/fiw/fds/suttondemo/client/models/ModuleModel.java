package de.fhws.fiw.fds.suttondemo.client.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.fhws.fiw.fds.sutton.client.converters.ClientLinkJsonConverter;
import de.fhws.fiw.fds.sutton.client.model.AbstractClientModel;
import de.fhws.fiw.fds.sutton.client.utils.Link;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ModuleModel extends AbstractClientModel {

    private String moduleName;
    private int semester;
    private int creditPoints;

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private transient Link selfLinkOnSecond;

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private Link selfLink;

    public ModuleModel() {
    }

    public ModuleModel(String moduleName, int semester, int creditPoints) {
        this.moduleName = moduleName;
        this.semester = semester;
        this.creditPoints = creditPoints;
    }

    // Getters and Setters

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        if (semester < 1 || semester > 2) {        // Assuming 1 for spring, 2 for autumn
            throw new IllegalArgumentException("Semester must be 1 (spring) or 2 (autumn).");
        }
        this.semester = semester;
    }

    public int getCreditPoints() {
        return creditPoints;
    }

    public void setCreditPoints(int creditPoints) {
        if (creditPoints <= 0) {        // Check that credits are non-negative
            throw new IllegalArgumentException("Credit points must be greater than 0.");
        }
        this.creditPoints = creditPoints;
    }

    @JsonIgnore
    public Link getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(Link selfLink) {
        this.selfLink = selfLink;
    }
    
    public Link getSelfLinkOnSecond() {
        return selfLinkOnSecond;
    }

    public void setSelfLinkOnSecond(Link selfLinkOnSecond) {
        this.selfLinkOnSecond = selfLinkOnSecond;
    }

    @Override
    public String toString() {
        return "ModuleModel{" +
                "moduleName='" + moduleName + '\'' +
                ", semester=" + semester +
                ", creditPoints=" + creditPoints +
                ", selfLink=" + selfLink +
                '}';
    }
}
