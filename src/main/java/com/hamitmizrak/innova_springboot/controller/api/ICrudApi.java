package com.hamitmizrak.innova_springboot.controller.api;

import org.springframework.http.ResponseEntity;

import java.util.List;

// D: Dto
public interface ICrudApi<D> {

    // CREATE
    public ResponseEntity<?> objectApiCreate(D d);

    // LIST
    public ResponseEntity<List<D>> objectApiList();

    // FIND BY ID
    public ResponseEntity<?> objectApiFindById(Long id);

    // UPDATE
    public ResponseEntity<?> objectApiUpdate(Long id, D d);

    // DELETE
    public ResponseEntity<?> objectApiDelete(Long id);

}
