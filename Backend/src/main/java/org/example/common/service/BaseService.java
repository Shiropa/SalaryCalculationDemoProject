package org.example.common.service;

import lombok.NonNull;
import org.example.common.database.entities.BaseEntity;
import org.example.common.database.repositories.ServiceRepository;
import org.example.common.dataclass.BooleanValueHolderDTO;
import org.example.common.dataclass.IOidHolderRequestBodyDTO;
import org.example.helper.IdGeneratorComponent;
import org.example.helper.ServiceExceptionHolder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseService <E extends BaseEntity, D extends IOidHolderRequestBodyDTO>{

    @Autowired
    private IdGeneratorComponent idGeneratorComponent;

    private ModelMapper modelMapper;

    private final ServiceRepository<E> repository;

    protected BaseService(ServiceRepository<E> repository) {
        this.repository = repository;
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
    }

    public List<D> getList() {
        return convertForRead(repository.findAllByIsDeleted("No"));
    }

    public D getByOid(@NonNull String oid) {
        return convertForRead(repository.findByOidAndIsDeleted(oid,"No")
                .orElseThrow(() -> new ServiceExceptionHolder.ResourceNotFoundDuringWriteRequestException(
                "No " + getEntityClass().getSimpleName() + " Found with ID: " + oid)));
    }

    public List<D> getByOids(Set<String> oids) {
        List<E> list = repository.findAllByOidInAndIsDeleted(oids,"No");
        if (list.isEmpty()) return new ArrayList<>();
        return convertForRead(list);
    }

    public D create(D d) {
        return convertForRead(createEntity(d));
    }

    public D update(D d) {
        return convertForRead(updateEntity(d));
    }

    public BooleanValueHolderDTO delete(@NonNull String oid) {
        deleteEntity(oid);
        return new BooleanValueHolderDTO() {{ setValue(true); }};
    }

    protected E createEntity(D body) {
        E e = convertForCreate(body);
        e.setOid(idGeneratorComponent.generateId());
        e.setCreatedOn(new Date());
        e.setIsDeleted("No");
        return repository.save(e);
    }

    protected E updateEntity(D body) {
        String oid = body.getOid();
        if (oid == null)
            throw new ServiceExceptionHolder.ResourceNotFoundDuringWriteRequestException("No Oid Provided for " + getEntityClass().getSimpleName());
        E e = getByOidForRead(oid);
        convertForUpdate(body, e);
        e.setUpdatedOn(new Date());
        e.setIsDeleted("No");
        return repository.save(e);
    }

    protected void deleteEntity(@NonNull String oid) {
        if (oid == null)
            throw new ServiceExceptionHolder.ResourceNotFoundDuringWriteRequestException("No Oid Provided for " + getEntityClass().getSimpleName());
        E e = getByOidForRead(oid);
        e.setIsDeleted("Yes");
        e.setUpdatedOn(new Date());
        repository.save(e);
    }

    public E getByOidForRead(@NonNull String oid) {
        return repository.findByOidAndIsDeleted(oid,"No").orElse(null);
    }

    protected D convertForRead(E e) {
        return modelMapper.map(e, getDtoClass());
    }

    protected List<D> convertForRead(List<E> e) {
        return e.stream().map(this::convertForRead).collect(Collectors.toList());
    }

    protected E convertForCreate(D d) {
        return modelMapper.map(d, getEntityClass());
    }

    protected void convertForUpdate(D d, E e) {
        BeanUtils.copyProperties(d, e);
    }

    @SuppressWarnings("unchecked")
    private Class<E> getEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    private Class<D> getDtoClass() {
        return (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected boolean nullAndEmptyStringCheck(String str) {
        boolean response = false;
        if (str != null && !str.trim().isEmpty()) {
            response = true;
        }
        return response;
    }


}
