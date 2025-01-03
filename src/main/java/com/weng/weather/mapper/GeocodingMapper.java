package com.weng.weather.mapper;

import com.weng.weather.model.dto.external.response.MeteoLocationRes;
import com.weng.weather.model.dto.fe.response.LocationRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class GeocodingMapper {
    @Mapping(source = "country_code", target = "countryCode")
    abstract LocationRes toLocationSimpleRes(MeteoLocationRes.Result data);

    public LocationRes toLocationRes(MeteoLocationRes.Result data) {
        return toLocationSimpleRes(data);
    }
}
