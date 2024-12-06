package com.example.devTimesheet.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.devTimesheet.dto.request.ClientRequest;
import com.example.devTimesheet.dto.respon.ClientRespon;

public interface ClientService {

    ClientRespon createClient(ClientRequest clientRequest);

    ClientRespon getClient(Integer id);

    List<ClientRespon> findAllClient();

    ClientRespon updateClient(Integer idClient, ClientRequest request);

    void deleteClient(Integer idClient);

    public Page<ClientRespon> findAllClientPage(Pageable pageable);
}
