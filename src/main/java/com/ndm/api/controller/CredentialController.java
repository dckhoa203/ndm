package com.ndm.api.controller;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.config.ApiPathConfig;
import com.ndm.api.dto.credential.CredentialMapper;
import com.ndm.api.dto.credential.CredentialRequest;
import com.ndm.api.dto.credential.CredentialRequestBody;
import com.ndm.api.dto.credential.CredentialsResponse;
import com.ndm.api.dto.Success;
import com.ndm.api.exception.InvalidParameterException;
import com.ndm.api.service.CredentialService;
import com.ndm.api.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This CredentialController class to define all api related to Credential
 */
@RestController
public class CredentialController {

    private final CredentialService credentialService;

    @Autowired
    private CredentialMapper credentialMapper;

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
        return credentialMapper.mapToCredentialsResponse(credentialService.getAll());
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
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
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
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResultRequest));
        }

        if (bindingResultRequestBody.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResultRequestBody));
        }

        credentialService.update(Integer.parseInt(request.getId()), requestBody);
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
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }

        credentialService.delete(Integer.parseInt(request.getId()));
        return new Success(HttpStatus.OK.value(), ConstantCommon.DELETE_SUCCESSFULLY);
    }
}
