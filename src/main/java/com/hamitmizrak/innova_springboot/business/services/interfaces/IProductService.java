package com.hamitmizrak.innova_springboot.business.services.interfaces;

import com.hamitmizrak.innova_springboot.business.services.ICrudService;
import com.hamitmizrak.innova_springboot.business.services.IModelMapperService;

public interface IProductService<D,E>
        extends IModelMapperService<D,E>,
        ICrudService<D,E> {

    // Special Address
}
