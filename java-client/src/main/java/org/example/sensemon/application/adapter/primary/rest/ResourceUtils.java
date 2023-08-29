package org.example.sensemon.application.adapter.primary.rest;

import org.example.sensemon.application.adapter.primary.rest.dto.DeviceInfoDto;
import org.example.sensemon.application.adapter.primary.rest.dto.FeatureInfoDto;
import org.example.sensemon.application.adapter.primary.rest.dto.SubFeatureInfoDto;
import org.springframework.hateoas.Link;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ResourceUtils {
    public static DeviceInfoDto addResourceLinks(DeviceInfoDto source) {
        var selfUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/sensors/{id}")
                .buildAndExpand(source.getName());

        var featuresUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/sensors/{id}/features")
                .buildAndExpand(source.getName());

        return source
                .add(Link.of(selfUri.toUriString()).withSelfRel())
                .add(Link.of(featuresUri.toUriString()).withRel("features"));
    }

    public static FeatureInfoDto addResourceLinks(String deviceName, FeatureInfoDto source) {
        var selfUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/sensors/{deviceName}/features/{featureId}")
                .buildAndExpand(deviceName, source.getName());

        var subfeaturesUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/sensors/{deviceName}/features/{featureId}/sub-features")
                .buildAndExpand(deviceName, source.getName());

        var deviceUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/sensors/{deviceName}")
                .buildAndExpand(deviceName);

        return source
                .add(Link.of(selfUri.toUriString()).withSelfRel())
                .add(Link.of(deviceUri.toUriString()).withRel("parent-device"))
                .add(Link.of(subfeaturesUri.toUriString()).withRel("sub-features"));
    }

    public static SubFeatureInfoDto addResourceLinks(String deviceName, String featureName, SubFeatureInfoDto source) {
        var selfUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/sensors/{deviceName}/features/{featureId}/sub-features/{subFeatureId}")
                .buildAndExpand(deviceName, featureName, source.getName());

        var valueUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/sensors/{deviceName}/features/{featureId}/sub-features/{subFeatureId}/value")
                .buildAndExpand(deviceName, featureName, source.getName());

        var featureUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/sensors/{deviceName}/features/{featureId}")
                .buildAndExpand(deviceName, featureName);

        return source
                .add(Link.of(selfUri.toUriString()).withSelfRel())
                .add(Link.of(featureUri.toUriString()).withRel("parent-feature"))
                .add(Link.of(valueUri.toUriString()).withRel("value"));
    }
}
