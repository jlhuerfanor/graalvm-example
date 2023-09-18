package org.example.sensemon.application.conversion;

import org.example.sensemon.adapter.rest.dto.SubFeatureInfoDto;
import org.example.sensemon.application.model.SubFeatureInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubFeatureInfoToDtoConverter implements Converter<SubFeatureInfo, SubFeatureInfoDto> {
    @Override
    public SubFeatureInfoDto convert(SubFeatureInfo source) {
        var info = new SubFeatureInfoDto();

        info.setName(source.getName());
        info.setNumber(source.getNumber());
        info.setType(source.getType());
        info.setMapping(source.getMapping());
        info.setFlags(source.getFlags());

        return info;
    }
}
