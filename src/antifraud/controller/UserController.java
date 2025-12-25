package antifraud.controller;

import antifraud.entity.user.*;
import antifraud.service.MaiUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final MaiUserService maiUserService;

    public UserController(MaiUserService maiUserService) {
        this.maiUserService = maiUserService;
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public MaiUserResponse saveUser(@RequestBody @Valid MaiUserRequest request) {
        return new MaiUserResponse(maiUserService.save(request));
    }

    @GetMapping("/list")
    public List<MaiUserResponse> listUsers() {
        return maiUserService.listUsers();
    }

    @PutMapping("/role")
    public MaiUserResponse updateRole(@RequestBody @Valid RoleRequest request) {
        return maiUserService.updateRole(request);
    }

    @PutMapping("/access")
    public LockingResponse lockUser(@RequestBody @Valid LockingRequest request) {
        return maiUserService.lockUser(request);
    }

    @DeleteMapping("/user/{username}")
    public UserDeleteResponse deleteUser(@PathVariable String username) {
        return maiUserService.delete(username);
    }
}
