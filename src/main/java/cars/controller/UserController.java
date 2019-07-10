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

@CrossOrigin(maxAge = 3600)
@RestController
//@RequestMapping(CarsApiConstants.ACCOUNT)
@Api(value = "onlinestore")

public class UserController {
    private final String httpsUrl = "http://localhost:4200";
    private final String hasRoleAdmin = "hasRole('ROLE_ADMIN')";
    private final String PERMIT_ALL = "permitAll";

    @Autowired
    private IUserManagement userManagement;
    //------------------------------------shutdown-------------------------------------------------------------------
    @ApiOperation(value = "Shutdown")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully shutdown"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = httpsUrl)
    @PreAuthorize(hasRoleAdmin)
    @PostMapping(CarsApiConstants.SHUTDOWN)
    void shutdown() {  }
    //------------------------------------login-------------------------------------------------------------------
    @ApiOperation(value = "Login")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully login"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = httpsUrl)
    @PreAuthorize(PERMIT_ALL)
    @PostMapping(CarsApiConstants.ACCOUNT + CarsApiConstants.LOGIN)
    void login(@RequestBody UserDto userDto) {  }
     // ------------------addUser-------------------------------------------------------------------------------------
    @ApiOperation(value = "Adding an user to DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = httpsUrl)
    @PreAuthorize(PERMIT_ALL)
    @PostMapping(CarsApiConstants.ACCOUNT)
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
    @CrossOrigin(origins = httpsUrl)
    @PreAuthorize(hasRoleAdmin)
    @PutMapping(CarsApiConstants.ACCOUNT)
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
    @CrossOrigin(origins = httpsUrl)
    @PreAuthorize(hasRoleAdmin)
    @GetMapping(CarsApiConstants.ACCOUNT)
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
    @DeleteMapping(CarsApiConstants.ACCOUNT)
    Response removeUser(@RequestParam(value = "username") String username) {
        return userManagement.removeUser(username);
    }

    // -----------------------------------------------------------------------------------------------------------------
}
