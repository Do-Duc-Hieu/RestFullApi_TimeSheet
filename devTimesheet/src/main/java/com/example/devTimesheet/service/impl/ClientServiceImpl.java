package com.example.devTimesheet.service.impl;

import com.example.devTimesheet.dto.request.ClientRequest;
import com.example.devTimesheet.dto.respon.ClientRespon;
import com.example.devTimesheet.dto.respon.UserRespon;
import com.example.devTimesheet.entity.*;
import com.example.devTimesheet.exception.AppException;
import com.example.devTimesheet.exception.ErrorCode;
import com.example.devTimesheet.mapper.ClientMapper;
import com.example.devTimesheet.repository.ClientRepository;
import com.example.devTimesheet.repository.ProjectRepository;
import com.example.devTimesheet.service.ClientService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientServiceImpl implements ClientService {
    ClientRepository clientRepository;
    ProjectRepository projectRepository;
    ClientMapper clientMapper;

    @Override
    public ClientRespon createClient(ClientRequest request) {
        if(clientRepository.existsByNameClient(request.getNameClient())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        Client client = clientMapper.toClient(request);
        clientRepository.save(client);
        return clientMapper.toClientRespon(client);
    }

    @Override
    public ClientRespon getClient(Integer id) {
        return clientMapper.toClientRespon(clientRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.USER_EXISTED)));
    }

    @Override
    public List<ClientRespon> findAllClient() {
        List<ClientRespon> clientRespons = new ArrayList<>();
        List<Client> clients = clientRepository.findAll();
        clients.forEach(
                client -> clientRespons.add(clientMapper.toClientRespon(client))

        );
        return clientRespons;
    }

    @Override
    public ClientRespon updateClient(Integer idClient, ClientRequest request) {
        Client client = clientRepository.findById(idClient)
                .orElseThrow(()-> new RuntimeException("Client not found"));
        clientMapper.updateClient(client, request);
        return clientMapper.toClientRespon(clientRepository.save(client));
    }

    @Override
    public void deleteClient(Integer idClient) {
        clientRepository.deleteById(idClient);
    }

    @Override
    public Page<ClientRespon> findAllClientPage(Pageable pageable) {
        Page<Client> clientPage = clientRepository.findAll(pageable);
        return clientPage.map(clientMapper::toClientRespon);
    }
}
