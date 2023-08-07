package com.DPDzero.services.impl;

import com.DPDzero.config.AppConstants;
import com.DPDzero.exception.ErrorsException;
import com.DPDzero.model.StoreData;
import com.DPDzero.repositories.StoreDataRepository;
import com.DPDzero.requests.StoreDataRequest;
import com.DPDzero.requests.UpdateRequest;
import com.DPDzero.responses.RetrieveDataResponse;
import com.DPDzero.responses.StoreDataResponse;
import com.DPDzero.services.StoreDataService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreDataServiceImpl implements StoreDataService {

    @Autowired
    private StoreDataRepository storeDataRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public StoreDataResponse storeData(StoreDataRequest request) {
        if(request.getKey_id()==null || request.getKey_id().isEmpty()){
            throw new ErrorsException(AppConstants.status,"INVALID_KEY",AppConstants.INVALID_KEY);
        }
        else if(request.getValue()==null || request.getValue().isEmpty()){
            throw new ErrorsException(AppConstants.status,"INVALID_VALUE",AppConstants.INVALID_VALUE);
        }
        else if(storeDataRepository.existsById(request.getKey_id()))
        {
            throw new ErrorsException(AppConstants.status,"KEY_EXISTS",AppConstants.KEY_EXISTS);
        }

        StoreData storeData=this.modelMapper.map(request,StoreData.class);
        this.storeDataRepository.save(storeData);
        StoreDataResponse storeDataResponse=new StoreDataResponse("success","Data stored successfully.");
        return storeDataResponse;
    }

    @Override
    public RetrieveDataResponse retrivedata(String id) {
        StoreData data=this.storeDataRepository.findById(id)
                .orElseThrow(()->new ErrorsException(AppConstants.status,"KEY_NOT_FOUND",AppConstants.KEY_NOT_FOUND));

        RetrieveDataResponse response=new RetrieveDataResponse("sucess",data);
        return response;
    }

    @Override
    public StoreDataResponse updateData(UpdateRequest request, String id) {
        StoreData data=this.storeDataRepository.findById(id)
                .orElseThrow(()->new ErrorsException(AppConstants.status,"KEY_NOT_FOUND",AppConstants.KEY_NOT_FOUND));
        data.setValue(request.getValue());
        this.storeDataRepository.save(data);

        StoreDataResponse response=new StoreDataResponse("success","Data updated successfully.");
        return response;
    }

    @Override
    public StoreDataResponse deleteData(String id) {
        StoreData data=this.storeDataRepository.findById(id)
                .orElseThrow(()->new ErrorsException(AppConstants.status,"KEY_NOT_FOUND",AppConstants.KEY_NOT_FOUND));
        this.storeDataRepository.delete(data);
        StoreDataResponse storeDataResponse=new StoreDataResponse("success","Data deleted successfully.");
        return storeDataResponse;
    }
}
