package com.example.devTimesheet.mapper;

import com.example.devTimesheet.dto.request.ClientRequest;
import com.example.devTimesheet.dto.respon.ClientRespon;
import com.example.devTimesheet.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toClient(ClientRequest request);
    void updateClient(@MappingTarget Client client, ClientRequest request);
    @Mapping(target = "projectRespons", source = "projects")
    ClientRespon toClientRespon(Client client);
}
