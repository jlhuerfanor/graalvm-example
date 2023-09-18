package org.example.sensemon.adapter.rest;

import org.example.sensemon.adapter.rest.dto.DeviceInfoDto;
import org.example.sensemon.adapter.rest.dto.FeatureInfoDto;
import org.example.sensemon.adapter.rest.dto.SubFeatureInfoDto;
import org.example.sensemon.application.model.SensorData;
import org.example.sensemon.application.service.SensorsService;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensors")
public class SensorMonitorController {
    private final SensorsService sensorsService;
    private final ConversionService conversionService;

    public SensorMonitorController(SensorsService sensorsService, ConversionService conversionService) {
        this.sensorsService = sensorsService;
        this.conversionService = conversionService;
    }

    @GetMapping
    public List<DeviceInfoDto> getDevices() {
        return sensorsService.getDevices().stream()
                .map(value -> conversionService.convert(value, DeviceInfoDto.class))
                .map(ResourceUtils::addResourceLinks)
                .collect(Collectors.toList());
    }

    @GetMapping("/{deviceName}")
    public DeviceInfoDto getDevice(@PathVariable("deviceName") String deviceName) {
        return Optional.of(deviceName)
                .map(sensorsService::getDevice)
                .map(value -> conversionService.convert(value, DeviceInfoDto.class))
                .map(ResourceUtils::addResourceLinks)
                .orElseThrow();
    }

    @GetMapping("/{deviceName}/features")
    public List<FeatureInfoDto> getFeatures(@PathVariable("deviceName") String deviceName) {
        return sensorsService.getFeatures(deviceName).stream()
                .map(value -> conversionService.convert(value, FeatureInfoDto.class))
                .map(value -> ResourceUtils.addResourceLinks(deviceName, value))
                .collect(Collectors.toList());
    }

    @GetMapping("/{deviceName}/features/{featureName}")
    public FeatureInfoDto getFeature(
            @PathVariable("deviceName") String deviceName,
            @PathVariable("featureName") String featureName) {
        return Optional.ofNullable(sensorsService.getFeature(deviceName, featureName))
                .map(value -> conversionService.convert(value, FeatureInfoDto.class))
                .map(value -> ResourceUtils.addResourceLinks(deviceName, value))
                .orElseThrow();
    }

    @GetMapping("/{deviceName}/features/{featureName}/sub-features")
    public List<SubFeatureInfoDto> getSubFeatures(
            @PathVariable("deviceName") String deviceName,
            @PathVariable("featureName") String featureName) {
        return sensorsService.getSubFeatures(deviceName, featureName).stream()
                .map(value -> conversionService.convert(value, SubFeatureInfoDto.class))
                .map(value -> ResourceUtils.addResourceLinks(deviceName, featureName, value))
                .collect(Collectors.toList());
    }

    @GetMapping("/{deviceName}/features/{featureName}/sub-features/{subfeatureName}")
    public SubFeatureInfoDto getSubFeature(
            @PathVariable("deviceName") String deviceName,
            @PathVariable("featureName") String featureName,
            @PathVariable("subfeatureName") String subfeatureName) {
        return Optional.ofNullable(sensorsService.getSubFeature(deviceName, featureName, subfeatureName))
                .map(value -> conversionService.convert(value, SubFeatureInfoDto.class))
                .map(value -> ResourceUtils.addResourceLinks(deviceName, featureName, value))
                .orElseThrow();
    }

    @GetMapping("/{deviceName}/features/{featureName}/sub-features/{subfeatureName}/value")
    public SensorData getValue(
            @PathVariable("deviceName") String deviceName,
            @PathVariable("featureName") String featureName,
            @PathVariable("subfeatureName") String subfeatureName) {
        return sensorsService.getValue(deviceName, featureName, subfeatureName);
    }
}
