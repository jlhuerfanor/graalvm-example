package org.example.sensemon.application.conversion;

import org.example.sensemon.adapter.rest.dto.DeviceInfoDto;
import org.example.sensemon.application.model.DeviceInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DeviceInfoToDtoConverter implements Converter<DeviceInfo, DeviceInfoDto> {
    @Override
    public DeviceInfoDto convert(DeviceInfo source) {
        var info = new DeviceInfoDto();

        info.setName(source.getName());
        info.setPath(source.getPath());
        info.setAddress(source.getAddress());
        info.setPrefix(source.getPrefix());
        info.setBusType(source.getBus().getType());
        info.setBusNumber(source.getBus().getNumber());

        return info;
    }
}
