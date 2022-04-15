package com.ndm.api.dto;

import com.ndm.api.dto.credential.CredentialRequestBody;
import com.ndm.api.dto.credential.CredentialResponse;
import com.ndm.api.dto.device.DeviceAddRequestBody;
import com.ndm.api.dto.device.DeviceResponse;
import com.ndm.api.dto.intefaces.InterfaceAddRequestBody;
import com.ndm.api.dto.intefaces.InterfaceResponse;
import com.ndm.api.dto.ntpclient.NtpResponse;
import com.ndm.api.dto.ntpserver.NtpServerAddRequestBody;
import com.ndm.api.dto.ntpserver.NtpServerResponse;
import com.ndm.api.dto.port.PortAddRequestBody;
import com.ndm.api.dto.port.PortResponse;
import com.ndm.api.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoMapper {
    private final ModelMapper mapper;

    @Autowired
    public DtoMapper(final ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }

    /**
     * This is a method convert Credential list to CredentialsResponse
     * @param credentials List<Credential>
     * @return List<CredentialResponse>
     */
    public List<CredentialResponse> mapToCredentialResponseList(final List<Credential> credentials) {
        return !credentials.isEmpty() ? credentials.stream().map(credential -> mapper.map(credential, CredentialResponse.class)).collect(Collectors.toList()) : Collections.emptyList();
    }

    /**
     * This is a method convert CredentialRequestBody to Credential
     * @param requestBody CredentialRequestBody
     * @return Credential
     */
    public Credential mapToCredential(final CredentialRequestBody requestBody) {
        return !ObjectUtils.isEmpty(requestBody) ? mapper.map(requestBody, Credential.class) : null;
    }

    /**
     * This is a method convert Devices to DeviceListResponse
     * @param devices List<Device>
     * @return List<DeviceResponse>
     */
    public List<DeviceResponse> mapToDeviceResponseList(final List<Device> devices) {
        return !devices.isEmpty() ? devices.stream().map(device -> mapper.map(device, DeviceResponse.class)).collect(Collectors.toList()) : Collections.emptyList();
    }

    /**
     * This is a method convert Device to DeviceResponse
     * @param device Device
     * @return DeviceResponse
     */
    public DeviceResponse mapToDeviceResponse(final Device device) {
        return !ObjectUtils.isEmpty(device) ? mapper.map(device, DeviceResponse.class) : null;
    }

    /**
     * This is a method convert DeviceAddRequestBody to Device
     * @param requestBody DeviceAddRequestBody
     * @return Device
     */
    public Device mapToDevice(final DeviceAddRequestBody requestBody) {
        return !ObjectUtils.isEmpty(requestBody) ? mapper.map(requestBody, Device.class) : null;
    }

    /**
     *
     * @param label String
     * @param ipAddress String
     * @param port int
     * @return Device
     */
    public Device mapToDevice(final String label, final String ipAddress, final int port) {
        return Device.builder().label(label).ipAddress(ipAddress).port(port).build();
    }

    /**
     * This is a method convert Interface list to InterfaceListResponse
     * @param interfaces List<Interface>
     * @return List<InterfaceResponse>
     */
    public List<InterfaceResponse> mapToInterfaceResponseList(final List<Interface> interfaces) {
        return !interfaces.isEmpty() ? interfaces.stream().map(anInterface -> mapper.map(anInterface, InterfaceResponse.class)).collect(Collectors.toList()) : Collections.emptyList();
    }

    /**
     * This is a method convert InterfaceAddRequestBody to Interface
     * @param requestBody InterfaceAddRequestBody
     * @return Interface
     */
    public Interface mapToInterface(final InterfaceAddRequestBody requestBody) {
        return Interface.builder()
                        .name(requestBody.getName())
                        .dhcp(requestBody.isDhcp())
                        .state(requestBody.isState())
                        .ipAddress(requestBody.getIpAddress())
                        .netmask(requestBody.getNetmask())
                        .gateway(requestBody.getGateway())
                        .build();
    }

    /**
     * This is a method convert NtpClient, NtpServer list to NtpResponse
     * @param ntpClient NtpClient
     * @param ntpServers List<NtpServer>
     * @return NtpResponse
     */
    public NtpResponse mapToNtpResponse(final NtpClient ntpClient, final List<NtpServer> ntpServers) {
        final List<NtpServerResponse> ntpServerResponses = !ntpServers.isEmpty() ? ntpServers.stream().map(ntpServer -> mapper.map(ntpServer, NtpServerResponse.class)).collect(Collectors.toList()) : Collections.emptyList();
        final NtpResponse ntpResponse = mapper.map(ntpClient, NtpResponse.class);
        ntpResponse.setNtpServers(ntpServerResponses);
        return ntpResponse;
    }

    /**
     * This is a method convert NtpServer list to NtpServerResponse list
     * @param ntpServers List<NtpServer>
     * @return List<NtpServerResponse>
     */
    public List<NtpServerResponse> mapToNtpServerResponseList(final List<NtpServer> ntpServers) {
        return !ntpServers.isEmpty() ? ntpServers.stream().map(ntpServer -> mapper.map(ntpServer, NtpServerResponse.class)).collect(Collectors.toList()) : Collections.emptyList();
    }

    /**
     * This is a method convert  to NtpServerAddRequestBody to NtpServer
     * @param requestBody NtpServerAddRequestBody
     * @return NtpServer
     */
    public NtpServer mapToNtpServer(final NtpServerAddRequestBody requestBody) {
        return !ObjectUtils.isEmpty(requestBody) ? mapper.map(requestBody, NtpServer.class) : null;
    }

    /**
     * This method to map NtpServer Object
     * @param clockName String
     * @param ipAddress String
     * @return NtpServer
     */
    public NtpServer mapToNtpServer(final String clockName, final String ipAddress) {
        return NtpServer.builder().clockName(clockName).ipAddress(ipAddress).build();
    }

    /**
     * This is a method convert Ports list to PortListResponse
     * @param ports List<Port>
     * @return List<PortResponse>
     */
    public List<PortResponse> mapToPortListResponse(final List<Port> ports) {
        return !ports.isEmpty() ? ports.stream().map(port -> mapper.map(port, PortResponse.class)).collect(Collectors.toList()) : Collections.emptyList();
    }

    /**
     * This is a method convert PortAddRequestBody to Port
     * @param requestBody PortAddRequestBody
     * @return Port
     */
    public Port mapToPort(final PortAddRequestBody requestBody) {
        return Port.builder()
                   .name(requestBody.getName())
                   .connector(requestBody.getConnector())
                   .state(requestBody.isState())
                   .speed(requestBody.getSpeed())
                   .mtu(requestBody.getMtu())
                   .mdi(requestBody.getMdi())
                   .macAddress(requestBody.getMacAddress())
                   .build();
    }
}
