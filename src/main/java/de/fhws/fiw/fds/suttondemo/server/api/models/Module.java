package de.fhws.fiw.fds.suttondemo.server.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.Link;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.annotations.SecondarySelfLink;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.annotations.SelfLink;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import jakarta.xml.bind.annotation.XmlRootElement;

@JsonRootName("module")
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement(name = "module")
public class Module extends AbstractModel {

    private String moduleName;
    private int semester;
    private int creditPoints;

    @SecondarySelfLink(
            primaryPathElement = "partners",
            secondaryPathElement = "modules"
    )
    private transient Link selfLinkOnSecond;

    @SelfLink(pathElement = "modules")
    private transient Link selfLink;

    public Module() {
        // make JPA happy
    }

    public Module(final String moduleName, final int semester, final int creditPoints) {
        this.moduleName = moduleName;
        this.semester = semester;
        this.creditPoints = creditPoints;
    }

    public Link getSelfLinkOnSecond() {
        return selfLinkOnSecond;
    }

    public void setSelfLinkOnSecond(final Link selfLinkOnSecond) {
        this.selfLinkOnSecond = selfLinkOnSecond;
    }

    public Link getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(final Link selfLink) {
        this.selfLink = selfLink;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(final String moduleName) {
        this.moduleName = moduleName;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(final int semester) {
        this.semester = semester;
    }

    public int getCreditPoints() {
        return creditPoints;
    }

    public void setCreditPoints(final int creditPoints) {
        this.creditPoints = creditPoints;
    }
}
