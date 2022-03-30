package com.ndm.api.controller;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.config.ApiPathConfig;
import com.ndm.api.dto.CredentialRequest;
import com.ndm.api.dto.CredentialRequestBody;
import com.ndm.api.dto.CredentialsResponse;
import com.ndm.api.dto.Success;
import com.ndm.api.exception.InvalidParameterException;
import com.ndm.api.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
public class CredentialController {

    private final CredentialService credentialService;

    @Autowired
    public CredentialController(final CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    /**
     * This is a method to get all credential
     * @return CredentialsResponse Object
     */
    @GetMapping(ApiPathConfig.GET_ALL_CREDENTIAL_URL)
    public CredentialsResponse getAll() {
        return new CredentialsResponse(credentialService.getAll());
    }

    /**
     * This is a method of adding credential
     * @param request CredentialRequestBody
     * @param bindingResult BindingResult
     * @throws InvalidParameterException if invalid parameter
     * @return Success Object {200, "message"}
     */
    @PostMapping(ApiPathConfig.ADD_CREDENTIAL_URL)
    public Success add(@Valid @RequestBody final CredentialRequestBody request, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(getErrorMessage(bindingResult));
        }

        credentialService.add(request);
        return new Success(HttpStatus.OK.value(), String.format(ConstantCommon.ADD_SUCCESSFULLY, "credential"));
    }

    /**
     * This is a method of updating credential
     * @param request CredentialRequest
     * @param bindingResultRequest BindingResult
     * @param requestBody CredentialRequestBody
     * @param bindingResultRequestBody BindingResult
     * @throws InvalidParameterException if invalid parameter
     * @return Success Object {200, "message"}
     */
    @PutMapping(ApiPathConfig.UPDATE_CREDENTIAL_URL)
    public Success update(@Valid final CredentialRequest request, final BindingResult bindingResultRequest,
                          @Valid @RequestBody final CredentialRequestBody requestBody, final BindingResult bindingResultRequestBody) {
        if (bindingResultRequest.hasErrors()) {
            throw new InvalidParameterException(getErrorMessage(bindingResultRequest));
        }

        if (bindingResultRequestBody.hasErrors()) {
            throw new InvalidParameterException(getErrorMessage(bindingResultRequestBody));
        }

        credentialService.update(request, requestBody);
        return new Success(HttpStatus.OK.value(), String.format(ConstantCommon.UPDATE_SUCCESSFULLY, "credential"));
    }

    /**
     * This is a method of deleting credential
     * @param request CredentialRequest
     * @param bindingResult BindingResult
     * @return Success Object {200, "message"}
     */
    @DeleteMapping(ApiPathConfig.DELETE_CREDENTIAL_URL )
    public Success delete(@Valid final CredentialRequest request,
                           final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(getErrorMessage(bindingResult));
        }

        credentialService.delete(Integer.parseInt(request.getId()));
        return new Success(HttpStatus.OK.value(), ConstantCommon.DELETE_SUCCESSFULLY);
    }

    /**
     * This a method get errors message from BindingResult
     * @param bindingResult BindingResult
     * @return message String
     */
    private String getErrorMessage(final BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(ConstantCommon.DELIMITER_CHARACTER));
    }
}
