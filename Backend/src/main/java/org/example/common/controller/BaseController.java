package org.example.common.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.database.entities.BaseEntity;
import org.example.common.dataclass.BooleanValueHolderDTO;
import org.example.common.dataclass.IOidHolderRequestBodyDTO;
import org.example.common.service.BaseService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public abstract class BaseController<E extends BaseEntity, D extends IOidHolderRequestBodyDTO> {

    private final BaseService<E, D> service;

    @GetMapping(path="get-list", produces = "application/json")
    public @ResponseBody List<D> getList() {
        return service.getList();
    }

    @Transactional
    @PostMapping(path="create", produces = "application/json")
    public @ResponseBody D create(@RequestBody D d) {
        return service.create(d);
    }

    @GetMapping(path="get-by-oid/{oid}", produces = "application/json")
    public @ResponseBody D getByOid(@PathVariable String oid) {
        return service.getByOid(oid);
    }

    @Transactional
    @PutMapping(path="update", produces = "application/json")
    public @ResponseBody D update(@RequestBody D d) {
        return service.update(d);
    }

    @DeleteMapping(path="delete/{oid}", produces = "application/json")
    public @ResponseBody BooleanValueHolderDTO delete(@PathVariable String oid) {
        return service.delete(oid);
    }
}
