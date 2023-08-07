package com.DPDzero.services;

import com.DPDzero.requests.StoreDataRequest;
import com.DPDzero.requests.UpdateRequest;
import com.DPDzero.responses.RetrieveDataResponse;
import com.DPDzero.responses.StoreDataResponse;

public interface StoreDataService {

    StoreDataResponse storeData(StoreDataRequest request);
    RetrieveDataResponse retrivedata(String id);
    StoreDataResponse updateData(UpdateRequest request, String id);
    StoreDataResponse deleteData(String id);
}
