package org.example.sensemon.application.adapter.primary.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.sensemon.application.model.SubFeatureType;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
public class SubFeatureInfoDto extends RepresentationModel<SubFeatureInfoDto> {
    private String name;
    private int number;
    private SubFeatureType type;
    private int mapping;
    private int flags;
}
