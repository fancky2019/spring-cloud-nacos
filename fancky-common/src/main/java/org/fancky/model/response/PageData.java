package org.fancky.model.response;

import lombok.Data;

import java.util.List;

@Data
public class PageData<T> {
    private int count;
    private List<T> data;
}
