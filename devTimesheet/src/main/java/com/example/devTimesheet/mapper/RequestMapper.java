package com.example.devTimesheet.mapper;

import com.example.devTimesheet.dto.request.*;
import com.example.devTimesheet.dto.respon.*;
import com.example.devTimesheet.entity.*;
import org.hibernate.Hibernate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {
        StatusMapper.class,
        UserMapper.class,
        RequestLastMapper.class,
        RequestRemoteMapper.class,
        RequestWorkTimeMapper.class,
        RequestOffMapper.class
})
public interface RequestMapper {
    @Mapping(target = "requestType", source = "requestType", qualifiedByName = "mapRequestType")
    Request toRequest(RequestRequest requestRequest);

    @Mapping(target = "requestType", source = "requestType", qualifiedByName = "mapRequestType")
    void updateRequest(@MappingTarget Request request, RequestRequest requestRequest);

    @Mapping(target = "requestTypeRespon", source = "requestType", qualifiedByName = "mapRequestTypeRespon")
    @Mapping(target = "userRespon", source = "user")
    @Mapping(target = "statusRespon", source = "status")
    RequestRespon toRequestRespon(Request request);

    @Named("mapRequestType")
    default RequestType mapRequestType(RequestTypeRequest requestType) {
        if (requestType == null || requestType.getType() == null) {
            return null;
        }

        switch (requestType.getType()) {
            case "LAST":
                return toRequestLast((RequestLastRequest) requestType);
            case "REMOTE":
                return toRequestRemote((RequestRemoteRequest) requestType);
            case "WORKTIME":
                return toRequestWorkTime((RequestWorkTimeRequest) requestType);
            case "OFF":
                return toRequestOff((RequestOffRequest) requestType);
            default:
                throw new IllegalArgumentException("Unknown request type: " + requestType.getType());
        }
    }

    @Named("mapRequestTypeRespon")
    default RequestTypeRespon mapRequestTypeRespon(RequestType requestType) {
        if (requestType == null) {
            return null;
        }
        RequestType realRequestType = (RequestType) Hibernate.unproxy(requestType);
        if (realRequestType instanceof RequestLast) {
            RequestLastRespon requestLastRespon = toRequestLastRespon((RequestLast) realRequestType);
            requestLastRespon.setType("LAST");
            return requestLastRespon;
        } else if (realRequestType instanceof RequestRemote) {
            RequestRemoteRespon requestRemoteRespon = toRequestRemoteRespon((RequestRemote) realRequestType);
            requestRemoteRespon.setType("REMOTE");
            return requestRemoteRespon;
        } else if (realRequestType instanceof RequestWorkTime) {
            RequestWorkTimeRespon requestWorkTimeRespon = toRequestWorkTimeRespon((RequestWorkTime) realRequestType);
            requestWorkTimeRespon.setType("WORKTIME");
            return requestWorkTimeRespon;
        } else if (realRequestType instanceof RequestOff) {
            RequestOffRespon requestOffRespon = toRequestOffRespon((RequestOff) realRequestType);
            requestOffRespon.setType("OFF");
            return requestOffRespon;
        } else {
            throw new IllegalArgumentException("Unknown request type");
        }
    }

    RequestLast toRequestLast(RequestLastRequest requestLast);
    RequestRemote toRequestRemote(RequestRemoteRequest requestRemote);
    RequestWorkTime toRequestWorkTime(RequestWorkTimeRequest requestWorkTime);
    RequestOff toRequestOff(RequestOffRequest requestOff);

    RequestLastRespon toRequestLastRespon(RequestLast requestLast);
    RequestRemoteRespon toRequestRemoteRespon(RequestRemote requestRemote);
    RequestWorkTimeRespon toRequestWorkTimeRespon(RequestWorkTime requestWorkTime);
    RequestOffRespon toRequestOffRespon(RequestOff requestOff);
}
