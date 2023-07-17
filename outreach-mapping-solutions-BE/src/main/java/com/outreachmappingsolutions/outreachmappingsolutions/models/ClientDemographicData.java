package com.outreachmappingsolutions.outreachmappingsolutions.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="client_demographic_data")
public class ClientDemographicData {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native", strategy="native")
    @Column(name="client_id")
    private Integer id;

//    private ClientBase client;
}
