package com.hamitmizrak.innova_springboot.business.services.interfaces;

import com.hamitmizrak.innova_springboot.business.services.ICrudService;
import com.hamitmizrak.innova_springboot.business.services.IModelMapperService;
import com.hamitmizrak.innova_springboot.business.services.ISortingPagingService;

public interface IAddressService <D,E>
        extends IModelMapperService<D,E>,
        ICrudService<D,E>,
        ISortingPagingService<D,E> {

    // Special Address
}
