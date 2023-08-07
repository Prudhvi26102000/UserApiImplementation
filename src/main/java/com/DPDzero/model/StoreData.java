package com.DPDzero.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name="store_data")
@Data
public class StoreData {

    @Id
    private String key_id;
    private String value;

}
