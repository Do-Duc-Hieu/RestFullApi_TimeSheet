package com.example.devTimesheet.service;

import com.example.devTimesheet.dto.request.ClientRequest;
import com.example.devTimesheet.dto.request.TaskRequest;
import com.example.devTimesheet.dto.respon.ClientRespon;
import com.example.devTimesheet.dto.respon.TaskRespon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface ClientService {

    ClientRespon createClient(ClientRequest clientRequest);

    ClientRespon getClient(Integer id);

    List<ClientRespon> findAllClient();

    ClientRespon updateClient(Integer idClient, ClientRequest request);

    void deleteClient(Integer idClient);

    public Page<ClientRespon> findAllClientPage(Pageable pageable);
}
