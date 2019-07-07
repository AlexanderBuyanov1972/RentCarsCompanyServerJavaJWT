package cars.controller;

import cars.dto.AccountDto;
import cars.dto.Response;
import cars.dto.constants.CarsApiConstants;
import cars.service.account.IAccountsManagement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(CarsApiConstants.ACCOUNT)
@Api(value = "onlinestore")

public class AccountController {
    private final String httpsUrl = "http://localhost:4200";
    private final String hasRoleAdmin = "hasRole('ROLE_ADMIN')";
    private final String PERMIT_ALL = "permitAll";
    @Autowired
    IAccountsManagement accounts;
    // ------------------login-------------------------------------------------------------------------------------
    @ApiOperation(value = "Login", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login is successfully"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = httpsUrl)
    @PreAuthorize(PERMIT_ALL)
    @PostMapping(CarsApiConstants.LOGIN)
    Response login(@RequestBody AccountDto accountDto) {
        return accounts.login(accountDto);
    }

    // ------------------addAccount-------------------------------------------------------------------------------------
    @ApiOperation(value = "Adding an account to DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added account"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = httpsUrl)
    @PreAuthorize(hasRoleAdmin)
    @PostMapping()
    Response addAccount(@RequestBody AccountDto accountDto) {
        return accounts.addAccount(accountDto);
    }

    // ------------------updateAccount---------------------------------------------------------------------------------
    @ApiOperation(value = "Updating an account in DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated account"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = httpsUrl)
    @PreAuthorize(hasRoleAdmin)
    @PutMapping()
    Response updateAccount(@RequestBody AccountDto accountDto) {
        return accounts.updateAccount(accountDto);
    }

    // -------------------getAccount---------------------------------------------------------------------------------------
    @ApiOperation(value = "Getting an account from DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully got account"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = httpsUrl)
    @PreAuthorize(hasRoleAdmin)
    @GetMapping()
    Response getAccount(@RequestParam(value = "username") String username) {
        return accounts.getAccount(username);

    }

    // ------------------removeAccount----------------------------------------------------------------------------------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @ApiOperation(value = "Removing an account from DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully removed account"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = httpsUrl)
    @PreAuthorize(hasRoleAdmin)
    @DeleteMapping()
    Response removeAccount(@RequestParam(value = "username") String username) {
        return accounts.removeAccount(username);
    }

    // -----------------------------------------------------------------------------------------------------------------
}
