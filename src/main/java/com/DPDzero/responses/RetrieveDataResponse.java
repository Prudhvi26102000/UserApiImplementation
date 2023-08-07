package com.DPDzero.responses;

import com.DPDzero.model.StoreData;
import com.DPDzero.requests.StoreDataRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RetrieveDataResponse {

    private String status;
    private StoreData data;
}
