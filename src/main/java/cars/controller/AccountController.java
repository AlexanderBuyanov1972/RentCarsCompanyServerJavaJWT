package cars.controller;


import cars.dto.AccountDto;
import cars.dto.Response;
import cars.service.accounting.IAccountsManagment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping()
@Api(value="onlinestore")
public class AccountController {
    @Autowired
    IAccountsManagment accounts;
    // ------------------addAccount-------------------------------------------------------------------------------------
    @ApiOperation(value = "Adding a account to DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added account"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @PostMapping(value = "/account")
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
    @DeleteMapping(value = "/account/{username}")
    Response removeAccount(@PathVariable(value = "username") String username) {
        return accounts.removeAccount(username);
    }

    // ------------------updatePassword---------------------------------------------------------------------------------
    @ApiOperation(value = "Updating a account to DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated account"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @PutMapping(value = "/account")
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
    @PostMapping(value = "/role")
    Response addRole(@RequestBody AccountDto accountDto) {
        return accounts.addRole(accountDto.getUsername(), accountDto.getRoles());

    }

    // -------------------removeRole------------------------------------------------------------------------------------
    @ApiOperation(value = "Removing a role from DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully removed role"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @DeleteMapping(value = "/role")
    Response removeRole(@RequestBody AccountDto accountDto) {
        return accounts.removeRole(accountDto.getUsername(), accountDto.getRoles());

    }
    // -----------------------------------------------------------------------------------------------------------------
}
