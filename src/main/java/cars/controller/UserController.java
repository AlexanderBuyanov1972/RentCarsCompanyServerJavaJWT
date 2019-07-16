package cars.controller;

import cars.dto.Response;
import cars.dto.UserDto;
import cars.dto.constants.CarsApiConstants;
import cars.service.account.IUserManagement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin( maxAge = 3600)
@RestController
@RequestMapping(CarsApiConstants.ACCOUNT)
@Api(value = "onlinestore")

public class UserController {
    private final String httpsUrl = "*";
    private final String allowed_headers = "*";
    private final String hasRoleAdmin = "hasRole('ROLE_ADMIN')";
    private final String PERMIT_ALL = "permitAll";

    @Autowired
    private IUserManagement userManagement;

     // ------------------addUser-------------------------------------------------------------------------------------
    @ApiOperation(value = "Adding an user to DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = httpsUrl, allowedHeaders = allowed_headers)
    @PreAuthorize(hasRoleAdmin)
    @PostMapping()
    Response addUser(@RequestBody UserDto userDto) {
        return userManagement.addUser(userDto);
    }

    // ------------------updateUser---------------------------------------------------------------------------------
    @ApiOperation(value = "Updating an user in DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = httpsUrl, allowedHeaders = allowed_headers)
    @PreAuthorize(hasRoleAdmin)
    @PutMapping()
    Response updateUser(@RequestBody UserDto userDto) {
        return userManagement.updateUser(userDto);
    }

    // -------------------getUser---------------------------------------------------------------------------------------
    @ApiOperation(value = "Getting an user from DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully got user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = httpsUrl, allowedHeaders = allowed_headers)
    @PreAuthorize(hasRoleAdmin)
    @GetMapping()
    Response getUser(@RequestParam(value = "username") String username) {
        return userManagement.getUser(username);

    }

    // ------------------removeUser----------------------------------------------------------------------------------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @ApiOperation(value = "Removing an user from DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully removed user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = httpsUrl)
    @PreAuthorize(hasRoleAdmin)
    @DeleteMapping()
    Response removeUser(@RequestParam(value = "username") String username) {
        return userManagement.removeUser(username);
    }
    //------------------------------------getUserRole-------------------------------------------------------------------
    @ApiOperation(value = "Get User Role", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully got role"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = httpsUrl, allowedHeaders = allowed_headers)
    @PreAuthorize(PERMIT_ALL)
    @GetMapping(CarsApiConstants.GET_ROLE)
    Response getUserRole(@RequestParam(value = "username") String username) {
        return userManagement.getUserRole(username);
    }
    // -----------------------------------------------------------------------------------------------------------------
}
