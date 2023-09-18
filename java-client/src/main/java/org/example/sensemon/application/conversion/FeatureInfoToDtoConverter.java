package org.example.sensemon.application.conversion;

import org.example.sensemon.adapter.rest.dto.FeatureInfoDto;
import org.example.sensemon.application.model.FeatureInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FeatureInfoToDtoConverter implements Converter<FeatureInfo, FeatureInfoDto> {
    @Override
    public FeatureInfoDto convert(FeatureInfo source) {
        var info = new FeatureInfoDto();

        info.setName(source.getName());
        info.setNumber(source.getNumber());
        info.setFeatureType(source.getFeatureType());

        return info;
    }
}
