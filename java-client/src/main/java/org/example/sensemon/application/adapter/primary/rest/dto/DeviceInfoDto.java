package org.example.sensemon.application.adapter.primary.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.sensemon.application.model.BusType;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
public class DeviceInfoDto extends RepresentationModel<DeviceInfoDto> {
    private String prefix;
    private BusType busType;
    private int busNumber;
    private String path;
    private int address;
    private String name;
}
