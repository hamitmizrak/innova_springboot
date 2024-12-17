package com.hamitmizrak.innova_springboot.business.services;

import java.util.List;

// D: Dto
// E: Entity
public interface ICrudService<D,E>  {

    // CREATE
    public D objectServiceCreate(D d);

    // LIST
    public List<D> objectServiceList();

    // FIND BY ID
    public D objectServiceFindById(Long id);

    // UPDATE
    public D objectServiceUpdate(Long id, D d);

    // DELETE
    public D objectServiceDelete(Long id);

}
