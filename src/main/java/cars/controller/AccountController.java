package cars.controller;


import cars.dto.AccountDto;
import cars.dto.Response;
import cars.dto.constants.CarsApiConstants;
import cars.security.accounting.IAccountsManagement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping()
@Api(value="onlinestore")
public class AccountController {
    @Autowired
    IAccountsManagement accounts;
    // ------------------addAccount-------------------------------------------------------------------------------------
    @ApiOperation(value = "Adding a account to DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added account"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = CarsApiConstants.ADD_ACCOUNT)
    Response addAccount(@RequestBody AccountDto accountDto) {
        return accounts.addAccount(accountDto.getUsername(), accountDto.getPassword(), accountDto.getRoles());
    }

    // ------------------removeAccount----------------------------------------------------------------------------------
    @ApiOperation(value = "Removing a account from DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully removed account"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(value = CarsApiConstants.REMOVE_ACCOUNT)
//    Response removeAccount(@PathVariable(value = "email") String email) {
//        return accounts.removeAccount(email);
//    }
    Response removeAccount(@RequestBody AccountDto accountDto) {
        return accounts.removeAccount(accountDto.getUsername());
    }

    // ------------------updatePassword---------------------------------------------------------------------------------
    @ApiOperation(value = "Updating a account to DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated account"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value = CarsApiConstants.UPDATE_PASSWORD)
    Response updatePassword(@RequestBody AccountDto accountDto) {
        return accounts.updatePassword(accountDto.getUsername(), accountDto.getPassword());
    }

    // -------------------addRole---------------------------------------------------------------------------------------
    @ApiOperation(value = "Adding a role to DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added role"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = CarsApiConstants.ADD_ROLES)
    Response addRoles(@RequestBody AccountDto accountDto) {
        return accounts.addRoles(accountDto.getUsername(), accountDto.getRoles());

    }

    // -------------------removeRole------------------------------------------------------------------------------------
    @ApiOperation(value = "Removing a role from DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully removed role"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(value = CarsApiConstants.REMOVE_ROLES)
    Response removeRoles(@RequestBody AccountDto accountDto) {
        return accounts.removeRoles(accountDto.getUsername(), accountDto.getRoles());

    }
    // -----------------------------------------------------------------------------------------------------------------
}
