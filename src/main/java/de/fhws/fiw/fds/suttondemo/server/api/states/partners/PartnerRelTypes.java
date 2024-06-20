package de.fhws.fiw.fds.suttondemo.server.api.states.partners;

public interface PartnerRelTypes {

    String CREATE_PARTNER = "createPartner";
    String GET_ALL_PARTNERS = "getAllPartners";
    String UPDATE_SINGLE_PARTNER = "updatePartner";
    String DELETE_SINGLE_PARTNER = "deletePartner";
    String GET_SINGLE_PARTNER = "getPartner";
    String GET_ALL_MODULES_OF_PARTNER = "getAllModules";
    String CREATE_MODULE = "createModuleOfPartner";
    String FILTER_BY_NAME = "getFilteredPartners";
    String ORDERING = "getOrderedPartners";
    String FILTERANDORDER = "getFilteredAndOrderedPartners";

}
