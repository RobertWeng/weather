package com.weng.weather.model.dto.fe.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResultList<T> {

    private long total;
    private List<T> result;

    public ResultList(long total, List<T> result) {
        this.total = total;
        this.result = result;
    }
}