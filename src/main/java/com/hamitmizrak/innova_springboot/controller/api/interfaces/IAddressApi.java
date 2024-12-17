package com.hamitmizrak.innova_springboot.controller.api.interfaces;

import com.hamitmizrak.innova_springboot.controller.api.ICrudApi;
import com.hamitmizrak.innova_springboot.controller.api.ISortingPagingApi;

public interface IAddressApi <D>
        extends ICrudApi<D>, ISortingPagingApi<D> {
}

