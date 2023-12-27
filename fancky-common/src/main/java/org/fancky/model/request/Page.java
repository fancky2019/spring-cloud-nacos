package org.fancky.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class Page implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer pageSize;
    private Integer pageIndex;
}
