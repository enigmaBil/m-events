package com.enigma.mevents.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasAnyRole('ADMIN')")
@Tag(name = "Admin")
public class AdminController {

    @Operation(
            description = "Get endpoint for manager",
            summary = "Get all information",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read')")
    public ResponseEntity<String> get(){
        return ResponseEntity.ok("GET:: admin controller");
    }
    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin:create')")
    public String post(){
        return "POST:: admin controller";
    }
    @PutMapping
    @PreAuthorize("hasAnyAuthority('admin:update')")
    public String put(){
        return "PUT:: admin controller";
    }
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('admin:delete')")
    public String delete(){
        return "DELETE:: admin controller";
    }
}
