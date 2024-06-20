package de.fhws.fiw.fds.suttondemo.client.rest;


import de.fhws.fiw.fds.sutton.client.rest2.AbstractRestClient;
import de.fhws.fiw.fds.suttondemo.client.models.PartnerUniversityModel;
import de.fhws.fiw.fds.suttondemo.client.models.ModuleModel;
import de.fhws.fiw.fds.suttondemo.client.web.PartnerUniversityWebClient;
import de.fhws.fiw.fds.suttondemo.client.web.ModuleWebClient;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RealRestClient extends AbstractRestClient {
    private static final String BASE_URL = "http://localhost:8080/demo/api";
    private static final String CREATE_PARTNER = "createPartner";
    private static final String GET_ALL_PARTNERS = "getAllPartners";
    private static final String UPDATE_SINGLE_PARTNER = "updatePartner";
    private static final String DELETE_SINGLE_PARTNER = "deletePartner";
    private static final String GET_SINGLE_PARTNER = "getPartner";

    private static final String GET_ALL_MODULES_OF_PARTNER = "getAllModules";
    private static final String CREATE_MODULE = "createModuleOfPartner";
	private static final String UPDATE_SINGLE_MODULE = "updateModuleOfPartner";
    private static final String DELETE_MODULE_OF_PARTNER = "deletePartnerOfModule";
    private static final String GET_SINGLE_MODULE = "getModule";


    private List<PartnerUniversityModel> currentPartnerData;
    private int cursorPartnerData = 0;

    private List<ModuleModel> currentModuleData;
    private int cursorModuleData = 0;

    final private PartnerUniversityWebClient partnerClient;
    final private ModuleWebClient moduleClient;

    public RealRestClient() {
        super();
        this.partnerClient = new PartnerUniversityWebClient();
        this.moduleClient = new ModuleWebClient();
        this.currentPartnerData = Collections.EMPTY_LIST;
        this.currentModuleData = Collections.EMPTY_LIST;
    }

    public void resetDatabase() throws IOException {
        processResponse(this.partnerClient.resetDatabaseOnServer(BASE_URL), (response) -> {
        });
        processResponse(this.moduleClient.resetDatabaseOnServer(BASE_URL), (response) -> {
        });
    }

    // Only need the one dispatcher, not necessary from Module side

    public void start() throws IOException {
        processResponse(this.partnerClient.getDispatcher(BASE_URL), (response) -> {
        });
    }

    public boolean isCreatePartnerAllowed() {
        return isLinkAvailable(CREATE_PARTNER);
    }

    public void createPartner(PartnerUniversityModel university) throws IOException {
        if (isCreatePartnerAllowed()) {
            processResponse(this.partnerClient.postNewPartner(getUrl(CREATE_PARTNER), university), (response) -> {
                this.currentPartnerData = Collections.EMPTY_LIST;
                this.cursorPartnerData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isGetAllPartnersAllowed() {
        return isLinkAvailable(GET_ALL_PARTNERS);
    }

    public void getAllPartners() throws IOException {
        if (isGetAllPartnersAllowed()) {
            processResponse(this.partnerClient.getCollectionOfPartners(getUrl(GET_ALL_PARTNERS)), (response) -> {
                this.currentPartnerData = new LinkedList(response.getResponseData());
                this.cursorPartnerData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isGetSinglePartnerAllowed() {
        return !this.currentPartnerData.isEmpty() || isLocationHeaderAvailable() || isLinkAvailable(GET_SINGLE_PARTNER);
    }

    public List<PartnerUniversityModel> partnerData() {
        if (this.currentPartnerData.isEmpty()) {
            throw new IllegalStateException();
        }

        return this.currentPartnerData;
    }

    public void setPartnerCursor(int index) {
        if (0 <= index && index < this.currentPartnerData.size()) {
            this.cursorPartnerData = index;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void getSinglePartner() throws IOException {
        if ( isLocationHeaderAvailable()) {
            getSinglePartner(getLocationHeaderURL());
        }
        else if (!this.currentPartnerData.isEmpty()) {
            getSinglePartner(this.cursorPartnerData);
        }
        else {
            throw new IllegalStateException();
        }
    }

    public void getSinglePartner(int index) throws IOException {
        getSinglePartner(this.currentPartnerData.get(index).getSelfLink().getUrl());
    }

    private void getSinglePartner(String url) throws IOException {
        processResponse(this.partnerClient.getSinglePartner(url), (response) -> {
            this.currentPartnerData = new LinkedList(response.getResponseData());
            this.cursorPartnerData = 0;
        });
    }

    public boolean isUpdatePartnerAllowed() {
        return isLinkAvailable(UPDATE_SINGLE_PARTNER);
    }

    public void updatePartner(PartnerUniversityModel partner) throws IOException {
        if(isUpdatePartnerAllowed()){
            processResponse(this.partnerClient.putPartner(getUrl(UPDATE_SINGLE_PARTNER), partner), (response) -> {
                this.currentPartnerData = Collections.EMPTY_LIST;
                this.cursorPartnerData = 0;
            });
        } else{
            throw new IllegalStateException();
        }
    }

    public boolean isDeletePartnerAllowed() {
        return isLinkAvailable(DELETE_SINGLE_PARTNER);
    }

    public void deletePartner() throws IOException {
        if (isDeletePartnerAllowed()) {
            processResponse(this.partnerClient.deletePartner(getUrl(DELETE_SINGLE_PARTNER)), (response) -> {
                this.currentPartnerData = Collections.EMPTY_LIST;
                this.cursorPartnerData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }
    

    /* Here begin thine module methods, behold! */

    public boolean isGetAllModulesPartnerAllowed() {
        return isLinkAvailable(GET_ALL_MODULES_OF_PARTNER);
    }

    public void getAllModulesOfPartner() throws IOException {
        if (isGetAllModulesPartnerAllowed()) {
            processResponse(this.moduleClient.getCollectionOfModules(getUrl(GET_ALL_MODULES_OF_PARTNER)), (response) -> {
                this.currentModuleData = new LinkedList(response.getResponseData());
                this.cursorModuleData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isCreateSingleModuleOfPartnerAllowed() {
        return isLinkAvailable(CREATE_MODULE);
    }
    
    public void createModuleOfPartner(ModuleModel module) throws IOException {
        if (isCreateSingleModuleOfPartnerAllowed()) {
            processResponse(this.moduleClient.postNewModule(getUrl(CREATE_MODULE), module), (response) -> {
                this.currentModuleData = Collections.EMPTY_LIST;
                this.cursorModuleData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isGetSingleModuleAllowed(){
        return !this.currentModuleData.isEmpty() || isLocationHeaderAvailable() || isLinkAvailable(GET_SINGLE_MODULE);
    }

    public List<ModuleModel> moduleData(){
        if(this.currentModuleData.isEmpty()){
            throw new IllegalStateException();
        }

        return this.currentModuleData;
    }

    public void setModuleCursor(int index) {
        if (0 <= index && index < this.currentModuleData.size()) {
            this.cursorModuleData = index;
        } else {
            throw new IllegalArgumentException();
        }
    }


    public void getSingleModule() throws IOException {
        if ( isLocationHeaderAvailable()) {
            getSingleModule(getLocationHeaderURL());
        }
        else if (isGetSingleModuleAllowed()){
            getSingleModule(getUrl(GET_SINGLE_MODULE));
        }
        else if (!this.currentModuleData.isEmpty()) {
            getSingleModule(this.cursorModuleData);
        }
        else {
            throw new IllegalStateException();
        }
    }

    public void getSingleModule(int index) throws IOException {
        getSingleModule(this.currentModuleData.get(index).getSelfLink().getUrl());
    }

    private void getSingleModule(String url) throws IOException {
        processResponse(this.moduleClient.getSingleModule(url), (response) -> {
            this.currentModuleData = new LinkedList<>(response.getResponseData());
            this.cursorModuleData = 0;
        });
    }

    public boolean isUpdateSingleModuleOfPartnerAllowed() {
        return isLinkAvailable(UPDATE_SINGLE_MODULE);
    }

    public void updateModule(ModuleModel module) throws IOException {
        if (isUpdateSingleModuleOfPartnerAllowed()) {
            processResponse(this.moduleClient.putModule(getUrl(UPDATE_SINGLE_MODULE), module), (response) -> {
                this.currentModuleData = Collections.EMPTY_LIST;
                this.cursorModuleData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }
    
    public boolean isDeleteSingleModuleOfPartnerAllowed() {
        return isLinkAvailable(DELETE_MODULE_OF_PARTNER);
    }

    public void deleteModule() throws IOException {
        if (isDeleteSingleModuleOfPartnerAllowed()) {
            processResponse(this.moduleClient.deleteModule(getUrl(DELETE_MODULE_OF_PARTNER)), (response) -> {
                this.currentModuleData = Collections.EMPTY_LIST;
                this.cursorModuleData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }


}
