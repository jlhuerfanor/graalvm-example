package org.example.sensemon.application.adapter.primary.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.sensemon.application.model.FeatureType;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
public class FeatureInfoDto extends RepresentationModel<FeatureInfoDto> {
    private String name;
    private int number;
    private FeatureType featureType;
}
